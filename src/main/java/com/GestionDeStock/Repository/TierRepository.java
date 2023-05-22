package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.Tier;
import com.GestionDeStock.Entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TierRepository extends JpaRepository<Tier, Integer > {
    List<Tier> findAllByType(Type type);

}
