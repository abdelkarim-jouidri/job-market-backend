package com.example.jobmarket.Models;

import com.example.jobmarket.Enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Password;
    @Column(unique = true)
    private String email;

    private Role role = Role.RECRUTER;


    @OneToOne
    private Company company;

    @Override
    public String getName() {
        return company.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(Role.RECRUTER).
                stream().
                map(r-> new SimpleGrantedAuthority(r.name())).
                collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.Password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
