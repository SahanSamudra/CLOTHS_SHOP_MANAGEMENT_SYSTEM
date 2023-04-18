package tm;

public class EmployeeTm {
    private String empId;
    private String name;
    private String NIC;
    private String address;
    private double salary;
    private String contact;


    public EmployeeTm() {
    }

    public EmployeeTm(String empId, String name, String NIC, String address, double salary, String contact) {
        this.empId = empId;
        this.name = name;
        this.NIC = NIC;
        this.address = address;
        this.salary = salary;
        this.contact = contact;
    }


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    @Override
    public String toString() {
        return "EmployeeTm{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", NIC='" + NIC + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", contact='" + contact + '\'' +
                '}';
    }
}
