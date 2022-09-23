package com.Proyecto_Ciclo_3.Project.services;



import com.Proyecto_Ciclo_3.Project.entities.MovimientoDinero;
import com.Proyecto_Ciclo_3.Project.repositories.MovementRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovementService {
    @Autowired
    MovementRepository movementRepository;

    public List<MovimientoDinero> GetAllMovements(){
        List<MovimientoDinero> movimientosList = new ArrayList<>();
        movementRepository.findAll().forEach(movimiento -> movimientosList.add(movimiento));
        return movimientosList;
    }

    public Optional<MovimientoDinero> GetMovementsById(Integer id){
        return movementRepository.findById(id);
    }

    public boolean SaveOrUpdateMovement(MovimientoDinero movimientoDinero){
        MovimientoDinero mov= movementRepository.save(movimientoDinero);
        if(movementRepository.findById(mov.getId()).isPresent()){
            return true;
        }
        return false;
    }

    public boolean DeleteMovement(Integer id){
        movementRepository.deleteById(id);
        if(this.movementRepository.findById(id).isPresent()){
            return false;
        }
        return true;
    }

   public ArrayList<MovimientoDinero> GetByUser(Integer id) { // OBTENER MTO POR ID DE EMPLEADO

        return movementRepository.findByUser(id);
    }
   public ArrayList<MovimientoDinero> GetByEnterprise(Integer id) { //OBTENER MTO POR ID DE EMPRESA REGISTRADA POR LO EMPLEADOS

        return movementRepository.findByEnterprise(id);
   }
   public Long GetTotalMoney(){
        return movementRepository.sumMonto();
   }
   public Long GetMoneyFromEnterprise(Integer id){
        return movementRepository.GetMoneyByEnterprise(id);
   }
   public Long GetMoneyFromUsers(Integer id){
        return movementRepository.GetMoneyByUser(id);
   }

}


