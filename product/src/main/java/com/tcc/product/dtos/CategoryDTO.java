package com.tcc.product.dtos;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private String name;
}
