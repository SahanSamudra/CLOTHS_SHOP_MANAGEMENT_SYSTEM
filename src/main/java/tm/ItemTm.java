package tm;

public class ItemTm {
    private  String Iid;
    private String type;
    private double price;
    private  int qty;
    private String size;
    private String supplier;
    private double Tcost;

    public ItemTm(String iid, String type, String size, double price, int qty, String supplier, double tcost) {
    }

    public ItemTm(String iid, String type, double price, int qty, String size, String supplier, double tcost) {
        Iid = iid;
        this.type = type;
        this.price = price;
        this.qty = qty;
        this.size = size;
        this.supplier = supplier;
        Tcost = tcost;
    }

    public String getIid() {
        return Iid;
    }

    public void setIid(String iid) {
        Iid = iid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getTcost() {
        return Tcost;
    }

    public void setTcost(double tcost) {
        Tcost = tcost;
    }

    @Override
    public String toString() {
        return "ItemTm{" +
                "Iid='" + Iid + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", size='" + size + '\'' +
                ", supplier='" + supplier + '\'' +
                ", Tcost=" + Tcost +
                '}';
    }
}
