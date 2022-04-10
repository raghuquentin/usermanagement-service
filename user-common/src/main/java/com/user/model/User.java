package com.user.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.Hidden;

@Entity
@Table(name = "USER",
		uniqueConstraints = @UniqueConstraint(columnNames = {"USER_EMAIL"}))
public class User implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7040507752608569565L;

	@Id
    @Column(name="USER_EMAIL",unique = true)
    @NotBlank(message = "Email address should not be empty")
    private String emailAddress;

    @Column(name="USER_PWD")
    @NotBlank(message = "Password should not be empty")
    private String password;

    @Column(name="USER_NAME")
    private String userName;
    @Hidden
    @Column(name="USER_LST_LOGIN_DATE")
    private LocalDateTime lastLoginDate;
    
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LocalDateTime getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(LocalDateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
    

}
