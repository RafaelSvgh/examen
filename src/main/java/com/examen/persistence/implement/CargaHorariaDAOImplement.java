package com.examen.persistence.implement;

import com.examen.entity.*;
import com.examen.persistence.IAulaDAO;
import com.examen.persistence.ICargaHorariaDAO;
import com.examen.repository.AulaRepository;
import com.examen.repository.CargaHorariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CargaHorariaDAOImplement implements ICargaHorariaDAO {

    @Autowired
    private CargaHorariaRepository cargaHorariaRepository;


    @Override
    public List<CargaHoraria> findAll() {
        return cargaHorariaRepository.findAll();
    }

    @Override
    public List<CargaHoraria> findByDocente(Docente docente) {
        return cargaHorariaRepository.findByDocente(docente);
    }

    @Override
    public Optional<CargaHoraria> findById(Long id) {
        return cargaHorariaRepository.findById(id);
    }

    @Override
    public Optional<CargaHoraria> findByGrupoAndMateria(Grupo grupo, Materia materia) {
        return cargaHorariaRepository.findByGrupoAndMateria(grupo, materia);
    }

    @Override
    public void save(CargaHoraria cargaHoraria) {
        cargaHorariaRepository.save(cargaHoraria);
    }

    @Override
    public void deleteById(Long id) {
        cargaHorariaRepository.deleteById(id);
    }
}
