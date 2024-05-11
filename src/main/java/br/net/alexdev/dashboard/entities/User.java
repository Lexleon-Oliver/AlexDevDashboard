package br.net.alexdev.dashboard.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "tb_usuarios",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;


    @NotBlank
    @Size(max = 50)
    private String name;


    @Size(max = 30)
    private String company;

    @NotBlank
    @Size(max = 30)
    private String jobTitle;

    @Size(max = 1000)
    private String about;

    @Size(max = 100)
    private String address;

    @Size(max = 30)
    private String city;

    @Size(max = 15)
    private String phone;

    private boolean hasNewNotifications;

    private boolean isActive;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "tb_roles_usuarios",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String username, String encode) {
        this.username = username;
        this.password = encode;
    }
}
