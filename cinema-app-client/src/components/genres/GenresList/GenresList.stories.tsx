import { Meta, StoryObj } from "@storybook/react";
import { ThemeProvider } from "styled-components";
import { theme } from "../../../utils/data";
import GenresList from "./GenresList";
import TypeOfGenre, { TypeOfGenreList } from "../../../models/Enums/TypeOfGenre";

const meta = {
    title: 'GenrePage-components/GenresList',
    component: GenresList,
    decorators: [(Story) => <ThemeProvider theme={theme}><Story /></ThemeProvider>],
    tags: ['autodocs']
  } satisfies Meta<typeof GenresList>
  
  export default meta;
  
  type Story = StoryObj<typeof meta>;

  export const Primary: Story = {
    args:{
        genres: [
           {id: 1, type: TypeOfGenre.ACTION},
           {id: 2, type: TypeOfGenre.HORROR},
           {id: 3, type: TypeOfGenre.MYSTERY},
           {id: 4, type: TypeOfGenre.THRILLER},
           {id: 5, type: TypeOfGenre.ROMANCE},
           {id: 6, type: TypeOfGenre.ANIME},
           {id: 7, type: TypeOfGenre.DOCUMENTARY},
           {id: 8, type: TypeOfGenre.COMEDY},
           {id: 9, type: TypeOfGenre.DRAMA},
           {id: 10, type: TypeOfGenre.FANTASY}
        ]
    },
  };

  export const SingleItem: Story = {
    args:{
        genres: [
          {id: 1, type: TypeOfGenre.ACTION},
        ]
    },
  };

  