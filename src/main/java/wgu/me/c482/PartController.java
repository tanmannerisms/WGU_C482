package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PartController extends Controller {
    private String partName, partCompanyName;
    private int partId, partInventory, partStock, partMin, partMax, partMachineId;
    private double partPrice;

    @FXML
    private TextField idField, nameField, inventoryField, minField, maxField, priceField, sourceTypeField;
    @FXML
    private RadioButton inHouseButton, outSourcedButton;
    private final ToggleGroup radioButtonGroup = new ToggleGroup();

    public PartController() {
        super();
    }

    PartController(Inventory inventory) {
        super(inventory);

        outSourcedButton = new RadioButton();
        System.out.println("Setting toggle group of buttons");
        inHouseButton.setToggleGroup(radioButtonGroup);
        outSourcedButton.setToggleGroup(radioButtonGroup);
        System.out.print("End PartController constructor with args");
    }

    @FXML
    private void addInHousePart() {
        partName = nameField.getText();
        partInventory = getIntFromTextField(inventoryField);
        partPrice = getDoubleFromTextField(priceField);
        partMin = getIntFromTextField(minField);
        partMax = getIntFromTextField(maxField);
        partMachineId = getIntFromTextField(sourceTypeField);
        Part newPart = new InHouse(partId, partName, partPrice, partStock, partMin, partMax, partMachineId);
        System.out.println("Part " + newPart.getName() + " has been successfully created.");
    }

    @FXML
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
}
