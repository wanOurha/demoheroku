package com.example.demoheroku.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor 
//@AllArgsConstructor
public class ProductRequest {

	// @NotEmpty มาจาก dependency spring-boot-starter-validation
	// @Size size ของ ค่าทั้งหมดต้องยาว 2-100
	@NotEmpty(message = "is Empty")
	@Size(min = 2, max = 100)
	private String name;
	private MultipartFile image;
	private int price;
	private int stock;

}
