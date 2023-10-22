import Footer from "./components/shared/footer/Footer";
import Navbar from "./components/shared/navbar/Navbar";
import {
  navbarTitle,
  icons,
  infoItems,
  theme,
  menuOptions,
} from "./utils/data";
import { ThemeProvider } from "styled-components";
import MyRoutes from "./utils/routes";
import { BrowserRouter as Router } from "react-router-dom";

import { AppContainer, ContentContainer } from "./App.styled";
import { useRef, useState } from "react";
import { ToastContainer } from "react-toastify";
import { UserProvider } from "./utils/UserContext/userContext";
import { User } from "./models/User";
import { MantineProvider } from "@mantine/core";

function App() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [user, setUser] = useState<User | null>(() => {
    const savedUser = localStorage.getItem("user");
    return savedUser ? JSON.parse(savedUser) : null;
  });
  const footerRef = useRef(null);
  const determineRole = (user: User | null): string => {
    if (!user) return "guest";
    return user.role;
  };

  const role = determineRole(user);
  const finalOptions = menuOptions.filter(
    (option) =>
      option.role === "all" ||
      option.role === role ||
      (option.role === "logged" && user)
  );

  return (
    <UserProvider value={{ user, setUser }}>
      <ThemeProvider theme={theme}>
        <MantineProvider>
          <Router>
            <ToastContainer className="toast-container" />
            <AppContainer className="App">
              <Navbar
                footerRef={footerRef}
                title={navbarTitle}
                isMenuOpen={isMenuOpen}
                setIsMenuOpen={setIsMenuOpen}
                options={finalOptions}
              />
              <ContentContainer isMenuOpen={isMenuOpen}>
                <MyRoutes />
              </ContentContainer>
              <Footer ref={footerRef} icons={icons} infoItems={infoItems} />
            </AppContainer>
          </Router>
        </MantineProvider>
      </ThemeProvider>
    </UserProvider>
  );
}

export default App;
