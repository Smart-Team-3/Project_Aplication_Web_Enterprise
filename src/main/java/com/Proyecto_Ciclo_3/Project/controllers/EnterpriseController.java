package com.Proyecto_Ciclo_3.Project.controllers;



import com.Proyecto_Ciclo_3.Project.entities.Empresa;
import com.Proyecto_Ciclo_3.Project.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;

    @GetMapping("/viewenterprises")
    public String viewEnterprises(Model model, @ModelAttribute("message") String message){
        List<Empresa> EnterpriseList = enterpriseService.GetAllEnterprises();
        model.addAttribute("enterpriseList",EnterpriseList);
        model.addAttribute("message",message);
        return "viewEnterprises";
    }
    @GetMapping ("/addenterprise")
    public String newEnterprise(Model model, @ModelAttribute("message") String message){
        Empresa enterprise = new Empresa();
        model.addAttribute("enterprise",enterprise);
        model.addAttribute("message",message);
        return "addEnterprise";
    }
    @PostMapping("/saveenterprise")
    public String SaveEnterprise(Empresa enterprise, RedirectAttributes redirectAttributes){
        if(enterpriseService.UpdateOrChangeEnterprise(enterprise)){
            redirectAttributes.addAttribute("message","it was saved");
            return "redirect:/viewenterprises";
        }
        redirectAttributes.addFlashAttribute("message","error, not save");
        return "redirect:/addenterprise";
    }
    @GetMapping("/editenterprise/{id}")
    public String EditEnterprise(Model model, @PathVariable Integer id,@ModelAttribute("message") String message){
        Empresa enterprise = enterpriseService.GetEnterpriseById(id).get();
        model.addAttribute("enterprise",enterprise);
        model.addAttribute("message",message);
        return "editEnterprise";
    }
    @PostMapping("/updateenterprise/{id}")
    public String UpdateEnterprise(@ModelAttribute("enterprise") Empresa enterprise,RedirectAttributes redirectAttributes){
        if(enterpriseService.UpdateOrChangeEnterprise(enterprise)){
            redirectAttributes.addFlashAttribute("message","it was updated");
            return "redirect:/viewenterprises";
        }
        redirectAttributes.addFlashAttribute("message","it was not updated");
        return "redirect:/addenterprise";
    }
    @GetMapping("/deleteenterprise/{id}")
    public String DeleteEnterprise(@PathVariable Integer id,RedirectAttributes redirectAttributes){
        if(enterpriseService.DeleteEnterprise(id)){
            redirectAttributes.addFlashAttribute("message","it was deleted");
            return "redirect:/viewenterprises";
        }
        redirectAttributes.addFlashAttribute("message","it was not deleted");
        return "redirect:/editenterprise";
    }
}


