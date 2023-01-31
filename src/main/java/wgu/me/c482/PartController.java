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
    private boolean isInHouse;
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

    /**
     * Sets initial layout of window.
     *
     * @param url not used.
     * @param resourceBundle not used.
     * @see #setRadioButtons()
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setRadioButtons();
    }
    public PartController() {
        super();
    }

    /**
     * Sets the initial radio button layout when the controller is initialized.
     *
     * @see #initialize(URL, ResourceBundle)
     */
    protected void setRadioButtons() {
        inHouseButton.setToggleGroup(radioButtonTGroup);
        outSourcedButton.setToggleGroup(radioButtonTGroup);
        inHouseButton.setSelected(true);
        sourceTypeLabel.setText(inHouseLabelText);
    }

    /** NEED TO NEST OTHER IF STATEMENTS INSIDE THE CHECK FOR IMPORTED PART.
     * Populates the TextFields with data from the importedPart
     */
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
            sourceTypeLabel.setText(inHouseLabelText);
            radioButtonTGroup.selectToggle(inHouseButton);
        }
        if (importedPart instanceof Outsourced) {
            sourceTypeField.setText(((Outsourced) importedPart).getCompanyName());
            sourceTypeLabel.setText(outSourcedLabelText);
            radioButtonTGroup.selectToggle(outSourcedButton);
        }
    }

    /**
     * Creates a new part and adds it to the inventory using the data int the TextFields of the add-part window.
     * Opens error window when getting data from the TextFields if data not congruent with requirements.
     *
     * @param actionEvent passed to closeWindow() for consumption
     * @see #closeWindow(ActionEvent)
     * @see #getPartFormInfo()
     * @see #validateFormInfo()
     * @see InHouse#InHouse(int, String, double, int, int, int, int)  InHouse
     * @see Outsourced#Outsourced(int, String, double, int, int, int, String)  Outsourced
     * @see Inventory#addPart(Part)
     * @see Controller#closeWindow(ActionEvent)
     */
    private void addNewPart(ActionEvent actionEvent) {
        try {
            getPartFormInfo();
            validateFormInfo();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            openErrorWindow(e);
            return;
        }
        Part newPart;
        if (isInHouse) {
            newPart = new InHouse(
                    id, name, price, stock, min, max, partMachineId
            );
        }
        else {
            newPart = new Outsourced(
                    id, name, price, stock, min, max, partCompanyName
            );
        }
        System.out.println("In-house part " + newPart.getName() + " has been successfully created.");
        Inventory.addPart(newPart);
        closeWindow(actionEvent);
    }

    /** NEEDS FIXED. CURRENTLY NOT ALLOWING CONVERSION FROM INHOUSE TO OUTSOURCED & VICE VERSA.
     * Sets all the information of the part passed in from changePartSceneChange(), using the TextFields.
     * The TextFields need not have changed for this to successfully run.
     * Opens error window when getting data from the TextFields if data not congruent with requirements.
     *
     * @param actionEvent passed to the closeWindow() for consumption.
     * @see #closeWindow(ActionEvent)
     */
    private void updatePart(ActionEvent actionEvent) {
        try {
            getPartFormInfo();
            validateFormInfo();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            openErrorWindow(e);
            return;
        }
        importedPart.setName(name);
        importedPart.setPrice(price);
        importedPart.setMin(min);
        importedPart.setMax(max);
        if (isInHouse) {
            ((InHouse) importedPart).setMachineId(partMachineId);
        }
        else ((Outsourced) importedPart).setCompanyName(partCompanyName);
        Inventory.updatePart((importedPart.getId() - 1), importedPart);
        importedPart.printPart();
        closeWindow(actionEvent);
    }

    /** NEED TO ADD THIS TO ABSTRACT CONTROLLER CLASS FOR USE IN PRODUCTCONTROLLER.
     * Gets all the data from the TextFields.
     *
     * @throws IOException InvalidNumericInput
     * @see Controller#InvalidNumericInput
     */
    private void getPartFormInfo() throws IOException {
        name = nameField.getText();
        try {
            stock = getIntFromTextField(stockField);
            price = getDoubleFromTextField(priceField);
            min = getIntFromTextField(minField);
            max = getIntFromTextField(maxField);
            if (isInHouse) {
                partMachineId = getIntFromTextField(sourceTypeField);
            } else {
                partCompanyName = sourceTypeField.getText();
            }
        } catch (NumberFormatException | NullPointerException e) {
            throw InvalidNumericInput;
        }
        id = createPartId();
    }

    /**
     * Checks the info taken from the TextFields to verify stock/inventory numbers.
     *
     * @throws IOException StockOutOfBounds, MinTooLow
     * @see MainController#StockOutOfBounds
     * @see MainController#MinTooLow
     */
    private void validateFormInfo() throws IOException {
        if (!(min <= stock & stock <= max)) {
            throw StockOutOfBounds;
        }
        if (min < 0) {
            throw MinTooLow;
        }
    }

    /**
     *
     * @return the partId static variable.
     */
    private int createPartId() {
        return Inventory.partIdIterator;
    }

    /**
     * Method called when the save button is pressed. Calls the addNewPart method if no part was imported.
     * Otherwise, calls the updatePart method.
     *
     * @param actionEvent passed into the other methods for closing the window after updating/adding a part.
     * @see #updatePart(ActionEvent)
     * @see #addNewPart(ActionEvent)
     */
    @FXML
    private void onSaveClick(ActionEvent actionEvent) {
        if (importedPart == null) {
            addNewPart(actionEvent);
        }
        else updatePart(actionEvent);
    }

    /**
     * Sets the sourceTypeLabel to "Machine ID".
     *
     * @param actionEvent consumed.
     */
    @FXML
    private void onInHouseClick(ActionEvent actionEvent) {
        sourceTypeLabel.setText(inHouseLabelText);
        isInHouse = true;
        actionEvent.consume();
    }

    /**
     * Sets the sourceTypeLabel to "Company Name".
     *
     * @param actionEvent consumed.
     */
    @FXML
    private void onOutSourcedClick(ActionEvent actionEvent) {
        sourceTypeLabel.setText(outSourcedLabelText);
        isInHouse = false;
        actionEvent.consume();
    }
}
