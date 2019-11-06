package by.fastrentcar.dao.entity;

import by.fastrentcar.model.user.AuthUser;
import by.fastrentcar.model.user.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auth_user")
public class AuthUserEntity {
    private Long Id;
    private String login;
    private String password;
    private Role role;
    private UserEntity userEntity;
    private Long user_id;
    private List<OrderEntity> orderEntity = new ArrayList<>();

    @OneToMany(mappedBy = "authUserEntity")
    public List<OrderEntity> getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(List<OrderEntity> orderEntity) {
        this.orderEntity = orderEntity;
    }


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "user_id", insertable = false, updatable = false)
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public AuthUserEntity() {
    }

    public AuthUserEntity(AuthUser authUser, UserEntity userEntity) {
        this.Id = authUser.getId();
        this.login = authUser.getLogin();
        this.password = authUser.getPassword();
        this.role = authUser.getRole();
        this.user_id = authUser.getUserId();
        this.userEntity = userEntity;
    }

    public AuthUser convertAuthUserByAuthUserEntity() {

        return new AuthUser(this.Id,
                this.login,
                this.password,
                this.role,
                this.user_id
        );
    }
}
