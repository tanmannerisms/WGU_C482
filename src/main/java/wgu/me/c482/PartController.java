package wgu.me.c482;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PartController extends Controller {
    private String name, companyName;
    private int inventory, min, max, machineId;
    private double price;

    @FXML
    private TextField id, nameField, inventoryField, minField, maxField, priceField, sourceTypeField;


    public PartController() {
        super();
    }

    PartController(String file, String title) {
        super(file, title);
    }

    private void addInHousePart() {
        name = nameField.getText();
        inventory = getIntFromTextField(inventoryField);
        price = getDoubleFromTextField(priceField);
        min = getIntFromTextField(minField);
        max = getIntFromTextField(maxField);
        machineId = getIntFromTextField(sourceTypeField);
    }

    private void addOutSourcedPart() {
        name = nameField.getText();
        inventory = getIntFromTextField(inventoryField);
        price = getDoubleFromTextField(priceField);
        min = getIntFromTextField(minField);
        max = getIntFromTextField(maxField);
        companyName = sourceTypeField.getText();
    }

    @Override
    void cancel() {
    }
}
