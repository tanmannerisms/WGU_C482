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
    private String partName;
    private int partId;
    private int partStock;
    private int partMin;
    private int partMax;
    private double partPrice;
    private final String inHouseLabelText = "Machine ID", outSourcedLabelText = "Company Name";

    @FXML
    private TextField idField, nameField, stockField, minField, maxField, priceField, sourceTypeField;
    @FXML
    private Label sourceTypeLabel;
    @FXML
    private RadioButton inHouseButton, outSourcedButton;
    @FXML
    private final ToggleGroup radioButtonTGroup = new ToggleGroup();

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
    private void addInHousePart() {
        try {
            getPartFormInfo();
            validateFormInfo();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            openErrorWindow(e);
            return;
        }
        int partMachineId = getIntFromTextField(sourceTypeField);
        Part newPart = new InHouse(
                partId, partName, partPrice, partStock, partMin, partMax, partMachineId
        );
        System.out.println("In-house part " + newPart.getName() + " has been successfully created.");
        Inventory.addPart(newPart);
    }
    private void addOutSourcedPart() {
        try {
            getPartFormInfo();
            validateFormInfo();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            openErrorWindow(e);
            return;
        }
        String partCompanyName = sourceTypeField.getText();
        Part newPart = new Outsourced(
                partId, partName, partPrice, partStock, partMin, partMax, partCompanyName
        );
        System.out.println("Outsourced part " + newPart.getName() + " has been successfully created.");
        Inventory.addPart(newPart);
    }
    private void getPartFormInfo() throws IOException {
        partName = nameField.getText();
        try {
            partStock = getIntFromTextField(stockField);
            partPrice = getDoubleFromTextField(priceField);
            partMin = getIntFromTextField(minField);
            partMax = getIntFromTextField(maxField);
        } catch (NumberFormatException | NullPointerException e) {
            throw InvalidNumericInput;
        }
        partId = createPartId();
    }
    private void validateFormInfo() throws IOException {
        if (!(partMin <= partStock & partStock <= partMax)) {
            throw StockOutOfBounds;
        }
        if (partMin < 0) {
            throw MinTooLow;
        }
    }
    private int createPartId() {
        int id = Inventory.partId;
        Inventory.partId++;
        return id;
    }
    @FXML
    private void onSaveClick(ActionEvent actionEvent) {
        if (radioButtonTGroup.getSelectedToggle() == outSourcedButton) {
            addOutSourcedPart();
        }
        else addInHousePart();
        actionEvent.consume();
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
