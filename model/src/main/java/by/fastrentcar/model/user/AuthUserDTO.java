package by.fastrentcar.model.user;

public class AuthUserDTO {
    private Long Id;
    private String login;
    private Role role;
    private Long userId;

    public AuthUserDTO(Long id, String login, Role role, Long userId) {
        Id = id;
        this.login = login;
        this.role = role;
        this.userId = userId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
