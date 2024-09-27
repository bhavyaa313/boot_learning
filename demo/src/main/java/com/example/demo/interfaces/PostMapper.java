package com.example.demo.interfaces;

import com.example.demo.dto.PostRequestDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {

//    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
//
    Post postDtoToPost(PostRequestDto postRequestDto);

    PostRequestDto postToPostDto(Post post);

    PostResponseDto postToPostResponseDto(Post post);
}
