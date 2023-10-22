import React from 'react';

enum TypeOfGenre{
    ACTION = 'ACTION',
    HORROR = 'HORROR',
    MYSTERY = 'MYSTERY',
    THRILLER = 'THRILLER',
    ROMANCE = 'ROMANCE',
    ANIME = 'ANIME',
    DOCUMENTARY = 'DOCUMENTARY',
    COMEDY = 'COMEDY',
    DRAMA = 'DRAMA',
    FANTASY = 'FANTASY',
   

}

export const TypeOfGenreList : string[] = Object.values(TypeOfGenre);

export default TypeOfGenre;