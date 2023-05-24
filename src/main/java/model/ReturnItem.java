package model;

import java.sql.Date;

public class ReturnItem {
    private String id;
    private int qty;
    private String size;
    private String itemId;

    private Date date;

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ReturnItem() {
    }

    public ReturnItem(String id, int qty, String size, String itemId, Date date, String orderId) {
        this.id = id;
        this.qty = qty;
        this.size = size;
        this.itemId = itemId;
        this.date = date;
        this.orderId = orderId;
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
}
