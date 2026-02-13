package com.ares.santa_api.controller;

import com.ares.santa_api.dto.*;
import com.ares.santa_api.service.AsistenteService;
import com.ares.santa_api.service.CartaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/santa")
public class MasterController {
    private AsistenteService asistenteService;
    private CartaService cartaService;

    //RECORDARRRR Iniciar los service en el constructor de los controler (del Master en este caso)
    public MasterController(
            AsistenteService asistenteService,
            CartaService cartaService) {
        this.asistenteService = asistenteService;
        this.cartaService = cartaService;
    }
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/asistente")
    @ResponseStatus(HttpStatus.CREATED)
    public AsistenteResponse createAsistente(@Valid @RequestBody AsistenteCreateRequest request) {
        return asistenteService.create(request);
    }


    @PostMapping("/carta")
    @ResponseStatus(HttpStatus.CREATED)
    public CartaResponse createCarta(@Valid @RequestBody CartaCreateRequest request) {
        return cartaService.create(request);
    }

    @PutMapping("/cartas/{cartaId}/assistente/{assistenteId}")
    public CartaResponse updateCarta(@PathVariable Long cartaId, @Valid @RequestBody CartaPutRequest request) {
        return cartaService.update(cartaId, request);
    }

    @PatchMapping("/cartas/{cartaId}/fechaEntrega")
    public CartaResponse patchCarta(@PathVariable Long cartaId, @Valid @RequestBody CartaPatchRequest request) {
        return cartaService.patchDate(cartaId, request);
    }
    @GetMapping("assistentes/{asistenteId}/cartas")
    public List<CartaResponse> getCartas(@PathVariable String asistenteId) {
        return cartaService.getCartasAsistente(asistenteId);
    }
}

