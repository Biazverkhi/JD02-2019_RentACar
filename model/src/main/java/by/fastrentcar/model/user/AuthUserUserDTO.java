package by.fastrentcar.model.user;

public class AuthUserUserDTO {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private Long user_id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String passport_number;
    private String passport_data;
    private String passport_authority;

    public AuthUserUserDTO(Long id, String login, String password, Role role, Long userId, String firstName, String lastName, String phone, String email, String passport_number, String passport_data, String passport_authority) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.user_id = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.passport_number = passport_number;
        this.passport_data = passport_data;
        this.passport_authority = passport_authority;
    }

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long userId) {
        this.user_id = userId;
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
