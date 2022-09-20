package com.Proyecto_Ciclo_3.Project.services;


import com.Proyecto_Ciclo_3.Project.entities.Empresa;
import com.Proyecto_Ciclo_3.Project.repositories.EnterpriseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnterpriseService {
    @Autowired
    EnterpriseRepository enterpriseRepository;

    public List<Empresa> GetAllEnterprises(){
        List<Empresa> EnterpriseList = new ArrayList<>();
        enterpriseRepository.findAll().forEach(enterprise -> EnterpriseList.add(enterprise));
        return EnterpriseList;

    }
    public Optional<Empresa> GetEnterpriseById(Integer id){

        return enterpriseRepository.findById(id);
    }
    //metodo para definir o actulizar el nombre de la empresa
    public boolean UpdateOrChangeEnterprise(Empresa empresa){
        Empresa emp = enterpriseRepository.save(empresa);
        return enterpriseRepository.findById(emp.getId()).isPresent();
    }
    public boolean DeleteEnterprise(Integer id){
        if(GetEnterpriseById(id).isPresent()){
            enterpriseRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
