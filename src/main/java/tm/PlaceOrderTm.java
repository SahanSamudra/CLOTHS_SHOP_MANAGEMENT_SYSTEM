package tm;

public class PlaceOrderTm {

    private String code;
    private String type;

    private String size;

    private int qty;
    private double price;
    private double cost;

    public PlaceOrderTm() {
    }

    public PlaceOrderTm(String code, String type, String size, int qty, double price, double cost) {
        this.code = code;
        this.type = type;
        this.size = size;
        this.qty = qty;
        this.price = price;
        this.cost = cost;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
