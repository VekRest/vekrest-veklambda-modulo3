package com.vekrest.veklambda.veklambda.function.dto;

import com.vekrest.veklambda.veklambda.entities.Address;
import java.time.LocalDate;

public record ClientRegisteredMessage(
        String name,
        LocalDate birth,
        Address address
) {}