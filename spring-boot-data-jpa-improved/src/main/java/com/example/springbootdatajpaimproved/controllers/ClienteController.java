package com.example.springbootdatajpaimproved.controllers;

import com.example.springbootdatajpaimproved.entity.Cliente;
import com.example.springbootdatajpaimproved.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("client")
public class ClienteController {

    @Autowired
    IClienteService clientService;



    @RequestMapping(value="/form", method = RequestMethod.GET)
    public String showForm(Model model){
        model.addAttribute("client",new Cliente());
        model.addAttribute("title","Nuevo Cliente");
        return "form";
    }


    @RequestMapping(value="/form",method = RequestMethod.POST)
    public String saveClient(@Valid @ModelAttribute("client") Cliente cliente, BindingResult result, Model model, SessionStatus status){
        if(result.hasErrors()){
            if(cliente.getId()!=null)
                model.addAttribute("title","Actualizando Cliente");
            else
                model.addAttribute("title","Nuevo Cliente");
            return "form";
        }
        clientService.save(cliente);
        status.setComplete();
        return "redirect:/list";
    }

    @RequestMapping(value="/form/{id}", method = RequestMethod.GET)
    public String updateClient(@PathVariable("id") Long id, Model model){
        model.addAttribute("client",clientService.findOneBy(id));
        model.addAttribute("title","Actualizando Cliente");
        return "form";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteClient(@PathVariable("id") Long id, Map<String,Object> model){
        clientService.deleteOneById(id);
        return "redirect:/list";
    }

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String showAllClients(Map<String,Object> model){
        model.put("lClients", clientService.findAll());
        model.put("title","Listado de Clientes");
        return "listar";
    }
}
