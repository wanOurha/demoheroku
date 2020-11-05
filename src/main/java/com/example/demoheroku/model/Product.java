package com.example.demoheroku.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
//@NoArgsConstructor 
//@RequiredArgsConstructor
//@Entity to make class product to table 
//tablename:product with column:long id,String name,String image,int price,int stock
//@Accessors(chain = true) return setter
@NoArgsConstructor
@Entity
@Accessors(chain = true)
public class Product {
	// obj => db with save(obj)
	// @Id make id to primary key
	// @GeneratedValue(strategy = GenerationType.IDENTITY make generateValue with
	// owner database หรือ database แต่ละค่ายจัดการเอง
	// @Setter(AccessLevel.NONE) ไม่สามารถ obj.setter() ได้

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long product_id;

	@Column(length = 150, nullable = false, unique = false)
	private String name;
	private String image;
	private int price;
	private int stock;

	@Setter(AccessLevel.NONE)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false)
	private Date creteDate;

	@Setter(AccessLevel.NONE)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = false)
	private Date updateDate;

}
