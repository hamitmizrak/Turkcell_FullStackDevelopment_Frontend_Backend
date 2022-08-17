package com.hamitmizrak.data.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

//Lombok
@Data
@Builder

//Entity
@Entity
@Table(name="register")
public class UserRegisterEntity implements Serializable {
    public static final Long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_register_id")
    private Long userRegisterId;

    private String name;
    private String surname;

    @Column(unique = true)
    private String email;
    private String password;
    private String picture;
    private boolean isLogin;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    //Relation : Register burası Eager
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    //burarada UserRegisterEntity ve RoleRegisterEntity id bağlama işlemini yaptım
    //inverseJoinColumns ==> karşımızdaki RoleRegisterEntity @Column name verdik
    @JoinTable(joinColumns =@JoinColumn(name="user_register_id") ,inverseJoinColumns = @JoinColumn(name="roles_id"))
    private Collection<RoleRegisterEntity> relationRoleRegisterEntities;

    //parametresiz constructor:
    public UserRegisterEntity(){
    }

    //parametreli constructor:  Dikkat RoleRegisterEntity ekledim
    public UserRegisterEntity(Long userRegisterId, String name, String surname, String email, String password, String picture, boolean isLogin, Date createdDate, Collection<RoleRegisterEntity> relationRoleRegisterEntities) {
        this.userRegisterId = userRegisterId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.isLogin = isLogin;
        this.createdDate = createdDate;
        this.relationRoleRegisterEntities = relationRoleRegisterEntities;
    }
}
