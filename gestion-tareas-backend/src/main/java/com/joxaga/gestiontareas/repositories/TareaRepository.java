package com.joxaga.gestiontareas.repositories;

import com.joxaga.gestiontareas.entities.TareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TareaRepository extends JpaRepository<TareaEntity, Long>  {
    Optional<TareaEntity> findByTitulo(String titulo);
}
