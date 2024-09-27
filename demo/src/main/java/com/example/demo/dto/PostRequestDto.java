package com.example.demo.dto;


import jakarta.validation.constraints.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {


    private Long id;

    @NotBlank(message = "Please provide a title")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;

    @NotBlank(message = "Please provide content")
    private String content;

    @NotBlank(message = "Please provide an author name")
//    @Size(min = 2, max = 50, message = "Author's name must be between 2 and 50 characters")
//    @NotNull(message = "not null")
    private String author;


}
