package model;

public class Supplier {

    private String sid;
    private String nic;
    private String name;
    private String contact;
    private String address;
    private String company;

    public Supplier(String string, String rstString, String s, String string1, String rstString1) {
    }

    public Supplier(String sid, String snic, String name, String contact, String address, String company) {
        this.sid = sid;
        this.nic = snic;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.company = company;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSnic() {
        return nic;
    }

    public void setSnic(String snic) {
        this.nic = snic;
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

   /* sid VARCHAR(10) not null,
    snic VARCHAR (45),
    sname VARCHAR(45),
    scontact VARCHAR(12),
    saddress VARCHAR (75),*/
}
