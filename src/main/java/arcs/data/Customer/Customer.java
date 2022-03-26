package arcs.data.Customer;

public class Customer {
    private String ic;
    private String name;
    private String phone;
    private String email;

    public Customer(String ic, String name, String phone, String email) {
        this.ic = ic;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getIc() {
        return ic;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
