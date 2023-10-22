import React, { ReactEventHandler, useState } from "react";
import {
  Anchor,
  Button,
  Container,
  Form,
  GhostButton,
  Input,
  LeftOverlayPanel,
  Overlay,
  OverlayContainer,
  Paragraph,
  RightOverlayPanel,
  SignInContainer,
  SignUpContainer,
  Title,
  Wrapper,
} from "./LoginPage.styled";
import UserService from "../../services/UserService/UserService";
import { NewUser, User } from "../../models/User";
import { showToast } from "../../components/shared/toast/CustomToast";
import useUser from "../../utils/UserContext/useUser";
import { useNavigate } from "react-router-dom";

export interface Props {
  signinIn?: boolean;
}

const LoginPage = () => {
  const [signInPanel, setSignInPanel] = useState(true);
  const [newUser, setNewUser] = useState<NewUser>({
    name: "",
    username: "",
    password: "",
    dateOfBirth: "",
  });
  const [loginUser, setLoginUser] = useState({
    username: "",
    password: "",
  });
  const { setUser } = useUser();
  const navigate = useNavigate();

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setNewUser((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleLoginInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setLoginUser((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSignUp = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    UserService.register(newUser)
      .then((response) => {
        console.log("User registered:", response.data);
        showToast("Please check your email for verification!");
      })
      .catch((error) => {
        console.error("Error registering user:", error);
      });
  };

  const handleSignIn = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.preventDefault();
    UserService.login(loginUser)
      .then((response) => {
        console.log("User logged in:", response.data);
        showToast("Login Successful!");
        setUser(response.data);
        navigate("/");
        localStorage.setItem("user", JSON.stringify(response.data));
      })
      .catch((error) => {
        console.error("Error logging in:", error);
        showToast("Login failed. Please check your credentials.");
      });
  };

  return (
    <Wrapper>
      <Container>
        <SignUpContainer signinIn={signInPanel}>
          <Form>
            <Title>Create Account</Title>
            <Input
              type="text"
              placeholder="Full Name"
              name="name"
              value={newUser.name}
              onChange={handleInputChange}
            />
            <Input
              type="email"
              placeholder="Email"
              name="username"
              value={newUser.username}
              onChange={handleInputChange}
            />
            <Input
              type="password"
              placeholder="Password"
              name="password"
              value={newUser.password}
              onChange={handleInputChange}
            />
            <Input
              type="date"
              placeholder="Date of birth"
              name="dateOfBirth"
              value={newUser.dateOfBirth}
              onChange={handleInputChange}
            />
            <Button onClick={handleSignUp}>Sign Up</Button>
          </Form>
        </SignUpContainer>

        <SignInContainer signinIn={signInPanel}>
          <Form>
            <Title>Sign in</Title>
            <Input
              type="email"
              placeholder="Email"
              name="username"
              value={loginUser.username}
              onChange={handleLoginInputChange}
            />
            <Input
              type="password"
              placeholder="Password"
              name="password"
              value={loginUser.password}
              onChange={handleLoginInputChange}
            />
            <Anchor href="#">Forgot your password?</Anchor>
            <Button onClick={handleSignIn}>Sigin In</Button>
          </Form>
        </SignInContainer>

        <OverlayContainer signinIn={signInPanel}>
          <Overlay signinIn={signInPanel}>
            <LeftOverlayPanel signinIn={signInPanel}>
              <Title>Welcome Back!</Title>
              <Paragraph>
                To keep connected with us please login with your personal info
              </Paragraph>
              <GhostButton onClick={() => setSignInPanel(true)}>
                Sign In
              </GhostButton>
            </LeftOverlayPanel>

            <RightOverlayPanel signinIn={signInPanel}>
              <Title>Hello, Friend!</Title>
              <Paragraph>
                Enter Your personal details and start journey with us
              </Paragraph>
              <GhostButton onClick={() => setSignInPanel(false)}>
                Sigin Up
              </GhostButton>
            </RightOverlayPanel>
          </Overlay>
        </OverlayContainer>
      </Container>
    </Wrapper>
  );
};

export default LoginPage;
