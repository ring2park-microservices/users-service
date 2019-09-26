package com.microfocus.ring2parkms.users;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Persistent account entity with JPA markup. Accounts are stored in an H2
 * relational database.
 *
 * @author Kevin A. Lee
 */
@Entity
@Table(name = "T_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public static Long nextId = 0L;

    @Id
    protected Long id;

    @Size(min = 4, message = "Username must be at least 4 characters")
    @Pattern(regexp="[a-zA-Z0-9]+$", message = "Username must be alphanumeric with no spaces")
    private String username;

    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(regexp="[a-zA-Z0-9]+$", message = "Password must be alphanumeric with no spaces")
    private String password;

    @Transient
    @Size(min = 6, message = "Confirm password must be at least 6 characters")
    @Pattern(regexp="[a-zA-Z0-9]+$", message = "Password must be alphanumeric with no spaces")
    private String confirmPassword;

    @Size(min = 6, message = "Name must be at least 6 characters")
    @Pattern(regexp="[a-zA-Z ]+$", message = "Name must be alphabetic with spaces")
    private String name;

    @Email(message = "A valid email address is required")
    @Column(unique=true)
    private String email;

    @Size(min = 10, message = "Mobile must be at least 10 characters")
    @Pattern(regexp="[0-9]+$", message = "Mobile must contain numbers only (no spaces)")
    @Column(unique=true)
    private String mobile;

    @NotNull
    private Boolean enabled;

    @Transient
    private Boolean acceptTerms;

    private String verifyCode;

    /**
     * This is a very simple, and non-scalable solution to generating unique
     * ids. Not recommended for a real application. Consider using the
     * <tt>@GeneratedValue</tt> annotation and a sequence to generate ids.
     *
     * @return The next available id.
     */
    protected static Long getNextId() {
        synchronized (nextId) {
            return nextId++;
        }
    }

    /**
     * Default constructor for JPA only.
     */
    protected User() {
        id = getNextId();
        this.setEnabled(false);
        this.setAcceptTerms(false);
    }

    public User(String username, String password, String name, String email) {
        this();
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    /**
     * Set JPA id - for testing and JPA only. Not intended for normal use.
     *
     * @param id
     *            The new id.
     */
    protected void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(Boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public boolean isCurrentUser(String username) {
        if (this.username.equals(username))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "User(" + username + " - " + name + " - " + email + ")";
    }

}

