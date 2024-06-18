package com.examen.persistence;

import com.examen.entity.CargaHoraria;
import com.examen.entity.Docente;
import com.examen.entity.Grupo;
import com.examen.entity.Materia;

import java.util.List;
import java.util.Optional;

public interface ICargaHorariaDAO {

    List<CargaHoraria> findAll();

    List<CargaHoraria> findByDocente(Docente docente);

    Optional<CargaHoraria> findById(Long id);

    Optional<CargaHoraria> findByGrupoAndMateria(Grupo grupo, Materia materia);

    void save(CargaHoraria cargaHoraria);

    void deleteById(Long id);
}
