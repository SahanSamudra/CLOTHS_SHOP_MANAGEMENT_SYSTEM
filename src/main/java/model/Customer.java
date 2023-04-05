package model;

public class Customer {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String noOfContact;

    public Customer() {
    }

    public Customer(String customerId, String customerName, String customerAddress, String noOfContact) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.noOfContact = noOfContact;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getNoOfContact() {
        return noOfContact;
    }

    public void setNoOfContact(String noOfContact) {
        this.noOfContact = noOfContact;
    }
}
