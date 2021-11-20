package com.degree.GraduateWork.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String post;

    @ManyToMany(fetch = FetchType.EAGER)
    @NotNull
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    @Transient
    private Set<Request> requests;

    public User(String name, String lastName, String email, String password, String post) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.post = post;
    }


    public void setRoles(String roles) {
        this.roles = new HashSet<>();
        if (roles.contains("ADMIN")) {
            this.roles.add(new Role("ADMIN"));
        }
        if (roles.contains("USER")) {
            this.roles.add(new Role("USER"));
        }
        if (roles.contains("SUPERVISOR")) {
            this.roles.add(new Role("SUPERVISOR"));
        }
    }

    //Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Имя Фамилия: " + name + " " + lastName + "\n" + "Отдел: " + post;
    }
}
