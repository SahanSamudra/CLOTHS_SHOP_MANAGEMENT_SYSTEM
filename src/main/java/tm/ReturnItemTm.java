package tm;

import java.sql.Date;

public class ReturnItemTm {
    private String id;
    private int qty;
    private String size;
    private String itemId;
    private Date date;
    private double price;

    private Date orderId;

    public ReturnItemTm(String id, int qty, String size, String itemId, Date date, double price, Date orderId) {
        this.id = id;
        this.qty = qty;
        this.size = size;
        this.itemId = itemId;
        this.date = date;
        this.price = price;
        this.orderId = orderId;
    }

    public Date getOrderId() {
        return orderId;
    }

    public void setOrderId(Date orderId) {
        this.orderId = orderId;
    }

    public ReturnItemTm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
