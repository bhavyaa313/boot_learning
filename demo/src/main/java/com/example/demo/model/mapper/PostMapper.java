package com.example.demo.model.mapper;

import com.example.demo.model.dto.request.PostRequestDto;
import com.example.demo.model.dto.response.PostResponseDto;
import com.example.demo.model.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post postDtoToPost(PostRequestDto postRequestDto);

    PostRequestDto postToPostDto(Post post);

    PostResponseDto postToPostResponseDto(Post post);
}
