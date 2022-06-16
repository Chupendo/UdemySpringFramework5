package com.example.springbootdatajpaimproved.controllers;

import com.example.springbootdatajpaimproved.entity.Cliente;
import com.example.springbootdatajpaimproved.service.IClienteService;
import com.example.springbootdatajpaimproved.util.paginator.PageRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@Controller
@SessionAttributes("client")
public class ClienteController {

    @Autowired
    IClienteService clientService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
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
            String uniqueFilename = UUID.randomUUID().toString();
            String nameFile = uniqueFilename+"_"+file.getOriginalFilename();
            /*****/
            //Obtenemos el direcotrio de los recursos
            /*****/
            Path rootPath = Paths.get("uploads"); //Directorio externo en raiz + nombre
            Path rootAbsolutePath = rootPath.toAbsolutePath();
            log.info("rootPath: "+rootPath);
            log.info("rootAbsolutePath: "+rootAbsolutePath);
            /*****/
            //StringBuilder rootPath = new StringBuilder(directorioRecursos.toAbsolutePath().toString());
            try {
                //Comprobamos si exsite el direcotior de recursos del cliente
                File directorioRecursosCliente = new File(rootAbsolutePath.toAbsolutePath().toString());
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

                    Files.copy(file.getInputStream(),
                            rootAbsolutePath.resolve(nameFile)
                    );
                    log.info("cliente foto: "+nameFile);
                    cliente.setFoto(nameFile);

                }

                redirectAttrs
                        .addFlashAttribute("foto","Has subido correcatmente: "+cliente.getFoto())
                        .addFlashAttribute("clase","info");



            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //Mensajes Flash
        redirectAttrs
                .addFlashAttribute("mensaje", "Cliente creado/acutalizado correctamente")
                .addFlashAttribute("clase", "success");

        //Se comprueba si tiene imange y se borra
        Cliente clienteViejo = clientService.findOneBy(cliente.getId());
        if(clienteViejo.getFoto()!=null && !clienteViejo.getFoto().equals(cliente.getFoto())){
            Path rootAbsoluteFile = Paths.get("uploads").resolve(clienteViejo.getFoto()).toAbsolutePath();
            File img = rootAbsoluteFile.toFile();
            if(img.exists() && img.canRead()){
                img.delete();
            }
        }
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
        Cliente cliente = clientService.findOneBy(id);
        if(cliente!=null){
            clientService.deleteOneById(id);
            if(!cliente.getFoto().isEmpty()){
                String nameFile = cliente.getFoto();
                Path rootAbsolutePath = Paths.get("uploads").resolve(nameFile).toAbsolutePath();
                File archivo = rootAbsolutePath.toFile();
                if(archivo.exists() && archivo.canRead()){
                    archivo.delete();
                }
            }
        }






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
        log.info("verDetalle: "+cliente.getFoto());
        System.out.println(cliente.toString());
        model.put("client",cliente);
        model.put("title","Detalle cliente de "+cliente.getNombre());
        return "ver";
    }

    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename){
        //Directorio "uploads" dentro de la raíz del proyecto
        Path pathFoto = Paths.get("uploads").resolve(filename).toAbsolutePath();
        log.info("pathFoto: "+pathFoto);
        Resource recurso = null;

        try {
            recurso = new UrlResource(pathFoto.toUri());
            if(!recurso.exists()){
                throw new RuntimeException("Error: no se puede cargar la imange "+pathFoto.toString());
            }
        } catch (MalformedURLException e) {//Rutal invalid
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+recurso.getFilename()+"\"").
                body(recurso);
    }

}
