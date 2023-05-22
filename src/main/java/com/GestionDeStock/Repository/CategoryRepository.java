package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category , Integer> {
}
