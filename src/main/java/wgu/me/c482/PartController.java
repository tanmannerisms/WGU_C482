package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PartController extends Controller {
    private String partName, partCompanyName;
    private int partId, partStock, partMin, partMax, partMachineId;
    private double partPrice;

    @FXML
    private TextField idField, nameField, stockField, minField, maxField, priceField, sourceTypeField;
    @FXML
    private RadioButton inHouseButton, outSourcedButton;
    private final ToggleGroup radioButtonGroup = new ToggleGroup();

    public PartController() {
        super();
    }

    PartController(Inventory inventory) {
        super(inventory);
        System.out.println("End PartController constructor with args");
    }
    @FXML
    private void addInHousePart() {
        getPartFormInfo();
        partMachineId = getIntFromTextField(sourceTypeField);
        Part newPart = new InHouse(
                partId, partName, partPrice, partStock, partMin, partMax, partMachineId
        );
        System.out.println("Part " + newPart.getName() + " has been successfully created.");
        inventory.addPart(newPart);

//        Need to get rid of this at some point or another
        System.out.println(inventory.getAllParts());
    }
    @FXML
    private void addOutSourcedPart() {
        getPartFormInfo();
        partCompanyName = sourceTypeField.getText();
        Part newPart = new Outsourced(
                partId, partName, partPrice, partStock, partMin, partMax, partCompanyName
        );
        System.out.println("Part " + newPart.getName() + " has been successfully created.");
        inventory.addPart(newPart);
    }
    private void getPartFormInfo() {
        partName = nameField.getText();
        partStock = getIntFromTextField(stockField);
        partPrice = getDoubleFromTextField(priceField);
        partMin = getIntFromTextField(minField);
        partMax = getIntFromTextField(maxField);
        partId = createPartId();
    }
    private int createPartId() {
        int id = inventory.partId;
        inventory.partId++;
        return id;
    }
    protected void setRadioButtons() {
        inHouseButton.setToggleGroup(radioButtonGroup);
        outSourcedButton.setToggleGroup(radioButtonGroup);
        inHouseButton.setSelected(true);
    }
}
