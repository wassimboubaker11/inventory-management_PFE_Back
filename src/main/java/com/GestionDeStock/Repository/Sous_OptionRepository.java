package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.SousOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface Sous_OptionRepository extends JpaRepository<SousOption, Integer> {



}
