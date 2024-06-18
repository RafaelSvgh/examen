package com.examen.repository;

import com.examen.entity.CargaHoraria;
import com.examen.entity.Docente;
import com.examen.entity.Grupo;
import com.examen.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CargaHorariaRepository extends JpaRepository<CargaHoraria, Long> {

    List<CargaHoraria> findByDocente(Docente docente);

    Optional<CargaHoraria> findByGrupoAndMateria(Grupo grupo, Materia materia);

}
