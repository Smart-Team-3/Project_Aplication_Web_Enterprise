package com.Proyecto_Ciclo_3.Project.controllers;

import com.Proyecto_Ciclo_3.Project.entities.Empleado;
import com.Proyecto_Ciclo_3.Project.entities.MovimientoDinero;
import com.Proyecto_Ciclo_3.Project.services.MovementService;
import com.Proyecto_Ciclo_3.Project.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovementController {
    @Autowired
    MovementService movementService;
    @Autowired
    UsersService usersService;

// CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA VISUALIZAR MTO

    @GetMapping("/viewmovement")
    public String viewMovement(Model model, @ModelAttribute("message") String message) {
        List<MovimientoDinero> movementList = movementService.GetAllMovements();
        model.addAttribute("movList", movementList);
        model.addAttribute("message", message);
        return "viewMovement";
    }
// CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA CREAR UN NUEVO MTO
    @GetMapping ("/addmovement")
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
        if(movementService.SaveOrUpdateMovement(mov)){
            redirectAttributes.addAttribute("message","saved");
            return "redirect:/viewMovement";
        }
        redirectAttributes.addFlashAttribute("message","request failed");
        return "redirect:/addMovement";
    }
    // CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA EDITAR UN MTO
    @GetMapping("/editmovement/{id}")
    public String EditMovement(Model model, @PathVariable Integer id,@ModelAttribute("message") String message){
        MovimientoDinero mov= movementService.GetMovementsById(id);
        model.addAttribute("mov", mov);
        model.addAttribute("message",message);
        List<Empleado> UserList= usersService.getAllUsers();
        model.addAttribute("userlist", UserList);
        return "editMovement";
    }
    // CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA ACTUALIZAR UN NUEVO MTO
    @PostMapping("/updatemovement")
    public String UpdateMovement(@ModelAttribute("mov") MovimientoDinero mov,RedirectAttributes redirectAttributes){
        if(movementService.SaveOrUpdateMovement(mov)){
            redirectAttributes.addFlashAttribute("message","updated");
            return "redirect:/viewMovement";
        }
        redirectAttributes.addFlashAttribute("message","request failed");
        return "redirect:/editMovement/"+mov.getId();
    }
    // CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA ELIMINAR UN NUEVO MTO
    @GetMapping("/deletemovement")
    public String DeleteMovement(@PathVariable Integer id,RedirectAttributes redirectAttributes) {
        if (movementService.DeleteMovement(id)) {
            redirectAttributes.addFlashAttribute("message", "deleted");
            return "redirect:/viewMovement";
        }
        redirectAttributes.addFlashAttribute("message", "request failed");
        return "redirect:/viewMovement";
    }
}




