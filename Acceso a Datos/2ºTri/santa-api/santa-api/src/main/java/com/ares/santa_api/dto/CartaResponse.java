package com.ares.santa_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CartaResponse {
    private Long id;
    private String asistenteId;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime fechaEntrega;
}
