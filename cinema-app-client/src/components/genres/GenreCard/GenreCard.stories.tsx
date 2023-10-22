import { Meta, StoryObj } from "@storybook/react";
import { ThemeProvider } from "styled-components";
import { theme } from "../../../utils/data";
import GenreCard from "./GenreCard";

const meta = {
    title: 'GenrePage-components/GenreCard',
    component: GenreCard,
    decorators: [(Story) => <ThemeProvider theme={theme}><Story /></ThemeProvider>],
    tags: ['autodocs']
  } satisfies Meta<typeof GenreCard>
  
  export default meta;
  
  type Story = StoryObj<typeof meta>;

  export const Primary: Story = {
    args:{
        label: 'Genre',
        onEdit: () => alert('Edit clicked'),
        onDelete: () => alert('Delete clicked'),
    },
  };

  export const LongLabel: Story = {
    args:{
        label: 'A Very Long Genre Label That Might Wrap',
        onEdit: () => alert('Edit clicked'),
        onDelete: () => alert('Delete clicked'),
    },
  };

  export const NoActions: Story = {
    args:{
        label: 'No Actions',
    },
  };

  export const EditOnly: Story = {
    args:{
        label: 'Edit only',
        onEdit: () => alert('Edit clicked'),
    },
  };

  export const DeleteOnly: Story = {
    args:{
        label: 'Delete Only',
        onDelete: () => alert('Delete clicked'),
    },
  };