package com.iesserpisdam.tickets.api.dto;

import com.iesserpisdam.tickets.api.entity.TicketPriority;
import com.iesserpisdam.tickets.api.entity.TicketType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketCreateRequest { //Lo que llega en el POST

    @NotBlank
    private String room;
    @NotNull
    private TicketType type;
    @NotBlank
    @Size(min = 10, max = 500)
    private String description;
    @NotNull
    private TicketPriority priority;
}
