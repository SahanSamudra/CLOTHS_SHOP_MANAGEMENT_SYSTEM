package model;

public class ReturnItem {

    private String returnid;
    private String customeridR;
    private String itemcodeR;
    private String returnreason;
    private String billingdateR;
    private String returndate;
    private String warrantydueR;

    public ReturnItem() {
    }

    public ReturnItem(String returnid, String customeridR, String itemcodeR, String returnreason, String billingdateR, String returndate, String warrantydueR) {
        this.returnid = returnid;
        this.customeridR = customeridR;
        this.itemcodeR = itemcodeR;
        this.returnreason = returnreason;
        this.billingdateR = billingdateR;
        this.returndate = returndate;
        this.warrantydueR = warrantydueR;
    }

    public String getReturnid() {
        return returnid;
    }

    public void setReturnid(String returnid) {
        this.returnid = returnid;
    }

    public String getCustomeridR() {
        return customeridR;
    }

    public void setCustomeridR(String customeridR) {
        this.customeridR = customeridR;
    }

    public String getItemcodeR() {
        return itemcodeR;
    }

    public void setItemcodeR(String itemcodeR) {
        this.itemcodeR = itemcodeR;
    }

    public String getReturnreason() {
        return returnreason;
    }

    public void setReturnreason(String returnreason) {
        this.returnreason = returnreason;
    }

    public String getBillingdateR() {
        return billingdateR;
    }

    public void setBillingdateR(String billingdateR) {
        this.billingdateR = billingdateR;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public String getWarrantydueR() {
        return warrantydueR;
    }

    public void setWarrantydueR(String warrantydueR) {
        this.warrantydueR = warrantydueR;
    }
}
