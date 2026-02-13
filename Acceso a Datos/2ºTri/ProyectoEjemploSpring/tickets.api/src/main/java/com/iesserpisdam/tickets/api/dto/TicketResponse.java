package com.iesserpisdam.tickets.api.dto;

import com.iesserpisdam.tickets.api.entity.TicketPriority;
import com.iesserpisdam.tickets.api.entity.TicketStatus;
import com.iesserpisdam.tickets.api.entity.TicketType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TicketResponse {//Lo que devolvemos con el GET/POST/PUT
    private Long id;
    private String room;
    private TicketType type;
    private String description;
    private TicketPriority priority;
    private TicketStatus status;
    private LocalDateTime openedAt;
}
