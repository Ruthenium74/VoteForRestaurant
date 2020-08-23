package ru.ruthenium74.voteforrestaurant.model;

import java.util.Date;
import java.util.Set;

public class User extends AbstractNamedEntity {
    private Set<Role> roles;

    private Date registered = new Date();

    private String email;

    private String password;

    private boolean enabled;

    public User(Integer id, String name, boolean enabled, String email, String password, Set<Role> roles) {
        super(id, name);
        this.roles = roles;
        this.enabled = enabled;
        this.email = email;
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registered=" + registered +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}
