package wgu.me.c482;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class PartController extends Controller {
    private String partName;
    private int partId;
    private int partStock;
    private int partMin;
    private int partMax;
    private double partPrice;
    private final String inHouseLabel = "Machine ID", outSourcedLabel = "Company Name";

    @FXML
    private TextField idField, nameField, stockField, minField, maxField, priceField, sourceTypeField;
    @FXML
    private Label sourceTypeLabel;
    @FXML
    private RadioButton inHouseButton, outSourcedButton;
    @FXML
    private Button saveButton;
    @FXML
    private final ToggleGroup radioButtonGroup = new ToggleGroup();

    // Event Handlers
    EventHandler<MouseEvent> onInHouseClick = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            sourceTypeLabel.setText(inHouseLabel);
        }
    };
    EventHandler<MouseEvent> onOutSourcedClick = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            sourceTypeLabel.setText(outSourcedLabel);
        }
    };
    EventHandler<MouseEvent> onSaveClick = new EventHandler<>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (radioButtonGroup.getSelectedToggle() == outSourcedButton) {
                addOutSourcedPart();
            }
            else addInHousePart();
        }
    };

    public PartController() {
        super();
    }

    PartController(Inventory inventory) {
        super(inventory);
        System.out.println("End PartController constructor with args");
    }
    protected void setRadioButtons() {
        inHouseButton.setToggleGroup(radioButtonGroup);
        outSourcedButton.setToggleGroup(radioButtonGroup);
        inHouseButton.setSelected(true);
        sourceTypeLabel.setText(inHouseLabel);
        inHouseButton.setOnMouseClicked(onInHouseClick);
        outSourcedButton.setOnMouseClicked(onOutSourcedClick);
        saveButton.setOnMouseClicked(onSaveClick);
    }
    private void addInHousePart() {
        getPartFormInfo();
        int partMachineId = getIntFromTextField(sourceTypeField);
        Part newPart = new InHouse(
                partId, partName, partPrice, partStock, partMin, partMax, partMachineId
        );
        System.out.println("In-house part " + newPart.getName() + " has been successfully created.");
        inventory.addPart(newPart);
    }
    private void addOutSourcedPart() {
        getPartFormInfo();
        String partCompanyName = sourceTypeField.getText();
        Part newPart = new Outsourced(
                partId, partName, partPrice, partStock, partMin, partMax, partCompanyName
        );
        System.out.println("Outsourced part " + newPart.getName() + " has been successfully created.");
        inventory.addPart(newPart);
    }
    private void getPartFormInfo() {
        partName = nameField.getText();
        try {
            partStock = getIntFromTextField(stockField);
            partPrice = getDoubleFromTextField(priceField);
            partMin = getIntFromTextField(minField);
            partMax = getIntFromTextField(maxField);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println(e.getMessage());
            openErrorWindow(e);
        }
        partId = createPartId();
    }
    private int createPartId() {
        int id = inventory.partId;
        inventory.partId++;
        return id;
    }
}
