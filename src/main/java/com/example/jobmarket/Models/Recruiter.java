package com.example.jobmarket.Models;

import com.example.jobmarket.Enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recruiter implements UserDetails, Principal {

    @Id
    @GeneratedValue
    private Integer id;
    private String Password;
    @Column(unique = true)
    private String email;
    private Set<Role> roles;


    @OneToOne
    private Company company;

    @Override
    public String getName() {
        return company.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.
                stream().
                map(r-> new SimpleGrantedAuthority(r.name())).
                collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return getPassword();
    }

    @Override
    public String getUsername() {
        return null;
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
}
