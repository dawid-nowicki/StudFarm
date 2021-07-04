package com.company.StudFarm.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "user_gen", sequenceName = "userseq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    private int id;
    private String name;
    private String surname;
    private String password;
    private String role;
    private String email;
    private boolean isEnabled;

    public User(String name, String surname, String password, String role, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.role = role;
        this.email = email;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
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
        return isEnabled;
    }
}
