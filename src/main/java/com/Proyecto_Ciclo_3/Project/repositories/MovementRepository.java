package com.Proyecto_Ciclo_3.Project.repositories;


import com.Proyecto_Ciclo_3.Project.entities.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovementRepository extends JpaRepository <MovimientoDinero,Integer>{

    // METODO PARA FILTRAR MTO CON EL ID DEL USUARIO
    @Query(value="SELECT * FROM movimientos where empleado_id= ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByUser(Integer id);

    // METODO PARA FILTRAR MTO CON EL ID DE LA EMPRESA
    @Query(value = "SELECT * FROM movimientos where empleado_id in (SELECT id where empresa_id= ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEnterprise(Integer id);

}