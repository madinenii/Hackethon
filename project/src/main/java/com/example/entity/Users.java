package com.example.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Idea> ideas = new ArrayList();

	@Column(name = "reset_token")
	private String resetToken;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "otp")
	private String otp;

	@OneToOne(mappedBy = "user")
	private Team team;

	public Users() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;

	}

	public void setUsername(String username) {
		this.username = username;

	}

	public String getPassword() {
		return password;

	}

	public void setPassword(String password) {
		this.password = password;

	}

	public String getRole() {
		return role;

	}

	public void setRole(String role) {
		this.role = role;

	}

	public String getEmail() {
		return email;

	}

	public void setEmail(String email) {
		this.email = email;

	}

	public String getFirstName() {
		return firstName;

	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;

	}

	public void setLastName(String lastName) {
		this.lastName = lastName;

	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;

	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;

	}

	public String generateUsername() {
		String username = getFirstName().toLowerCase() + getDateOfBirth().getYear();
		return username;

	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String generateOtp() {
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);

	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public void generateResetToken() {
		Random random = new Random();
		int token = 100000 + random.nextInt(900000);
		this.resetToken = String.valueOf(token);
	}

	public List<Idea> getIdeas() {
		return ideas;
	}

	public void setIdeas(List<Idea> ideas) {
		this.ideas = ideas;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	

}
