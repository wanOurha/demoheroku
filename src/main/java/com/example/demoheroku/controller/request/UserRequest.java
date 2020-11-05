package com.example.demoheroku.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

// request class สำหรับสร้างรูปแบบ request body หรือ JSON ที่จะรับ และ กำหนด exception ได้ 
@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor 
//@AllArgsConstructor
//@Accessors(chain = true) setter แบบ ต่อเนื่อง และ return this
@Accessors(chain = true)
public class UserRequest {

	@NotEmpty
	@Size(min = 1, max = 20)
	private String username;
	@NotEmpty
	@Length(min = 8, message = "The field must be least {min} character")
	private String password;
	@NotEmpty
	private String role;

}
