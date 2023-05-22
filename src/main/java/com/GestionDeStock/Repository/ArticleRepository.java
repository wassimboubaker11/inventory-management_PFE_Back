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
}