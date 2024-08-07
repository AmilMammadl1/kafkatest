package com.alas.task1.repository;

import com.alas.task1.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Override
    @EntityGraph(attributePaths = {"category"})
    //@Query("SELECT DISTINCT u FROM product u JOIN FETCH u.properties WHERE u.id = :productId")
    Optional<Product> findById(@Param("productId") Integer userId);

//    @Override
//    List<Product> findAllById(List<Integer> productIds);

}
