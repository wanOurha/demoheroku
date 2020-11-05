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
public class ProductBorrow {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long borrow_id;

	@Column(length = 150, nullable = false, unique = false)
	private long user_id;
	private long product_id;
	private long borrow_amount;
	private long is_return;

	@Setter(AccessLevel.NONE)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "borrow_date", nullable = false)
	private Date borrowDate;

	@Setter(AccessLevel.NONE)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "return_date", nullable = false)
	private Date returnDate;

}
