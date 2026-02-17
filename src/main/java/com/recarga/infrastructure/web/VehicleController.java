package com.recarga.infrastructure.web;

import com.recarga.application.AddVehicleFromCatalog;
import com.recarga.application.CreateVehicle;
import com.recarga.application.GetUserVehicles;
import com.recarga.application.dto.AddVehicleFromCatalogRequest;
import com.recarga.application.dto.CreateVehicleRequest;
import com.recarga.application.dto.VehicleResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final CreateVehicle createVehicle;
    private final GetUserVehicles getUserVehicles;
    private final AddVehicleFromCatalog addVehicleFromCatalog;

    public VehicleController(CreateVehicle createVehicle, GetUserVehicles getUserVehicles, AddVehicleFromCatalog addVehicleFromCatalog) {
        this.createVehicle = createVehicle;
        this.getUserVehicles = getUserVehicles;
        this.addVehicleFromCatalog = addVehicleFromCatalog;
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> create(@Valid @RequestBody CreateVehicleRequest request) {
        UUID userId = getCurrentUserId();
        VehicleResponse response = createVehicle.execute(request, userId);
        return ResponseEntity.created(URI.create("/api/vehicles/" + response.getId())).body(response);
    }

    @PostMapping("/from-catalog")
    public ResponseEntity<VehicleResponse> createFromCatalog(@Valid @RequestBody AddVehicleFromCatalogRequest request) {
        UUID userId = getCurrentUserId();
        VehicleResponse response = addVehicleFromCatalog.execute(request, userId);
        return ResponseEntity.created(URI.create("/api/vehicles/" + response.getId())).body(response);
    }

    @GetMapping
    public ResponseEntity<List<VehicleResponse>> list() {
        UUID userId = getCurrentUserId();
        List<VehicleResponse> vehicles = getUserVehicles.execute(userId);
        return ResponseEntity.ok(vehicles);
    }

    private static UUID getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new IllegalStateException("Usuário não autenticado");
        }
        return (UUID) auth.getPrincipal();
    }
}
