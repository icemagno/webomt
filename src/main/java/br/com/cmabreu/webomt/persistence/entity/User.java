package br.com.cmabreu.webomt.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import br.com.cmabreu.webomt.misc.UserType;


@Entity
@Table(name="users", indexes = {
	       @Index(columnList = "id_user", name = "user_hndx"),
	       @Index(columnList = "loginname,password", name = "user_login_hndx")
	}) 
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user")
	private int idUser;
	
	@Column(length=250)
	private String fullName;

	@Column(length=100)
	private String userMail;
	
	@Column(length=50)
	private String loginName;
	
	@Column(length=40)
	private String password;

	@Column(length=15)
	@Enumerated(EnumType.STRING)
	private UserType type;
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	
}
