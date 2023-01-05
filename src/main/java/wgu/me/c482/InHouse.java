package wgu.me.c482;

public class InHouse extends Part {
    public int machineId;
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
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
