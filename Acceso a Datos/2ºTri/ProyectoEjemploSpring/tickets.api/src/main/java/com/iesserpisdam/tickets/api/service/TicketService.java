package com.iesserpisdam.tickets.api.service;

import com.iesserpisdam.tickets.api.dto.TicketCreateRequest;
import com.iesserpisdam.tickets.api.dto.TicketResponse;
import com.iesserpisdam.tickets.api.dto.TicketUpdateRequest;
import com.iesserpisdam.tickets.api.entity.Ticket;
import com.iesserpisdam.tickets.api.entity.TicketStatus;
import com.iesserpisdam.tickets.api.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


@Service
public class TicketService {
    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public TicketResponse create(TicketCreateRequest request) {
        Ticket ticket = new Ticket();
        ticket.setRoom(request.getRoom());
        ticket.setType(request.getType());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setOpenedAt(LocalDateTime.now());
        Ticket saved = repository.save(ticket);
        return mapToResponse(saved);
    }

    public List<TicketResponse> findAll() {
        return repository.findAll().stream().map(this::mapToResponse).toList();
    }

    public TicketResponse findById(Long id) {
        Ticket ticket = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Ticket not found " + id));
        return mapToResponse(ticket);
    }

    public TicketResponse update(Long id, TicketUpdateRequest request) {
        Ticket ticket = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Ticket not found " + id));
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        ticket.setStatus(request.getStatus());
        Ticket saved = repository.save(ticket);
        return mapToResponse(saved);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Ticket not found " + id);
        }
        repository.deleteById(id);
    }

    private TicketResponse mapToResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        response.setId(ticket.getId());
        response.setRoom(ticket.getRoom());
        response.setType(ticket.getType());
        response.setDescription(ticket.getDescription());
        response.setPriority(ticket.getPriority());
        response.setStatus(ticket.getStatus());
        response.setOpenedAt(ticket.getOpenedAt());
        return response;
    }
}
