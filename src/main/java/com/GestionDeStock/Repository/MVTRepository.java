package com.GestionDeStock.Repository;


import com.GestionDeStock.Entity.MVT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MVTRepository extends JpaRepository<MVT , Integer> {
}
