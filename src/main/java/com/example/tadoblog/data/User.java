package com.example.tadoblog.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = ("users"))
public class User implements UserDetails {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "varchar(36)", updatable = false)
    @Type(type = "uuid-char")
    private UUID id;

    private String username;
    private String name;
    private String surname;
    private String country;
    private String city;
    private String street;
    private String zipCode;
    private String phoneNumber;
    private String password;

    public User(String username, String name, String surname, String country, String city, String street, String zipCode, String phoneNumber, String password, Set<Role> roles) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
    public String getFullName(){
        return String.format("%s %s",name,surname);
    }
}
