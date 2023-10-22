import { StoryObj, Meta } from '@storybook/react';
import Footer from './Footer'; 
import { FaFacebookF, FaTwitter, FaInstagram, FaLinkedin } from 'react-icons/fa';
import { ThemeProvider } from 'styled-components';
import { theme } from '../../../utils/data';

const meta = {
    title: 'Shared-components/Footer',
    component: Footer,
    decorators: [(Story) => <ThemeProvider theme={theme}><Story /></ThemeProvider>],
    tags: ['autodocs']
} satisfies Meta<typeof Footer>;
  
  export default meta;
  type Story = StoryObj<typeof meta>;

  
  export const Primary: Story = {
    args: {
      icons :[
        { href: 'https://www.facebook.com', icon: <FaFacebookF /> },
        { href: 'https://www.twitter.com', icon: <FaTwitter /> },
        { href: 'https://www.instagram.com', icon: <FaInstagram /> },
        { href: 'https://www.linkedin.com', icon: <FaLinkedin /> },
      ],
      infoItems: [
        { label: 'Email', value: 'cinemaApp@gmail.com' },
        { label: 'Phone', value: '+123456789' },
        { label: 'Address', value: 'Strumicka 6, Novi Sad' },
    ]
      }
    };
  