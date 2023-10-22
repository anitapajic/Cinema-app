import {
  FaFacebookF,
  FaInstagram,
  FaLinkedin,
  FaTwitter,
} from "react-icons/fa";

export const theme = {
  colors: {
    main: "#b4e854",
    secondColor: "#263d3d",
    textColor: "white",
  },
  radius: {
    buttons: "4px",
  },
  fontSizes: {
    standard: "14px",
    large: "16px",
    small: "12px",
    header: "30px",
  },
};

export const icons = [
  { href: "https://www.facebook.com", icon: <FaFacebookF /> },
  { href: "https://www.twitter.com", icon: <FaTwitter /> },
  { href: "https://www.instagram.com", icon: <FaInstagram /> },
  { href: "https://www.linkedin.com", icon: <FaLinkedin /> },
];

export const infoItems = [
  { label: "Email", value: "cinemaApp@gmail.com" },
  { label: "Phone", value: "+123456789" },
  { label: "Address", value: "Strumicka 6, Novi Sad" },
];
export const navbarTitle = "Cinema App";

export const menuOptions = [
  { href: "", value: "Home", role: "all" },
  { href: "/login", value: "Sign In/Sign up", role: "guest" },
  { href: "/my-reservations", value: "My reservations", role: "CONSUMER" },
  { href: "/screenings", value: "MovieScreenings", role: "ADMIN" },
  { href: "/consumers", value: "Consumers", role: "ADMIN" },
  { href: "/movies", value: "Movies", role: "ADMIN" },
  { href: "/genres", value: "Genres", role: "ADMIN" },
  { href: "/login", value: "Log Out", role: "logged" },
  { href: "#", value: "Contact", role: "all" },
];
