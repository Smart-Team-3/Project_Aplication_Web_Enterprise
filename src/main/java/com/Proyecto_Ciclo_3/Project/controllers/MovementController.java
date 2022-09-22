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
        model.addAttribute("movementList", movementList);
        model.addAttribute("message", message);
        Long sumMonto = movementService.GetTotalMoney();
        model.addAttribute("sumaMontos",sumMonto);
        return "viewMovement";
    }
// CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA CREAR UN NUEVO MTO
    @GetMapping ("/addmovement")
    public String newMovemt(Model model, @ModelAttribute("message") String message){
        MovimientoDinero movement = new MovimientoDinero();
        model.addAttribute("movement",movement);
        model.addAttribute("message",message);
        List<Empleado> UserList= usersService.getAllUsers();
        model.addAttribute("userList",UserList);
        return "addMovement";
    }
    // CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA GUARDAR UN NUEVO MTO
    @PostMapping("/savemovement")
    public String SaveMovement(MovimientoDinero movement, RedirectAttributes redirectAttributes){
        if(movementService.SaveOrUpdateMovement(movement)){
            redirectAttributes.addAttribute("message","it was saved");
            return "redirect:/viewmovement";
        }
        redirectAttributes.addFlashAttribute("message","it was not saved");
        return "redirect:/addmovement";
    }
    // CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA EDITAR UN MTO
    @GetMapping("/editmovement/{id}")
    public String EditMovement(Model model, @PathVariable("id") Integer id,@ModelAttribute("message") String message){
        MovimientoDinero movement= movementService.GetMovementsById(id).get();
        model.addAttribute("movement", movement);
        model.addAttribute("message",message);
        List<Empleado> UserList= usersService.getAllUsers();
        model.addAttribute("userlist", UserList);
        return "editMovement";
    }
    // CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA ACTUALIZAR UN NUEVO MTO
    @PostMapping("/updatemovement")
    public String UpdateMovement(@ModelAttribute("movement") MovimientoDinero movement,RedirectAttributes redirectAttributes){
        if(movementService.SaveOrUpdateMovement(movement)){
            redirectAttributes.addFlashAttribute("message","it was updated");
            return "redirect:/viewmovement";
        }
        redirectAttributes.addFlashAttribute("message","it was not updated");
        return "redirect:/editmovement/"+movement.getId();
    }
    // CONTROLADOR QUE NOS REDIRECCIONA EL TEMPLATE PARA ELIMINAR UN NUEVO MTO
    @GetMapping("/deletemovement")
    public String DeleteMovement(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
        if (movementService.DeleteMovement(id)) {
            redirectAttributes.addFlashAttribute("message", "it was deleted");
            return "redirect:/viewmovement";
        }
        redirectAttributes.addFlashAttribute("message", "it was not deleted");
        return "redirect:/viewmovement";
    }
    @GetMapping("/users/{id}/movements")
    public String GetMovementsByUsers(@PathVariable("id") Integer id,Model model){
        List<MovimientoDinero> usersList = movementService.GetByUser(id);
        model.addAttribute("usersList",usersList);
        return "viewMovement";
    }
    @GetMapping("/enterprise/{id}/movements")
    public String GetMovementByEnterprise(@PathVariable("id") Integer id,Model model){
        List<MovimientoDinero> movementList = movementService.GetByEnterprise(id);
        model.addAttribute("movementList",movementList);
        return "viewMovement";
    }
}




