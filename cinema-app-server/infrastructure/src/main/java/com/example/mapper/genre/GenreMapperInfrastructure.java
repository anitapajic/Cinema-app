package com.example.mapper.genre;

import com.example.model.Genre;
import com.example.persistence.genre.model.GenreJpaEntity;

public class GenreMapperInfrastructure {

    public static Genre toDomain(GenreJpaEntity genreJpaEntity){
        return new Genre(genreJpaEntity.getId(), genreJpaEntity.getType());
    }

    public static GenreJpaEntity toDataBase(Genre genre){
        return new GenreJpaEntity(genre.getId(), genre.getType());
    }
}
