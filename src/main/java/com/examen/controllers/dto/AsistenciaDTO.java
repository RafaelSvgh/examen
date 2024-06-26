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

public class AsistenciaDTO {
    private Long id;
    private char estado;
    private Date fecha;
    private LocalTime hora;
    private String observacion;
    private HorarioDTO horario;
}
