package by.fastrentcar.model;


public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String passport_number;
    private String passport_data;
    private String passport_authority;

    public User(Long id, String firstName, String lastName, String phone, String email, String passport_number, String passport_data, String passport_authority) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.passport_number = passport_number;
        this.passport_data = passport_data;
        this.passport_authority = passport_authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getPassport_data() {
        return passport_data;
    }

    public void setPassport_data(String passport_data) {
        this.passport_data = passport_data;
    }

    public String getPassport_authority() {
        return passport_authority;
    }

    public void setPassport_authority(String passport_authority) {
        this.passport_authority = passport_authority;
    }

}

