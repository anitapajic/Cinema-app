import { StoryObj, Meta } from '@storybook/react';
import Button from './Button'; 
import { ThemeProvider } from 'styled-components';
import { theme } from '../../../utils/data';
import { ButtonSize, ButtonType } from '../../../utils/enums';

const meta = {
    title: 'Shared-components/Button',
    component: Button,
    decorators: [(Story) => <ThemeProvider theme={theme}><Story /></ThemeProvider>],
    parameters: {
     layout: 'centered',
    },
    tags: ['autodocs']
  } satisfies Meta<typeof Button>;
  
  export default meta;
  type Story = StoryObj<typeof meta>;

  export const Primary: Story = {
    args: {
      label: 'Primary button',
      variant: ButtonType.PRIMARY
    },
  };

  export const PrimaryLarge: Story = {
    args: {
      label: 'Primary large button',
      variant: ButtonType.PRIMARY,
      size : ButtonSize.LARGE
    },
  };

  export const PrimarySmall: Story = {
    args: {
      label: 'Primary small button',
      variant: ButtonType.PRIMARY,
      size: ButtonSize.SMALL
    },
  };

  export const Secondary: Story = {
    args: {
      label: 'Secondary button',
      variant: ButtonType.SECONDARY  
    },
  };

  export const SecondaryLarge: Story = {
    args: {
      label: 'Secondary large button',
      variant: ButtonType.SECONDARY ,
      size: ButtonSize.LARGE
    },
  };
  export const SecondarySmall: Story = {
    args: {
      label: 'Secondary small button',
      variant: ButtonType.SECONDARY ,
      size: ButtonSize.SMALL
    },
  };