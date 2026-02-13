package com.iesserpisdam.tickets.api.repository;

import com.iesserpisdam.tickets.api.entity.Ticket;
import com.iesserpisdam.tickets.api.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByRoom(String room);
    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findByRoomAndStatus(String room, TicketStatus status);
}
