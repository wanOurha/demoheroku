package com.example.demoheroku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demoheroku.model.ProductBorrow;

public interface ProductBorrowRepository extends JpaRepository<ProductBorrow, Long> {

	@Query(value = "SELECT * FROM product_borrow WHERE borrow_id = :borrowid", nativeQuery = true)
	ProductBorrow findById(@Param("borrowid") long borrowId);

	@Query(value = "SELECT * FROM product_borrow WHERE user_id = :userId ORDER BY borrow_id DESC  ", nativeQuery = true)
	List<ProductBorrow> findAllBorrowByUserId(@Param("userId") long userId);

	@Query(value = "SELECT * FROM product_borrow WHERE is_return = :isReturn ORDER BY borrow_id DESC  ", nativeQuery = true)
	List<ProductBorrow> findAllBorrowByBorrowStatus(@Param("isReturn") long isReturn);

	@Query(value = "SELECT * FROM product_borrow WHERE is_return NOT IN (2,11)ORDER BY borrow_id DESC  ", nativeQuery = true)
	List<ProductBorrow> findAllBorrowIsNotReturn();

//	@Query(value = "SELECT b.borrow_id,a.product_id,a.name FROM product AS a LEFT OUTER JOIN product_borrow AS b ON a.product_id = b.product_id WHERE b.user_id = :userId ORDER BY b.borrow_id ASC", nativeQuery = true)
//	List<ProductBorrow> findBorrowInfoByUserId(@Param("userId") long userId);
}
