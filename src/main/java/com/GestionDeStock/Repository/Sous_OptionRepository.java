package com.GestionDeStock.Repository;

import com.GestionDeStock.Entity.SousOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface Sous_OptionRepository extends JpaRepository<SousOption, Integer> {


    @Query("SELECT V FROM SousOption V WHERE V.option.idoption=:optionid")
    List<SousOption> getSousOptionsByOption(@Param("optionid") Integer idoption);

}
