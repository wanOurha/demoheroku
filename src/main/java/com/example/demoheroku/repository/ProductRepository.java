package com.example.demoheroku.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demoheroku.model.Product;

// DAO, locate to CRUD command from JpcRepository
public interface ProductRepository extends JpaRepository<Product, Long> {
	// JpaRepository function
	// SELECT* FROM Product WHERE name = "name"
	Optional<Product> findByName(String name);

	// SELECT* FROM Product WHERE name = "name" LIMIT 1
	Optional<Product> findTopByName(String name);

	// SELECT* FROM Product WHERE name LIKE "%name"
	Optional<Product> findByNameContaining(String name);

	// SELECT* FROM Product WHERE name LIKE "%name" AND stock > {stock}
	List<Product> findByNameContainingAndStockGreaterThan(String name, int stock);

	// SELECT* FROM Product WHERE name LIKE "%name" AND stock > {stock} ORDER BY
	// stock DESC
	List<Product> findByNameContainingAndStockGreaterThanOrderByStockDesc(String name, int stock);

	// SQL command
	// JPQL default: @Query("SELECT p FROM Product WHERE STOCK = 0")
	@Query(value = "SELECT * FROM Product WHERE STOCK = 0", nativeQuery = true)
	List<Product> checkOutOfStock();

	// sql string not single code
	@Query(value = "SELECT * FROM Product WHERE name LIKE %:product_name% AND price > :price ", nativeQuery = true)
	List<Product> searchNameAndPrice(@Param("product_name") String name, int price);

	@Modifying
	@Transactional
	@Query(value = "UPDATE product SET stock = stock - :borrowAmount WHERE product_id=:productId", nativeQuery = true)
	int updateProductStockByIDBorrow(@Param("productId") int productId, @Param("borrowAmount") int amount);

	@Modifying
	@Transactional
	@Query(value = "UPDATE product SET stock = stock + :borrowAmount WHERE product_id=:productId", nativeQuery = true)
	int updateProductStockByIDReturn(@Param("productId") int productId, @Param("borrowAmount") int amount);

}
