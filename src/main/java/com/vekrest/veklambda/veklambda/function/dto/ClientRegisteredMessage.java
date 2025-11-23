feat: package com.vekrest.veklambda.veklambda.function.dto;

import java.time.LocalDate;

public record ClientRegisteredMessage(
        String name,
        LocalDate birth,
        String cep,
        String state
) {}