package wgu.me.c482;

public class Outsourced extends Part{
    private String companyName;
    Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     *
     * @param companyName sets the company name for the outsourced part
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
