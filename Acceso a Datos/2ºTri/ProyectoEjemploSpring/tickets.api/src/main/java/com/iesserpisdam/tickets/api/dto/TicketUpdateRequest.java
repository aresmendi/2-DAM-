package com.iesserpisdam.tickets.api.dto;

import com.iesserpisdam.tickets.api.entity.TicketPriority;
import com.iesserpisdam.tickets.api.entity.TicketStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketUpdateRequest {//Lo que llega en el PUT
    @Size(min = 10, max = 500)
    private String description;
    @NotNull
    private TicketPriority priority;
    @NotNull
    private TicketStatus status;
}
