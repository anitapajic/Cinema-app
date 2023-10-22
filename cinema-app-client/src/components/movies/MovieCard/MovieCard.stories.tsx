import { Meta, StoryObj } from "@storybook/react";
import { ThemeProvider } from "styled-components";
import { theme } from "../../../utils/data";
import TypeOfGenre from "../../../models/Enums/TypeOfGenre";
import MovieCard from "./MovieCard";
import { showToast } from "../../shared/toast/CustomToast";

const meta = {
    title: 'MoviePage-components/MovieCard',
    component: MovieCard,
    decorators: [(Story) => <ThemeProvider theme={theme}><Story /></ThemeProvider>],
    tags: ['autodocs']
  } satisfies Meta<typeof MovieCard>
  
  export default meta;
  
  type Story = StoryObj<typeof meta>;

  export const Primary: Story = {
    args:{
        movie: {id:1, name: "Shutter Island", duration:120, rating:4.8, posterImage:"/images/shutterIsland.jpg", genres:[{id:2, type: TypeOfGenre.MYSTERY}, {id:3, type: TypeOfGenre.THRILLER}]},
        onEdit: () => alert('Edit clicked'),
        onDelete: () => alert('Delete clicked'),
    },
  };

  export const LongName: Story = {
    args:{
        movie: {id:1, name: "Shutter Island shutter island shutter island", duration:120, rating:4.8, posterImage:"/images/shutterIsland.jpg", genres:[{id:2, type: TypeOfGenre.MYSTERY}, {id:3, type: TypeOfGenre.THRILLER}]},
        onEdit: () => alert('Edit clicked'),
        onDelete: () => alert('Delete clicked'),
    },
  };

  export const ManyGenres: Story = {
    args:{
        movie: {id:1, name: "Shutter Island", duration:120, rating:4.8, posterImage:"/images/shutterIsland.jpg", genres:[{id:2, type: TypeOfGenre.MYSTERY}, {id:3, type: TypeOfGenre.THRILLER}, {id:3, type: TypeOfGenre.THRILLER}, {id:3, type: TypeOfGenre.THRILLER}, {id:3, type: TypeOfGenre.THRILLER}]},
        onEdit: () => alert('Edit clicked'),
        onDelete: () => alert('Delete clicked'),
    },
  };

  export const NoActions: Story = {
    args:{
        movie: {id:1, name: "Shutter Island", duration:120, rating:4.8, posterImage:"/images/shutterIsland.jpg", genres:[{id:2, type: TypeOfGenre.MYSTERY}, {id:3, type: TypeOfGenre.THRILLER}]},
    },
  };

  export const EditOnly: Story = {
    args:{
        movie: {id:1, name: "Shutter Island", duration:120, rating:4.8, posterImage:"/images/shutterIsland.jpg", genres:[{id:2, type: TypeOfGenre.MYSTERY}, {id:3, type: TypeOfGenre.THRILLER}]},
        onEdit: () => showToast('Edit clicked'),
    },
  };

  export const DeleteOnly: Story = {
    args:{
        movie: {id:1, name: "Shutter Island", duration:120, rating:4.8, posterImage:"/images/shutterIsland.jpg", genres:[{id:2, type: TypeOfGenre.MYSTERY}, {id:3, type: TypeOfGenre.THRILLER}]},
        onDelete: () => showToast('Delete clicked'),
    },
  };