package arcs.data.staff;

public class Staff {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String job;

    public Staff(String id, String password, String name, String job, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.job = job;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public static boolean isValidId(String newId) {
        if (newId.length() != 3) {
            return false;
        }
        if (Character.isDigit(newId.charAt(0))) {
            return false;
        }
        for (int i = 1; i < 3; i++) {
            if (!Character.isDigit(newId.charAt(i))) {
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
        String str = id + " / " + name + " / " + job + " / "
                + phone + " / " + email;

        return str;
    }

    public String getStaffInfo() {
        String info = "ID: " + id + System.lineSeparator()
                + "Name: " + name + System.lineSeparator()
                + "job: " + name + System.lineSeparator()
                + "Phone number: " + phone + System.lineSeparator()
                + "Email address: " + email;
        return info;
    }
}
