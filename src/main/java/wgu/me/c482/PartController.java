package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PartController extends Controller {
    private String partName, partCompanyName;
    private int partId, partInventory, partStock, partMin, partMax, partMachineId;
    private double partPrice;

    @FXML
    private TextField id, nameField, inventoryField, minField, maxField, priceField, sourceTypeField;


    public PartController() {
        super();
    }

    PartController(String file, String title, Inventory inventory) {
        super(file, title, inventory);
    }

    private void addInHousePart() {
        partName = nameField.getText();
        partInventory = getIntFromTextField(inventoryField);
        partPrice = getDoubleFromTextField(priceField);
        partMin = getIntFromTextField(minField);
        partMax = getIntFromTextField(maxField);
        partMachineId = getIntFromTextField(sourceTypeField);
        Part newPart = new InHouse(partId, partName, partPrice, partStock, partMin, partMax, partMachineId);
    }

    private void addOutSourcedPart() {
        partName = nameField.getText();
        partInventory = getIntFromTextField(inventoryField);
        partPrice = getDoubleFromTextField(priceField);
        partMin = getIntFromTextField(minField);
        partMax = getIntFromTextField(maxField);
        partCompanyName = sourceTypeField.getText();
        Part newPart = new Outsourced(partId, partName, partPrice, partStock, partMin, partMax, partCompanyName);
    }

    private void assignPartId() {

    }
    @Override
    void cancel() {
    }
}
