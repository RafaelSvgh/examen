package com.examen.controllers.dto;

import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data

public class AsistenciaDocenteDTO {
    private Long id;
    private char estado;
    private Date fecha;
    private LocalTime hora;
    private String observacion;
    private String nombre;
    private String apellido;
    private HorarioDTO horario;
}
