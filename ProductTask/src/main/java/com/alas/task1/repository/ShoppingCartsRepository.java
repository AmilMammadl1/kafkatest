package com.alas.task1.repository;

import com.alas.task1.model.ShoppingCarts;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShoppingCartsRepository extends JpaRepository<ShoppingCarts,Integer> {
    @Override
    @EntityGraph(attributePaths = {"products"})
    //@Query("SELECT DISTINCT u FROM product u JOIN FETCH u.properties WHERE u.id = :userId")
    Optional<ShoppingCarts> findById( Integer cartId);

}
