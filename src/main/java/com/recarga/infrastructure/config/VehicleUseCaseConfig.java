package com.recarga.infrastructure.config;

import com.recarga.application.AddVehicleFromCatalog;
import com.recarga.application.CreateVehicle;
import com.recarga.application.GetUserVehicles;
import com.recarga.domain.ports.EvCatalogRepository;
import com.recarga.domain.ports.VehicleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleUseCaseConfig {

    @Bean
    public CreateVehicle createVehicle(VehicleRepository vehicleRepository) {
        return new CreateVehicle(vehicleRepository);
    }

    @Bean
    public GetUserVehicles getUserVehicles(VehicleRepository vehicleRepository) {
        return new GetUserVehicles(vehicleRepository);
    }

    @Bean
    public AddVehicleFromCatalog addVehicleFromCatalog(VehicleRepository vehicleRepository, EvCatalogRepository evCatalogRepository) {
        return new AddVehicleFromCatalog(vehicleRepository, evCatalogRepository);
    }
}
