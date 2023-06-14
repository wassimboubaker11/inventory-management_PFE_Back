package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.Article;
import com.GestionDeStock.Entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant , Integer> {

    List<Variant> findVariantByArticle(Article article);

    @Query("SELECT COALESCE(SUM(v.quantity), 0) FROM Variant v WHERE v.article.idarticle = :articleId")
    int sumQuantityByArticleId(@Param("articleId") int articleId);



}
