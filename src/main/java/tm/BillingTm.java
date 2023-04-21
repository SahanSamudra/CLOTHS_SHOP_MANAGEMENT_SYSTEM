package tm;

public class BillingTm {

    private String ItemCode;
    private String ItemType;
    private String BrandName;
    private String BrandModel;
    private String IMEI;
    private String WarrantyDue;
    private String Description;
    private int Qty;
    private double UnitPrice;
    private double Cost;

    public BillingTm() {
    }

    public BillingTm(String itemCode, String itemType, String brandName, String brandModel, String IMEI, String warrantyDue, String description, int qty, double unitPrice, double cost) {
        ItemCode = itemCode;
        ItemType = itemType;
        BrandName = brandName;
        BrandModel = brandModel;
        this.IMEI = IMEI;
        WarrantyDue = warrantyDue;
        Description = description;
        Qty = qty;
        UnitPrice = unitPrice;
        Cost = cost;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getBrandModel() {
        return BrandModel;
    }

    public void setBrandModel(String brandModel) {
        BrandModel = brandModel;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getWarrantyDue() {
        return WarrantyDue;
    }

    public void setWarrantyDue(String warrantyDue) {
        WarrantyDue = warrantyDue;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getCost() {
        return Cost;
    }

    public void setCost(double cost) {
        Cost = cost;
    }

    @Override
    public String toString() {
        return "BillingTm{" +
                "ItemCode='" + ItemCode + '\'' +
                ", ItemType='" + ItemType + '\'' +
                ", BrandName='" + BrandName + '\'' +
                ", BrandModel='" + BrandModel + '\'' +
                ", IMEI='" + IMEI + '\'' +
                ", WarrantyDue='" + WarrantyDue + '\'' +
                ", Description='" + Description + '\'' +
                ", Qty=" + Qty +
                ", UnitPrice=" + UnitPrice +
                ", Cost=" + Cost +
                '}';
    }
}
