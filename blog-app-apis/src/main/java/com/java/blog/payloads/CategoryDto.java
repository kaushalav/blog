package com.java.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    @NotBlank
    private String categoryTitle;
    @NotBlank
    private String categoryDescription;
}
