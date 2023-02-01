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

/**
 * RUNTIME ERROR: When altering a part, the part will automatically be set to outsourced when trying to edit it for a
 * second time.
 *
 * Ex. Added an InHouse part named Test to the inventory through the add-part form. Select the part and hit change.
 *      It opens up the change-part form with the OutSourced radio button selected. Change it back to InHouse and save.
 *      Open it back up under the change-part form. InHouse radio button is selected as expected. Click save and open
 *      it back up again for the same part. Now the OutSourced radio button is selected again. Clicking on cancel
 *      instead of save will circumvent this.
 *
 * After having run the program in debug mode and crawling through the code, line by line, I found that the boolean
 * value inHouseSelected was not being set on initialization of the change-part form. I added that functionality into
 * the setFields() method.
 */
public class PartController extends Controller implements Initializable {
    private boolean inHouseSelected;
    private String partCompanyName;
    private int partMachineId;
    private final String inHouseLabelText = "Machine ID", outSourcedLabelText = "Company Name";
    @FXML
    private TextField sourceTypeField, idField;
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

    /**
     * Populates the TextFields with data from the importedPart
     */
    protected void setFields() {
        if (importedPart != null) {
            idField.setText(Integer.toString(importedPart.getId()));
            nameField.setText(importedPart.getName());
            stockField.setText(Integer.toString(importedPart.getStock()));
            priceField.setText(Double.toString(importedPart.getPrice()));
            minField.setText(Integer.toString(importedPart.getMin()));
            maxField.setText(Integer.toString(importedPart.getMax()));

            if (importedPart instanceof InHouse) {
                sourceTypeField.setText(Integer.toString(((InHouse) importedPart).getMachineId()));
                sourceTypeLabel.setText(inHouseLabelText);
                radioButtonTGroup.selectToggle(inHouseButton);
                inHouseSelected = true;
            }
            if (importedPart instanceof Outsourced) {
                sourceTypeField.setText(((Outsourced) importedPart).getCompanyName());
                sourceTypeLabel.setText(outSourcedLabelText);
                radioButtonTGroup.selectToggle(outSourcedButton);
                inHouseSelected = false;
            }
        }
    }

    /**
     * Creates a new part and adds it to the inventory using the data int the TextFields of the add-part window.
     * Opens error window when getting data from the TextFields if data not congruent with requirements.
     *
     * @param actionEvent passed to closeWindow() for consumption
     * @see #closeWindow(ActionEvent)
     * @see #getPartFormInfo()
     * @see #validateFormData()
     * @see InHouse#InHouse(int, String, double, int, int, int, int)  InHouse
     * @see Outsourced#Outsourced(int, String, double, int, int, int, String)  Outsourced
     * @see Inventory#addPart(Part)
     * @see Controller#closeWindow(ActionEvent)
     */
    private void addNewPart(ActionEvent actionEvent) {
        try {
            getPartFormInfo();
            validateFormData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            openErrorWindow(e);
            return;
        }
        Part newPart;
        if (inHouseSelected) {
            newPart = new InHouse(
                    Inventory.partIdIterator, name, price, stock, min, max, partMachineId
            );
        }
        else {
            newPart = new Outsourced(
                    Inventory.partIdIterator, name, price, stock, min, max, partCompanyName
            );
        }
        System.out.println("In-house part " + newPart.getName() + " has been successfully created.");
        Inventory.addPart(newPart);
        closeWindow(actionEvent);
    }

    /**
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
            validateFormData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            openErrorWindow(e);
            return;
        }
        importedPart.setName(name);
        importedPart.setPrice(price);
        importedPart.setStock(stock);
        importedPart.setMin(min);
        importedPart.setMax(max);
        Part newPart;
        if (inHouseSelected & importedPart instanceof InHouse) {
            ((InHouse) importedPart).setMachineId(partMachineId);
        }
        else if (inHouseSelected & importedPart instanceof Outsourced) {
            newPart = new InHouse(importedPart.getId(), name, price, stock, min, max, partMachineId);
            Inventory.updatePart(Inventory.getPartIndex(importedPart), newPart);
        }
        else if (!inHouseSelected & importedPart instanceof Outsourced) {
            ((Outsourced) importedPart).setCompanyName(partCompanyName);
        }
        else if (!inHouseSelected & importedPart instanceof InHouse) {
            newPart = new Outsourced(importedPart.getId(), name, price, stock, min, max, partCompanyName);
            Inventory.updatePart(Inventory.getPartIndex(importedPart), newPart);
        }
        else {
            openNotifyWindow("Could not determine part type.");
        }
        importedPart.printPart();
        closeWindow(actionEvent);
    }

    /**
     * Gets all the data from the TextFields.
     *
     * @throws IOException InvalidNumericInput
     * @see Controller#InvalidNumericInput
     * @see Controller#getFormData()
     */
    private void getPartFormInfo() throws IOException {
        try {
            getFormData();
            if (inHouseSelected) {
                partMachineId = getIntFromTextField(sourceTypeField);
            } else {
                partCompanyName = sourceTypeField.getText();
            }
        } catch (NumberFormatException | NullPointerException e) {
            throw InvalidNumericInput;
        }
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
        inHouseSelected = true;
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
        inHouseSelected = false;
        actionEvent.consume();
    }
}
