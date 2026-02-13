package com.iesserpisdam.tickets.api.controller;

import com.iesserpisdam.tickets.api.dto.TicketCreateRequest;
import com.iesserpisdam.tickets.api.dto.TicketResponse;
import com.iesserpisdam.tickets.api.dto.TicketUpdateRequest;
import com.iesserpisdam.tickets.api.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private TicketService service;
    public TicketController(TicketService service) {
        this.service = service;
    }
    @GetMapping("/ping")
    public String ping() {
        return "Ticket API is running";
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse create(@Valid @RequestBody TicketCreateRequest request){
        return service.create(request);
    }
    @GetMapping
    public List<TicketResponse> findAll(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public TicketResponse findById(@PathVariable Long id){
        return service.findById(id);
    }
    @PutMapping("/{id}")
    public TicketResponse update(@PathVariable Long id, @Valid @RequestBody TicketUpdateRequest request){
        return service.update(id, request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
