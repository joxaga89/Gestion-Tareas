package com.joxaga.gestiontareas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.joxaga.gestiontareas.entities.TareaEntity;
import com.joxaga.gestiontareas.repositories.TareaRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

 @Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    @Transactional
    public TareaEntity saveTask (TareaEntity tarea){
        if (tarea.getId() == null){
            return tareaRepository.save(tarea);
        }
        return null;
    }

    @Transactional
    public List<TareaEntity> saveListTask(List<TareaEntity> tarea) {
        return tareaRepository.saveAll(tarea);
    }

    public Page<TareaEntity> getAllTask (Integer page, Integer size, Boolean enablePagination){
        return tareaRepository.findAll(enablePagination ? PageRequest.of(page, size): Pageable.unpaged());
    }

    public Optional<TareaEntity> findById(Long id){
        return tareaRepository.findById(id);
    }

    public  Optional<TareaEntity> findByTitulo(String titulo){
        return tareaRepository.findByTitulo(titulo);
    }

    @Transactional
    public void deleteTask(Long id){
        tareaRepository.deleteById(id);
    }

    @Transactional
    public TareaEntity editTask (TareaEntity tarea){
        if (tarea.getId() != null && tareaRepository.existsById(tarea.getId())){
            return tareaRepository.save(tarea);
        }
        return null;
    }

    public boolean existById(Long id) {
        return tareaRepository.existsById(id);
    }

}
