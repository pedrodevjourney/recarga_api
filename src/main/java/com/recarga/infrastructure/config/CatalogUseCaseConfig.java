package com.recarga.infrastructure.config;

import com.recarga.application.ListEvCatalog;
import com.recarga.domain.ports.EvCatalogRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogUseCaseConfig {

    @Bean
    public ListEvCatalog listEvCatalog(EvCatalogRepository evCatalogRepository) {
        return new ListEvCatalog(evCatalogRepository);
    }
}
