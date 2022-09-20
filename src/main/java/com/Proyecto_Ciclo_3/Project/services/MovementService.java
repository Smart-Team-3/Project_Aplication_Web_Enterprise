package com.Proyecto_Ciclo_3.Project.services;



import com.Proyecto_Ciclo_3.Project.entities.Empleado;
import com.Proyecto_Ciclo_3.Project.entities.MovimientoDinero;
import com.Proyecto_Ciclo_3.Project.repositories.MovementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovementService {
    @Autowired
    MovementRepository movimientosRepository;

    public List<MovimientoDinero> getAllMovimientos(){
        List<MovimientoDinero> movimientosList = new ArrayList<>();
        movimientosRepository.findAll().forEach(movimiento -> movimientosList.add(movimiento));
        return movimientosList;
    }

    public MovimientoDinero getMovimientoById(Integer id){
        return movimientosRepository.findById(id).get();
    }

    public boolean saveOrUpdateMovimiento(MovimientoDinero movimientoDinero){
        MovimientoDinero movem=movimientosRepository.save(movimientoDinero);
        if(movimientosRepository.findById(movem.getId())!= null){
            return true;
        }
        return false;
    }

    public boolean deleteMovimiento(Integer id){
        movimientosRepository.deleteById(id);
        if(this.movimientosRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

   public ArrayList<MovimientoDinero> GetByUser(Integer id) { // OBTENER MTO POR ID DE EMPLEADO
        return movimientosRepository.findByUser(id);
    }
   public ArrayList<MovimientoDinero> GetByEnterprise(Integer id) { //OBTENER MTO POR ID DE EMPRESA REGISTRADA POR LO EMPLEADOS
        return movimientosRepository.findByEnterprise(id);
   }


}


