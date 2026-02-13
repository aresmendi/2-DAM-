package com.ares.santa_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsistenteCreateRequest {
    @NotBlank
    private String id;
}
