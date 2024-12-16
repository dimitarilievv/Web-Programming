package mk.finki.ukim.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "booking_users")
public class User{
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
//    @Enumerated(value = EnumType.STRING)
//    private Role role;
//    private boolean isAccountNonExpired=true;
//    private boolean isAccountNonLocked=true;
//    private boolean isCredentialsNonExpired=true;
//    private boolean isEnabled=true;
    public User() {

    }

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(role);
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return isAccountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return isAccountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return isCredentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return isEnabled;
//    }
}
