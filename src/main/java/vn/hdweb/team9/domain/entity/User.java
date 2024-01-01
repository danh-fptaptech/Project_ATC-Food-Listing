package vn.hdweb.team9.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name="avatar")
    private String avatar;
    @Column(name="create_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> listOrder = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<RatingFood> listRatingFood = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<RatingRestaurant> listRatingRestaurant = new ArrayList<>();
}
