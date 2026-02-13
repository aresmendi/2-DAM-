package com.ares.santa_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CartaPatchRequest {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NotNull
    private LocalDateTime fechaEntrega;
}
