package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.Article;
import com.GestionDeStock.Entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant , Integer> {

    List<Variant> findVariantByArticle(Article article);
}
