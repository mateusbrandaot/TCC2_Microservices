package com.agenday.registry.dto;

public record AddressDTO(

         String street,
         Long city,
         String zipCode,
         String complement,
         String number
) {
}
