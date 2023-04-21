package model;

import java.util.ArrayList;

public class Order {
    private String oid;
    private String ocid;
    private String odate;
    private String otime;
    private double cost;

    private ArrayList<ItemReports> items;

    public Order() {
    }

    public Order(String oid, String ocid, String odate, String otime, double cost) {
        this.oid = oid;
        this.ocid = ocid;
        this.odate = odate;
        this.otime = otime;
        this.cost = cost;
    }

    public Order(String oid, String ocid, String odate, String otime, double cost, ArrayList<ItemReports> items) {
        this.oid = oid;
        this.ocid = ocid;
        this.odate = odate;
        this.otime = otime;
        this.cost = cost;
        this.items = items;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOcid() {
        return ocid;
    }

    public void setOcid(String ocid) {
        this.ocid = ocid;
    }

    public String getOdate() {
        return odate;
    }

    public void setOdate(String odate) {
        this.odate = odate;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<ItemReports> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemReports> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", ocid='" + ocid + '\'' +
                ", odate='" + odate + '\'' +
                ", otime='" + otime + '\'' +
                ", cost=" + cost +
                ", items=" + items +
                '}';
    }
}
