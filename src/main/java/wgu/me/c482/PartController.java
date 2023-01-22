package wgu.me.c482;

public class PartController extends Controller {
    private String name, machineId, companyName;
    private int inventory, min, max;
    private double price;


    public PartController() {
        super();
    }

    PartController(String file, String title) {
        super(file, title);
    }

    private void addInHousePart() {

    }

    private void addOutSourcedPart() {

    }

    @Override
    void cancel() {
    }
}
