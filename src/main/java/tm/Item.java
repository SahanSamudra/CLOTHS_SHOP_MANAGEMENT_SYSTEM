package tm;


public class Item {
    private  String id;
    private String type;
    private double price;
    private  int qty;
    private String size;
    private String supplier;

    public Item(String iid, String type, String price, String qty, String size, String supplier) {
    }

    public Item(String id, String type, Double price, int qty, String size, String supplier) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.qty = qty;
        this.size = size;
        this.supplier = supplier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setPrice(Double price) {
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

    @Override
    public String toString() {
        return "item{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", size='" + size + '\'' +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}
