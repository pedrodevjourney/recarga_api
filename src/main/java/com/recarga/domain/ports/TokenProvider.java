package com.recarga.domain.ports;

import java.util.UUID;

public interface TokenProvider {

    String generate(UUID userId);

    UUID getUserIdFromToken(String token);
}
