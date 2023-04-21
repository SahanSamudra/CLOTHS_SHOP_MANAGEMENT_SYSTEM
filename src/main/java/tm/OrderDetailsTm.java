package tm;

public class OrderDetailsTm {

    private String itemCode;
    private String oid;
    private double unitprice;
    private int itemcount;
    private double total;
    private String date;

    public OrderDetailsTm(String itemCode, String oid, double unitprice, int itemcount, double total, String date) {
        this.itemCode = itemCode;
        this.oid = oid;
        this.unitprice = unitprice;
        this.itemcount = itemcount;
        this.total = total;
        this.date = date;
    }

    public OrderDetailsTm() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public int getItemcount() {
        return itemcount;
    }

    public void setItemcount(int itemcount) {
        this.itemcount = itemcount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
