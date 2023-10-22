import { StoryObj, Meta } from "@storybook/react";
import Navbar from "./Navbar";
import { theme } from "../../../utils/data";
import { ThemeProvider } from "styled-components";
import { BrowserRouter as Router } from "react-router-dom";

const meta = {
  title: "Shared-components/Navbar",
  component: Navbar,
  decorators: [
    (Story) => (
      <ThemeProvider theme={theme}>
        <Router>
          <Story />
        </Router>
      </ThemeProvider>
    ),
  ],
  tags: ["autodocs"],
} satisfies Meta<typeof Navbar>;

export default meta;

type Story = StoryObj<typeof meta>;

export const Primary: Story = {
  args: {
    label: "Primary navbar",
    title: "Cinema app",
    isMenuOpen: false,
    options: [
      { href: "", value: "Home" },
      { href: "/login", value: "Sign In/Sign up" },
      { href: "#", value: "Contact" },
    ],
  },
};

export const ConsumerLoggedIn: Story = {
  args: {
    label: "Consumer navbar",
    role: "consumer",
    title: "Cinema app",
    isMenuOpen: false,
    options: [
      { href: "", value: "Home" },
      { href: "/my-reservations", value: "My reservations" },
      { href: "/login", value: "Log Out" },
      { href: "#", value: "Contact" },
    ],
  },
};

export const AdminLoggedIn: Story = {
  args: {
    label: "Admin navbar",
    role: "admin",
    title: "Cinema app",
    isMenuOpen: false,
    options: [
      { href: "", value: "Home" },
      { href: "/screenings", value: "MovieScreenings" },
      { href: "/all-reservations", value: "Reservations" },
      { href: "/movies", value: "Movies" },
      { href: "/genres", value: "Genres" },
      { href: "/consumers", value: "Consumers" },
      { href: "#", value: "Contact" },
      { href: "/login", value: "Log Out" },
    ],
  },
};
