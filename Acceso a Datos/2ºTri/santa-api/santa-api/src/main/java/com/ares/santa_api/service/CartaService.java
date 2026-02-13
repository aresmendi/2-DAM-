package com.ares.santa_api.service;

import com.ares.santa_api.dto.*;
import com.ares.santa_api.entity.Asistente;
import com.ares.santa_api.entity.Carta;
import com.ares.santa_api.repository.AsistenteRepository;
import com.ares.santa_api.repository.CartaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CartaService {
    private final CartaRepository repository;
    private final AsistenteRepository asistenteRepository;

    public CartaService(CartaRepository repository, AsistenteRepository asistenteRepository) {
        this.repository = repository;
        this.asistenteRepository = asistenteRepository;
    }

    public CartaResponse create(CartaCreateRequest request) {
        LocalDateTime inicio = LocalDateTime.of(2025, 12, 24, 23, 0);
        LocalDateTime fin = LocalDateTime.of(2025, 12, 25, 23, 59);
        LocalDateTime fechaEntrega = request.getFechaEntrega();
        if (fechaEntrega.isBefore(inicio) || fechaEntrega.isAfter(fin)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha Incorrecta");
        }
        Asistente asistente = asistenteRepository.findById(request.getAsistenteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asistente inexistente " + request.getAsistenteId()));
        Carta carta = new Carta();
        carta.setAsistente(asistente);
        carta.setFechaEntrega(request.getFechaEntrega());
        Carta saved = repository.save(carta);
        return mapToResponse(saved);
    }

    public CartaResponse update(Long id, CartaPutRequest request) {
        Carta carta = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carta no encontrada " + id));
        Asistente asistente = asistenteRepository.findById(request.getAsistenteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asistente inexistente " + request.getAsistenteId()));
        if (carta.getAsistente().getId().equalsIgnoreCase(asistente.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Asistente ya asignado");
        }
        carta.setAsistente(asistente);
        Carta saved = repository.save(carta);
        return mapToResponse(saved);
    }

    public CartaResponse patchDate(Long id, CartaPatchRequest request) {
        Carta carta = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carta no encontrada " + id));
        LocalDateTime inicio = LocalDateTime.of(2025, 12, 24, 23, 0);
        LocalDateTime fin = LocalDateTime.of(2025, 12, 25, 23, 59);
        LocalDateTime fechaEntrega = request.getFechaEntrega();
        if (fechaEntrega.isBefore(inicio) || fechaEntrega.isAfter(fin)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha Incorrecta");
        }
        if (fechaEntrega.isEqual(carta.getFechaEntrega())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Misma fecha que la anteriormente asignada");
        }
        carta.setFechaEntrega(request.getFechaEntrega());
        Carta saved = repository.save(carta);
        return mapToResponse(saved);
    }

    public List<CartaResponse> getCartasAsistente(String id) {
        Asistente asistente = asistenteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asistente inexistente " + id));
        if (asistente.getCartas().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asistente sin cartas");
        }
        return repository.findByAsistente_Id(asistente.getId()).stream().map(this::mapToResponse).toList();
    }


    private CartaResponse mapToResponse(Carta carta) {
        CartaResponse response = new CartaResponse();
        response.setId(carta.getId());
        response.setAsistenteId(carta.getAsistente().getId());
        response.setFechaEntrega(carta.getFechaEntrega());
        return response;
    }
}
