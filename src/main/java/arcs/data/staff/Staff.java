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

    /**
     * Checks if the input Id is valid.
     * A valid Id should have 3 characters.
     * The first character is non-digit and the last two are digits
     * @param newId the input Id
     * @return true if the Id is valid.
     */
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

    /**
     * Checks if the input phone number is valid.
     * A valid IC should have 8 characters.
     * All the characters should be digits
     * @param num the input phone number string
     * @return true if the phone number is valid.
     */
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

    /**
     * Checks if the input email address is valid.
     * The email address must contain "@" character
     * @param newEmail the input email address string
     * @return true if the email is valid.
     */
    public static boolean isValidEmail(String newEmail) {
        if (newEmail.contains("@")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * print staff object and its attributes excluding passward for security purpose.
     */
    @Override
    public String toString() {
        String str = id + " / " + name + " / " + job + " / "
                + phone + " / " + email;

        return str;
    }

    /**
     * print staff object and its attributes including passward.
     * used only for store app data
     */
    public String toStringStore() {
        String str = id + " / " + password + " / " + name + " / " + job + " / "
                + phone + " / " + email;

        return str;
    }

    /**
     * Get information of the staff
     * Information including id number, password, name, job, phone number and email.
     * @return a string describing staff's information.
     */
    public String getStaffInfo() {
        String info = "ID: " + id + System.lineSeparator()
                + "Name: " + name + System.lineSeparator()
                + "job: " + job + System.lineSeparator()
                + "Phone number: " + phone + System.lineSeparator()
                + "Email address: " + email;
        return info;
    }
}
