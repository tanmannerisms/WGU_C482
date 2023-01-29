package wgu.me.c482;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PartController extends Controller implements Initializable {
    private String partCompanyName;
    private int partMachineId;
    private final String inHouseLabelText = "Machine ID", outSourcedLabelText = "Company Name";
    @FXML
    private TextField sourceTypeField;
    @FXML
    private Label sourceTypeLabel;
    @FXML
    private RadioButton inHouseButton, outSourcedButton;
    @FXML
    private final ToggleGroup radioButtonTGroup = new ToggleGroup();
    protected Part importedPart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRadioButtons();
    }
    public PartController() {
        super();
    }
    
    protected void setRadioButtons() {
        inHouseButton.setToggleGroup(radioButtonTGroup);
        outSourcedButton.setToggleGroup(radioButtonTGroup);
        inHouseButton.setSelected(true);
        sourceTypeLabel.setText(inHouseLabelText);
    }
    protected void setFields() {
        if (importedPart != null) {
            nameField.setText(importedPart.getName());
            stockField.setText(Integer.toString(importedPart.getStock()));
            priceField.setText(Double.toString(importedPart.getPrice()));
            minField.setText(Integer.toString(importedPart.getMin()));
            maxField.setText(Integer.toString(importedPart.getMax()));
        }
        if (importedPart instanceof InHouse) {
            sourceTypeField.setText(Integer.toString(((InHouse) importedPart).getMachineId()));
        }
        if (importedPart instanceof Outsourced) {
            sourceTypeField.setText(((Outsourced) importedPart).getCompanyName());
        }
    }
    private void addInHousePart(ActionEvent actionEvent) {
        try {
            getPartFormInfo();
            partMachineId = getIntFromTextField(sourceTypeField);
            validateFormInfo();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            openErrorWindow(e);
            return;
        }
        Part newPart = new InHouse(
                id, name, price, stock, min, max, partMachineId
        );
        System.out.println("In-house part " + newPart.getName() + " has been successfully created.");
        Inventory.addPart(newPart);
        closeWindow(actionEvent);
    }
    private void addOutSourcedPart(ActionEvent actionEvent) {
        try {
            getPartFormInfo();
            partCompanyName = sourceTypeField.getText();
            validateFormInfo();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            openErrorWindow(e);
            return;
        }
        Part newPart = new Outsourced(
                id, name, price, stock, min, max, partCompanyName
        );
        System.out.println("Outsourced part " + newPart.getName() + " has been successfully created.");
        Inventory.addPart(newPart);
        closeWindow(actionEvent);
    }
    private void getPartFormInfo() throws IOException {
        name = nameField.getText();
        try {
            stock = getIntFromTextField(stockField);
            price = getDoubleFromTextField(priceField);
            min = getIntFromTextField(minField);
            max = getIntFromTextField(maxField);
        } catch (NumberFormatException | NullPointerException e) {
            throw InvalidNumericInput;
        }
        id = createPartId();
    }
    private void validateFormInfo() throws IOException {
        if (!(min <= stock & stock <= max)) {
            throw StockOutOfBounds;
        }
        if (min < 0) {
            throw MinTooLow;
        }
    }
    private int createPartId() {
        return Inventory.partId;
    }
    @FXML
    private void onSaveClick(ActionEvent actionEvent) {
        if (radioButtonTGroup.getSelectedToggle() == outSourcedButton) {
            addOutSourcedPart(actionEvent);
        }
        else addInHousePart(actionEvent);
    }
    @FXML
    private void onInHouseClick(ActionEvent actionEvent) {
        sourceTypeLabel.setText(inHouseLabelText);
        actionEvent.consume();
    }
    @FXML
    private void onOutSourcedClick(ActionEvent actionEvent) {
        sourceTypeLabel.setText(outSourcedLabelText);
        actionEvent.consume();
    }
}
