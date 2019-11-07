package by.fastrentcar.dao.entity;

import by.fastrentcar.model.user.User;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "user")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class UserEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String passport_number;
    private String passport_data;
    private String passport_authority;
    private AuthUserEntity authUserEntity;
    //private List<OrderEntity> orderEntity=new ArrayList<>();

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "userEntity", cascade = CascadeType.ALL)
    public AuthUserEntity getAuthUserEntity() {
        return authUserEntity;
    }

    public void setAuthUserEntity(AuthUserEntity authUserEntity) {
        this.authUserEntity = authUserEntity;
    }

    //public List<OrderEntity> getOrderEntity() {
    //    return orderEntity;
    //}

//    public void setOrderEntity(List<OrderEntity> orderEntity) {
//        this.orderEntity = orderEntity;
//    }

    public UserEntity() {
    }

    public UserEntity(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.passport_number = user.getPassport_number();
        this.passport_data = user.getPassport_data();
        this.passport_authority = user.getPassport_authority();
        this.authUserEntity = null;
        //   this.orderEntity = null;
    }

    public User convertUserbyUserEntity() {
        return new User(this.id,
                this.firstName,
                this.lastName,
                this.phone,
                this.email,
                this.passport_number,
                this.passport_data,
                this.passport_authority);
    }


}

