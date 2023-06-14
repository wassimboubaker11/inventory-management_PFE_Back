package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article , Integer> {

    @Query("SELECT a FROM Article a JOIN a.depot d WHERE d.iddepot = :depotId")
    List<Article> findByDepotId(@Param("depotId") int depotId);


    @Query("SELECT a FROM Article a WHERE a.alert IS NULL")
    List<Article> findAllArticlesWithoutAlert();

    @Query("SELECT a FROM Article a JOIN a.variants v WHERE v.idvariant = :variantId")
    Article findArticleByVariantId(int variantId);


    @Query("SELECT a FROM Article a WHERE a.code_barre = :codeBare")
    Article findByCodeBare(@Param("codeBare") String codeBare);
}
