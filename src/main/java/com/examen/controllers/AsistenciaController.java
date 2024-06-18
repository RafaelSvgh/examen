package com.examen.controllers;

import com.examen.controllers.dto.AsistenciaDTO;
import com.examen.controllers.dto.AsistenciaDocenteDTO;
import com.examen.controllers.dto.AsistenciaHorarioDTO;
import com.examen.controllers.dto.HorarioDTO;
import com.examen.entity.Asistencia;
import com.examen.entity.CargaHoraria;
import com.examen.entity.Horario;
import com.examen.service.IAsistenciaService;
import com.examen.service.ICargaHorariaService;
import com.examen.service.IHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {
    @Autowired
    private IAsistenciaService asistenciaService;

    @Autowired
    private IHorarioService horarioService;

    @Autowired
    private ICargaHorariaService cargaHorariaService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Asistencia> asistenciaOptional = asistenciaService.findById(id);
        if(asistenciaOptional.isPresent()){
            Asistencia asistencia = asistenciaOptional.get();
            AsistenciaDTO asistenciaDTO = AsistenciaDTO.builder()
                    .id(asistencia.getId())
                    .fecha(asistencia.getFecha())
                    .hora(asistencia.getHora())
                    .estado(asistencia.getEstado())
                    .observacion(asistencia.getObservacion())
                    .horario(HorarioDTO.builder()
                            .id(asistencia.getHorario().getId())
                            .dia(asistencia.getHorario().getDia())
                            .horaInicio(asistencia.getHorario().getHoraInicio())
                            .horaFin(asistencia.getHorario().getHoraFin())
                            .build()
                    )
                    .build();
            return ResponseEntity.ok(asistenciaDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
//        List<AsistenciaDTO> asistenciaDTOS = asistenciaService.findAll().
//                stream().map(asistencia -> AsistenciaDTO.builder()
//                        .id(asistencia.getId())
//                        .fecha(asistencia.getFecha())
//                        .hora(asistencia.getHora())
//                        .estado(asistencia.getEstado())
//                        .observacion(asistencia.getObservacion())
//                        .horario(HorarioDTO.builder()
//                                .id(asistencia.getHorario().getId())
//                                .dia(asistencia.getHorario().getDia())
//                                .horaInicio(asistencia.getHorario().getHoraInicio())
//                                .horaFin(asistencia.getHorario().getHoraFin())
//                                .build())
//                        .build()
//                ).toList();


        List<AsistenciaDocenteDTO> asistenciaDTOS = new ArrayList<>();
        List<Asistencia> asistencias = asistenciaService.findAll();
        for(Asistencia asistencia : asistencias){
            Optional<CargaHoraria> cargaHorariaOptional = cargaHorariaService.findByGrupoAndMateria(asistencia.getHorario().getGrupo(),asistencia.getHorario().getMateria());
            if(cargaHorariaOptional.isPresent()){
                CargaHoraria cargaHoraria = cargaHorariaOptional.get();
                AsistenciaDocenteDTO asistenciaDocenteDTO = AsistenciaDocenteDTO.builder()
                        .id(asistencia.getId())
                        .estado(asistencia.getEstado())
                        .fecha(asistencia.getFecha())
                        .hora(asistencia.getHora())
                        .observacion(asistencia.getObservacion())
                        .nombre(cargaHoraria.getDocente().getNombre())
                        .apellido(cargaHoraria.getDocente().getApellidoP())
                        .horario(HorarioDTO.builder()
                                .id(asistencia.getHorario().getId())
                                .dia(asistencia.getHorario().getDia())
                                .horaInicio(asistencia.getHorario().getHoraInicio())
                                .horaFin(asistencia.getHorario().getHoraFin())
                                .build())
                        .build();
                asistenciaDTOS.add(asistenciaDocenteDTO);
            }
        }

        return ResponseEntity.ok(asistenciaDTOS);
    }

    @GetMapping("/findHorario/{idHorario}")
    public ResponseEntity<?> findHorario(@PathVariable Long idHorario){

        Optional<Horario> horarioOptional = horarioService.findById(idHorario);

        if (horarioOptional.isPresent()){
            Horario horario = horarioOptional.get();

            List<AsistenciaHorarioDTO> asistenciaDTOS = asistenciaService.findByHorario(horario).
                    stream().map(asistencia -> AsistenciaHorarioDTO.builder()
                            .id(asistencia.getId())
                            .fecha(asistencia.getFecha())
                            .hora(asistencia.getHora())
                            .estado(asistencia.getEstado())
                            .observacion(asistencia.getObservacion())
                            .dia(asistencia.getHorario().getDia())
                            .horaInicio(asistencia.getHorario().getHoraInicio())
                            .aula(asistencia.getHorario().getAula().getNumero())
                            .modulo(asistencia.getHorario().getAula().getModulo().getNombre())
                            .horaFin(asistencia.getHorario().getHoraFin())
                            .horarioId(asistencia.getHorario().getId())
                            .build()
                    ).toList();
            return ResponseEntity.ok(asistenciaDTOS);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody AsistenciaDTO asistenciaDTO) throws URISyntaxException {
        Asistencia asistencia = Asistencia.builder()
                .estado(asistenciaDTO.getEstado())
                .fecha(asistenciaDTO.getFecha())
                .hora(asistenciaDTO.getHora())
                .observacion(asistenciaDTO.getObservacion())
                .horario(Horario.builder()
                        .id(asistenciaDTO.getHorario().getId())
                        .dia(asistenciaDTO.getHorario().getDia())
                        .horaInicio(asistenciaDTO.getHorario().getHoraInicio())
                        .horaFin(asistenciaDTO.getHorario().getHoraFin())
                        .build()
                )
                .build();
        asistenciaService.save(asistencia);
        return ResponseEntity.created(new URI("/api/asistencia/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AsistenciaDTO asistenciaDTO) throws URISyntaxException {
        Optional<Asistencia> asistenciaOptional = asistenciaService.findById(id);
        if(asistenciaOptional.isPresent()){
            Asistencia asistencia = asistenciaOptional.get();
            asistencia.setFecha(asistenciaDTO.getFecha());
            asistencia.setHora(asistenciaDTO.getHora());
            asistencia.setEstado(asistenciaDTO.getEstado());
            asistencia.setObservacion(asistenciaDTO.getObservacion());
            asistencia.setHorario(Horario.builder()
                    .id(asistenciaDTO.getHorario().getId())
                    .dia(asistenciaDTO.getHorario().getDia())
                    .horaInicio(asistenciaDTO.getHorario().getHoraInicio())
                    .horaFin(asistenciaDTO.getHorario().getHoraFin())
                    .build());
            asistencia.setId(id);
            asistenciaService.save(asistencia);
            return ResponseEntity.ok("Registro actualizado");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (id != null && asistenciaService.findById(id).isPresent()){
            asistenciaService.deleteById(id);
            return ResponseEntity.ok("Registro eliminado");
        }
        return ResponseEntity.badRequest().build();
    }

}
