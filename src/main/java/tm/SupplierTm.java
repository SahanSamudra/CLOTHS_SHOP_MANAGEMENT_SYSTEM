package tm;

public class SupplierTm {

    private String sid;
    private String nic;
    private String name;
    private String contact;
    private String address;
    private String company;

    public SupplierTm() {
    }

    public SupplierTm(String sid, String nic, String name, String contact, String address, String company) {
        this.sid = sid;
        this.nic = nic;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.setCompany(company);
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
