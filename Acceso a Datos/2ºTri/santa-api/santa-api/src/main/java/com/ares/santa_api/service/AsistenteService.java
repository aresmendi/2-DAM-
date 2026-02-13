package com.ares.santa_api.service;

import com.ares.santa_api.dto.AsistenteCreateRequest;
import com.ares.santa_api.dto.AsistenteResponse;
import com.ares.santa_api.entity.Asistente;
import com.ares.santa_api.repository.AsistenteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AsistenteService {
    private AsistenteRepository repository;
    public AsistenteService(AsistenteRepository repository) {
        this.repository = repository;
    }
    public AsistenteResponse create(AsistenteCreateRequest request){
        List<Asistente> asistentes = repository.findAll();
        for(Asistente asistente : asistentes){
            if(asistente.getId().equalsIgnoreCase(request.getId())){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Asistente existente");
            }
        }
        Asistente asistente = new Asistente();
        asistente.setId(request.getId());
        Asistente saved = repository.save(asistente);
        return mapToResponse(saved);
    }


    private AsistenteResponse mapToResponse(Asistente asistente) {
        AsistenteResponse response = new AsistenteResponse();
        response.setId(asistente.getId());
        return response;
    }
}
