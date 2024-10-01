package com.example.demo.model.dto.request;


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
    private String author;


}
