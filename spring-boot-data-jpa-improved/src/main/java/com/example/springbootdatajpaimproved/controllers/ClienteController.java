package com.example.springbootdatajpaimproved.controllers;

import com.example.springbootdatajpaimproved.entity.Cliente;
import com.example.springbootdatajpaimproved.service.IClienteService;
import com.example.springbootdatajpaimproved.util.paginator.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String saveClient(@Valid @ModelAttribute("client") Cliente cliente, BindingResult result, Model model, SessionStatus status,
                             RedirectAttributes redirectAttrs){
        if(result.hasErrors()){
            if(cliente.getId()!=null) {
                model.addAttribute("title", "Actualizando Cliente");
            }else {
                model.addAttribute("title", "Nuevo Cliente");
            }
            return "form";
        }
        redirectAttrs
                .addFlashAttribute("mensaje", "Cliente creado/acutalizado correctamente")
                .addFlashAttribute("clase", "success");
        clientService.save(cliente);
        status.setComplete();
        return "redirect:/list";
    }

    @RequestMapping(value="/form/{id}", method = RequestMethod.GET)
    public String updateClient(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttrs){
        Cliente c = clientService.findOneBy(id);
        if(c!=null) {
            model.addAttribute("client", clientService.findOneBy(id));
            model.addAttribute("title", "Actualizando Cliente");

            return "form";
        }else{
            redirectAttrs
                    .addFlashAttribute("mensaje", "Cliente no enconrado")
                    .addFlashAttribute("clase", "warning");
            model.addAttribute("error1","Client do not find");
            return "redirect:/list";
        }

    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteClient(@PathVariable("id") Long id){
        clientService.deleteOneById(id);
        return "redirect:/list";
    }

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String showAllClients(Map<String,Object> model){
        model.put("lClients", clientService.findAll());
        model.put("title","Listado de Clientes");
        return "listar";
    }

    @RequestMapping(value="/list_pageable",method = RequestMethod.GET)
    public String showAllClientsPageable(@RequestParam(name="page", defaultValue ="0") int page, Map<String,Object> model){
        //4 registros por pagina,
        Pageable pageRequest = PageRequest.of(page,4);
        //Obtenemos la lista paginda
        Page<Cliente> clientes = clientService.findAll(pageRequest);
        //Generamos el paginador
        PageRender<Cliente> pageRender = new PageRender<Cliente>("/list_pageable",clientes);

        model.put("lClients",clientes );
        model.put("title","Listado de Clientes");
        model.put("page",pageRender);
        return "listar_pageable";
    }
}
