package com.Proyecto_Ciclo_3.Project.controllers;


import com.Proyecto_Ciclo_3.Project.entities.Empleado;
import com.Proyecto_Ciclo_3.Project.entities.Empresa;
import com.Proyecto_Ciclo_3.Project.services.EnterpriseService;
import com.Proyecto_Ciclo_3.Project.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;


@Controller
public class UsersController {
    @Autowired
    UsersService usersService;
    @Autowired
    EnterpriseService enterpriseService;

    @GetMapping("/viewusers")
    public String viewUsers(Model model, @ModelAttribute("message") String message){
        List<Empleado> usersList = usersService.getAllUsers();
        model.addAttribute("userList",usersList);
        model.addAttribute("message",message);
        return "viewUsers";
    }
    @GetMapping ("/adduser")
    public String newUser(Model model, @ModelAttribute("message") String message){
        Empleado user = new Empleado();
        model.addAttribute("user",user);
        model.addAttribute("message",message);
        List<Empresa> EnterpriseList = enterpriseService.GetAllEnterprises();
        model.addAttribute("enterpriseList",EnterpriseList);
        return "addUser";
    }
    @PostMapping("/saveuser")
    public String SaveUser(Empleado user, RedirectAttributes redirectAttributes){
        if(usersService.saveOrUpdateUser(user)){
            redirectAttributes.addAttribute("message","it was saved");
            return "redirect:/viewusers";
        }
        redirectAttributes.addFlashAttribute("message","error, not save");
        return "redirect:/adduser";
    }
    @GetMapping("/edituser/{id}")
    public String EditUser(Model model, @PathVariable Integer id,@ModelAttribute("message") String message){
        Empleado user = usersService.getUserById(id).get();
        model.addAttribute("user",user);
        model.addAttribute("message",message);
        List<Empresa> EnterpriseList = enterpriseService.GetAllEnterprises();
        model.addAttribute("enterpriseList",EnterpriseList);
        return "editUsers";
    }
    @PostMapping("/updateuser")
    public String UpdateUser(@ModelAttribute("user") Empleado user,RedirectAttributes redirectAttributes){
        if(usersService.saveOrUpdateUser(user)){
            redirectAttributes.addFlashAttribute("message","it was updated");
            return "redirect:/viewusers";
        }
        redirectAttributes.addFlashAttribute("message","it was not updated");
        return "redirect:/adduser";
    }
    @GetMapping("/deleteuser/{id}")
    public String DeleteUser(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        if(usersService.DeleteUser(id)){
            redirectAttributes.addFlashAttribute("message","it was deleted");
            return "redirect:/viewusers";
        }
        redirectAttributes.addFlashAttribute("message","it was not deleted");
        return "redirect:/edituser";
    }
    @GetMapping("/enterprise/{id}/users")
    public String ViewUsersByEnterprise(@PathVariable("id") Integer id,Model model){
        List<Empleado> usersList = usersService.getUsersByEnterprise(id);
        model.addAttribute("userList", usersList);
        return "viewUsers";
    }
}
