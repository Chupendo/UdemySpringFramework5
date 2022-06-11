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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public String saveClient(@Valid @ModelAttribute("client") Cliente cliente, BindingResult result,@RequestParam("file") MultipartFile file, Model model, SessionStatus status,
                             RedirectAttributes redirectAttrs){
        if(result.hasErrors()){
            if(cliente.getId()!=null) {
                model.addAttribute("title", "Actualizando Cliente");
            }else {
                model.addAttribute("title", "Nuevo Cliente");
            }
            return "form";
        }
        if(!file.isEmpty()){
            //Obtenemos el direcotrio de los recursos
            Path directorioRecursos = Paths.get("src//main//resources//static//uploads");
            StringBuilder rootPath = new StringBuilder(directorioRecursos.toAbsolutePath().toString());
            try {
                byte[] bytes = file.getBytes();
                //Comprobamos si exsite el direcotior de recursos del cliente
                String pathDirecotrioRecursosCliente = rootPath.append("//"+cliente.getId()+cliente.normalizeString(cliente.getNombre())).toString();
                System.out.println(pathDirecotrioRecursosCliente);
                File directorioRecursosCliente = new File(pathDirecotrioRecursosCliente);
                if (!directorioRecursosCliente.exists()) {
                    //Si no existe lo creamos
                    if (directorioRecursosCliente.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        System.out.println("Error al crear directorio");
                    }
                }

                //Si se creó o existe correctamente se guarda el fichero
                if(directorioRecursosCliente.exists()){
                    rootPath.append("//"+file.getOriginalFilename());
                    Path rutaCompleta = Paths.get(rootPath.toString());
                    Files.write(rutaCompleta,bytes);//Creando y guarndo la imange
                }



                redirectAttrs
                        .addFlashAttribute("foto","Has subido correcatmente: "+file.getOriginalFilename())
                        .addFlashAttribute("clase","info");

                cliente.setFoto(file.getOriginalFilename());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //Mensajes Flash
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

    @GetMapping(value = "/ver/{id}")
    public String verDetalle(@PathVariable("id") Long id, Map<String, Object> model,RedirectAttributes flash){
        Cliente cliente = clientService.findOneBy(id);
        if(cliente==null){
            flash.addFlashAttribute("mensaje","El cliente no existe en la base de datos")
                    .addFlashAttribute("clase","warning");
            return "redirect:/list";
        }

        System.out.println(cliente.toString());
        model.put("client",cliente);
        model.put("title","Detalle cliente de "+cliente.getNombre());
        return "ver";
    }
}
