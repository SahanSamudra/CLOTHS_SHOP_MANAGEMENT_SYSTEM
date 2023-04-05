package tm;

public class CustomerTm {
    private String CustomerId;
    private String CustomerName;
    private String CustomerAddress;
    private String noOfContact;

    public CustomerTm() {
    }

    public CustomerTm(String customerId, String customerName, String customerAddress, String noOfContact) {
        CustomerId = customerId;
        CustomerName = customerName;
        CustomerAddress = customerAddress;
        this.noOfContact = noOfContact;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    public String getNoOfContact() {
        return noOfContact;
    }

    public void setNoOfContact(String noOfContact) {
        this.noOfContact = noOfContact;
    }
}
