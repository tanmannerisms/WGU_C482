package wgu.me.c482;

public class Outsourced extends Part{
    private String companyName;

    /**
     * This constructor calls the super constructor of the abstract class Part and sets the companyName of the Part.
     *
     * @param id the id that will be passed to the super's constructor.
     * @param name the name that will be passed to the super's constructor.
     * @param price the price that will be passed to the super's constructor.
     * @param stock the stock level that will be passed to the super's constructor.
     * @param min the minimum stock boundary that will be passed to the super's constructor.
     * @param max the maximum stock boundary that will be passed to the super's constructor.
     * @param companyName the name of the company that manufactures the part.
     */
    Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        setCompanyName(companyName);
    }

    /**
     * Setter for assigning a company name to an Outsourced part.
     *
     * @param companyName the company name to set for the outsourced part
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     *
     * @return the company name of the part
     */
    public String getCompanyName() {
        return companyName;
    }
}
