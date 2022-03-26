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

    public static boolean isValidIc(String newIc) {
        if (newIc.length() != 9) {
            return false;
        }
        if (Character.isDigit(newIc.charAt(0)) || Character.isDigit(newIc.charAt(8))) {
            return false;
        }
        for (int i = 1; i < 7; i++) {
            if (!Character.isDigit(newIc.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidPhone(String num) {
        if (num.length() != 8) {
            return false;
        }
        for (int i = 0; i < 8; i++) {
            if (!Character.isDigit(num.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidEmail(String newEmail) {
        if (newEmail.contains("@")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String str = ic + " / " + name + " / "
                + phone + " / " + email;

        return str;
    }

    public String getCustomerInfo() {
        String info = "IC: " + ic + System.lineSeparator()
                + "Name: " + name + System.lineSeparator()
                + "Phone number: " + phone + System.lineSeparator()
                + "Email address: " + email;
        return info;
    }
}
