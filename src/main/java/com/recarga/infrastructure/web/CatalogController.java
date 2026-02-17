package com.recarga.infrastructure.web;

import com.recarga.application.ListEvCatalog;
import com.recarga.application.dto.EvCatalogResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    private final ListEvCatalog listEvCatalog;

    public CatalogController(ListEvCatalog listEvCatalog) {
        this.listEvCatalog = listEvCatalog;
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<EvCatalogResponse>> listVehicles(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        List<EvCatalogResponse> vehicles = listEvCatalog.execute(brand, year, model, page, size);
        return ResponseEntity.ok(vehicles);
    }
}
