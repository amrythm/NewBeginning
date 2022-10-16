package org.pharmEasy.db.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private int id;
    private String name;
    private String email;
    private String password;
    private char gender;
    private Date dateOfBirth;
    private List<Prescription> prescriptions;

    public MyUserDetails(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.gender = user.getGender();
        this.dateOfBirth = user.getDateOfBirth();
        this.prescriptions = user.getPrescriptions();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
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
