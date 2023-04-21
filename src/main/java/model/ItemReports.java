package model;

public class ItemReports {


    private String itemCode;
    private double unitprice;
    private int itemcount;
    private double total;
    private String date;

    public ItemReports() {
    }

    public ItemReports( String itemCode, double unitprice, int itemcount, double total, String date) {

        this.itemCode = itemCode;
        this.unitprice = unitprice;
        this.itemcount = itemcount;
        this.total = total;
        this.date = date;
    }


    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
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

    @Override
    public String toString() {
        return "ItemReports{" +

                ", itemCode='" + itemCode + '\'' +
                ", unitprice=" + unitprice +
                ", itemcount=" + itemcount +
                ", total=" + total +

                '}';
    }
}
