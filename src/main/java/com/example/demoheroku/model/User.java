package com.example.demoheroku.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

//For generate database table
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "`user`")
@Accessors(chain = true)
public class User {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long user_id;

	@Column(nullable = false, unique = true)
	private String username;

	// 12345678 -> qweqwdadwq เข้ารหัส password ให้เจาะได้ยาก
	@Column(nullable = false, unique = true)
	private String password;

	// permission
	@Column(nullable = false)
	private String role;
}
