package com.GestionDeStock.Repository;


import com.GestionDeStock.Entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert , Integer> {


}
