package wgu.me.c482;

public class InHouse extends Part {
    private int machineId;

    /**
     * This constructor calls the super constructor of the abstract class Part and sets the machineId of the part.
     *
     * @param id the id that will be passed to the super's constructor.
     * @param name the name that will be passed to the super's constructor.
     * @param price the price that will be passed to the super's constructor.
     * @param stock the stock level that will be passed to the super's constructor.
     * @param min the minimum stock boundary that will be passed to the super's constructor.
     * @param max the maximum stock boundary that will be passed to the super's constructor.
     * @param machineId the ID of the machine that is used to manufacture the part.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        setMachineId(machineId);
    }

    /**
     * Setter for assigning a machine ID to an InHouse Part.
     *
     * @param machineId the machineId to set for the Part
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     *
     * @return the machineId of the InHouse Part
     */
    public int getMachineId() {
        return this.machineId;
    }
}
