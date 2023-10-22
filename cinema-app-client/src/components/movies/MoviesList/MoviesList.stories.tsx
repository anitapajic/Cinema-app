import { Meta, StoryObj } from "@storybook/react";
import { ThemeProvider } from "styled-components";
import { theme } from "../../../utils/data";
import MoviesList from "./MoviesList";
import TypeOfGenre from "../../../models/Enums/TypeOfGenre";

const meta = {
    title: 'MoviePage-components/MoviesList',
    component: MoviesList,
    decorators: [(Story) => <ThemeProvider theme={theme}><Story /></ThemeProvider>],
    tags: ['autodocs']
  } satisfies Meta<typeof MoviesList>
  
  export default meta;
  
  type Story = StoryObj<typeof meta>;

  export const Primary: Story = {
    args:{
        movies: [
            {id:1, name: "Shutter Island", duration:120, rating:4.8, posterImage:"/images/shutterIsland.jpg", genres:[{id:2, type: TypeOfGenre.MYSTERY}, {id:3, type: TypeOfGenre.THRILLER}]},
            {id:2, name: "The Invisible Guest", duration:120, rating:4.6, posterImage:"/images/theInvisibleGuest.jpg", genres:[{id:2, type: TypeOfGenre.MYSTERY}, {id:3, type: TypeOfGenre.THRILLER}]},
            {id:3, name: "The body", duration:120, rating:4.6, posterImage:"/images/theBody.jpg", genres:[{id:2, type: TypeOfGenre.MYSTERY}, {id:3, type: TypeOfGenre.THRILLER}]},
            {id:4, name: "Annabelle", duration:120, rating:4.6, posterImage:"/images/anabel.jpg", genres:[{id:1, type: TypeOfGenre.HORROR}]},
            {id:5, name: "Barbie", duration:120, rating:4.1, posterImage:"/images/barbie.jpg", genres:[{id:7, type: TypeOfGenre.COMEDY}, {id:8, type: TypeOfGenre.DRAMA}]},
            {id:6, name: "Notebook", duration:120, rating:4.6, posterImage:"/images/notebook.jpg", genres:[{id:4, type: TypeOfGenre.ROMANCE}]}
          ]
    },
  };

  export const SingleItem: Story = {
    args:{
        movies: [
            {id:1, name: "Shutter Island", duration:120, rating:4.8, posterImage:"/images/shutterIsland.jpg", genres:[{id:2, type: TypeOfGenre.MYSTERY}, {id:3, type: TypeOfGenre.THRILLER}]}
        ]
    },
  };