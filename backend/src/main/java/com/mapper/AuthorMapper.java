package com.mapper;

import com.entities.AuthorEntity;
import com.entities.ParticipantEntity;
import com.model.Author;
import com.model.Participant;

import javax.persistence.Column;

public class AuthorMapper
{
    public static AuthorEntity authorToEntity(Author author)
    {
        return AuthorEntity.builder()
                .name(author.getName())
                .email(author.getEmail())
                .userName(author.getUserName())
                .password(author.getPassword())
                .isAuthenticated(author.getIsAuthenticated())
                //paper.(author.getPaper())
                .section(author.getSection())
                .build();
    }

    public static Author entityToAuthor(AuthorEntity entity)
    {
        return Author.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .userName(entity.getUserName())
                .password(entity.getPassword())
                .isAuthenticated(entity.getIsAuthenticated())
                //paper(entity.getPaper())
                .section(entity.getSection())
                .build();
    }
}
