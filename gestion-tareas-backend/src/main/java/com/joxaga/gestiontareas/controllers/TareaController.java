package com.joxaga.gestiontareas.controllers;

import com.joxaga.gestiontareas.entities.TareaEntity;
import com.joxaga.gestiontareas.services.TareaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.joxaga.gestiontareas.repositories.TareaRepository;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.OPTIONS})

public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Autowired
    private TareaRepository tareaRepository;

    public ResponseEntity<Void> handleOptions(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id) {
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Origin", " http://localhost:4200");
        response.setHeader("Access-Control-Max-Age", "3600"); // 1 hour
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<TareaEntity> saveTask (@Valid  @RequestBody TareaEntity tarea){
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaService.saveTask(tarea));
    }
  
    @GetMapping
    public ResponseEntity<Page<TareaEntity>> getAllTask (
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination
    ){
        return ResponseEntity.ok(tareaService.getAllTask(page, size, enablePagination));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable ("id") Long id){
        tareaService.deleteTask(id);
        return ResponseEntity.ok(!tareaService.existById(id));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TareaEntity> findById(@PathVariable Long id) {
        Optional<TareaEntity> TaskOptional = tareaService.findById(id);
        if (TaskOptional.isPresent()) {
            TareaEntity tareas = TaskOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(tareas);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<TareaEntity> findByCedula(@PathVariable String titulo) {
        Optional<TareaEntity> taskOptional = tareaService.findByTitulo(titulo);
        if (taskOptional.isPresent()) {
            TareaEntity tarea = taskOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(tarea);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaEntity> updateTask(@PathVariable Long id, @RequestBody TareaEntity tarea) {
        Optional<TareaEntity> tareaExistente = tareaService.findById(id);
        System.out.println(tareaExistente);
        if (tareaExistente.isPresent()) {
            tarea.setId(id); // Asigna el ID proporcionado a la tarea
            TareaEntity taskUpdate = tareaRepository.save(tarea); // Actualiza la tarea.
            System.out.println(taskUpdate);
            System.out.println(new ResponseEntity<>(taskUpdate, HttpStatus.OK));
            return new ResponseEntity<>(taskUpdate, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
