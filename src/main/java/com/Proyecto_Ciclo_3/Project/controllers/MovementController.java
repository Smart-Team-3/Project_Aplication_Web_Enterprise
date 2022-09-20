package com.Proyecto_Ciclo_3.Project.controllers;

import com.Proyecto_Ciclo_3.Project.entities.Empleado;
import com.Proyecto_Ciclo_3.Project.entities.Empresa;
import com.Proyecto_Ciclo_3.Project.entities.MovimientoDinero;
import com.Proyecto_Ciclo_3.Project.services.MovementService;
import com.Proyecto_Ciclo_3.Project.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class MovementController {

    @Autowired
    MovementService movementService;

    @Autowired
    UsersService usersService;



// CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA VISUALIZAR MTO

    @GetMapping("/viewMovement")
    public String viewMovement(Model model, @ModelAttribute("message") String message) {
        List<MovimientoDinero> movementList = movementService.getAllMovimientos();
        model.addAttribute("movList", movementList);
        model.addAttribute("message", message);
        return "viewMovement";
    }
// CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA CREAR UN NUEVO MTO
    @GetMapping ("/addMovement")
    public String newMovemt(Model model, @ModelAttribute("message") String message){
        MovimientoDinero movemnt = new MovimientoDinero();
        model.addAttribute("mov",movemnt);
        model.addAttribute("message",message);
        List<Empleado> UserList= usersService.getAllUsers();
        model.addAttribute("userList",UserList);
        return "addMovement";
    }
    // CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA GUARDAR UN NUEVO MTO
    @PostMapping("/savemovement")
    public String SaveMovement(MovimientoDinero mov, RedirectAttributes redirectAttributes){
        if(movementService.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addAttribute("message","saved");
            return "redirect:/viewMovement";
        }
        redirectAttributes.addFlashAttribute("message","request failed");
        return "redirect:/addMovement";
    }
    // CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA EDITAR UN MTO
    @GetMapping("/editmovement/{id}")
    public String EditMovement(Model model, @PathVariable Integer id,@ModelAttribute("message") String message){
        MovimientoDinero mov= movementService.getMovimientoById(id);
        model.addAttribute("mov", mov);
        model.addAttribute("message",message);
        List<Empleado> UserList= usersService.getAllUsers();
        model.addAttribute("userlist", UserList);
        return "editMovement";
    }
    // CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA ACTUALIZAR UN NUEVO MTO
    @PostMapping("/updatemovement")
    public String UpdateMovement(@ModelAttribute("mov") MovimientoDinero mov,RedirectAttributes redirectAttributes){
        if(movementService.saveOrUpdateMovimiento(mov)){
            redirectAttributes.addFlashAttribute("message","updated");
            return "redirect:/viewMovement";
        }
        redirectAttributes.addFlashAttribute("message","request failed");
        return "redirect:/editMovement/"+mov.getId();
    }
    // CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA ELIMINAR UN NUEVO MTO
    @GetMapping("/deletemovement")
    public String DeleteMovement(@PathVariable Integer id,RedirectAttributes redirectAttributes) {
        if (movementService.deleteMovimiento(id)) {
            redirectAttributes.addFlashAttribute("message", "deleted");
            return "redirect:/viewMovement";
        }
        redirectAttributes.addFlashAttribute("message", "request failed");
        return "redirect:/viewMovement";
    }


}


// MOVIMIENTOS DE DINERO POR ID DE EMPRESA
   // @GetMapping("/enterprises/{id}/movement") // CONSULTA DE MTO POR ID EMPRESA
    //public ArrayList<MovimientoDinero> movimientoDineroPorEmpresa(@PathVariable("id") Integer id){
        //return movementService.obtenerPorEnterprise(id);
    //}

    //@PostMapping("/enterprises/{id}/movement") // AGREGAR NUEVO MTO DE EMPRESA
    //public Optional <MovimientoDinero> guardarMovimientoDinero (@RequestBody MovimientoDinero movimientoDinero) {
        //return Optional.ofNullable(this.movementService.saveOrUpdateMovimiento(movimientoDinero));
    //}

    //@PatchMapping("/enterprises/{id}/movement") // MODIFICAR UN MTO EXISTENTE POR ID EMPRESA
    //public MovimientoDinero actualizarMovimientoDinero(@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimientoDinero){
        //Optional<MovimientoDinero> movimientoDinero1 = Optional.ofNullable(movementService.getMovimientoById(id));
        //MovimientoDinero movement = movimientoDinero1.get();
        //movement.setMonto(movimientoDinero.getMonto());
        //movement.setConcepto(movimientoDinero.getConcepto());
        //movement.setUsuario(movimientoDinero.getUsuario());
        //return movementService.saveOrUpdateMovimiento(movement);
    //}
    //@DeleteMapping("/enterprises/{id}/movement") // ELIMINAR UN MTO POR ID EMPRESA
    //public String DeleteMovimiento (@PathVariable("id") Integer id){
        //boolean rta = this.movementService.deleteMovimiento(id);
        //if (rta) {
            //return "El Mto Dinero ha sido eliminado atravez del id " + id;
        //}
        //return "El Mto Dinero NO ha sido eliminado atravez del id " + id;
        //}


// MOVIMIENTOS DE DINERO POR ID DE EMPLEADOS
    //@GetMapping("/users/{id}/movement") //CONSULTA DE MTO POR ID DE EMPLEADO
    //public ArrayList<MovimientoDinero> movementByUser(@PathVariable("id")Integer id){
        //return movementService.obtenerPorUser(id);
    //}

    //@PostMapping("/users/{id}/movement") // AGREGAR NUEVO MTO POR ID DE EMPLEADO
    //public Optional <MovimientoDinero> guardarMovimientoDinero (@RequestBody MovimientoDinero movimientoDinero) {
        //return Optional.ofNullable(this.movementService.saveOrUpdateMovimiento(movimientoDinero));


    //}



