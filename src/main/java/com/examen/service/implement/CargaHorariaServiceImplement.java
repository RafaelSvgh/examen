package com.examen.service.implement;

import com.examen.entity.CargaHoraria;
import com.examen.entity.Docente;
import com.examen.entity.Grupo;
import com.examen.entity.Materia;
import com.examen.persistence.ICargaHorariaDAO;
import com.examen.repository.CargaHorariaRepository;
import com.examen.service.ICargaHorariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargaHorariaServiceImplement implements ICargaHorariaService {

    @Autowired
    private ICargaHorariaDAO cargaHorariaDAO;


    @Override
    public List<CargaHoraria> findAll() {
        return cargaHorariaDAO.findAll();
    }

    @Override
    public List<CargaHoraria> findByDocente(Docente docente) {
        return  cargaHorariaDAO.findByDocente(docente);
    }

    @Override
    public Optional<CargaHoraria> findById(Long id) {
        return cargaHorariaDAO.findById(id);
    }

    @Override
    public Optional<CargaHoraria> findByGrupoAndMateria(Grupo grupo, Materia materia) {
        return cargaHorariaDAO.findByGrupoAndMateria(grupo, materia);
    }

    @Override
    public void save(CargaHoraria cargaHoraria) {
        cargaHorariaDAO.save(cargaHoraria);
    }

    @Override
    public void deleteById(Long id) {
        cargaHorariaDAO.deleteById(id);
    }
}
