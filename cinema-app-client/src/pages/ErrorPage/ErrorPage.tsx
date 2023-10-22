import { useNavigate } from "react-router";
import {
  StyledContainer,
  StyledDiv,
  StyledTitle,
  StyledText,
  StyledGroup,
  StyledButton,
  StyledErrorPage,
} from "./ErrorPage.styled";
import { Illustration } from "./Illustration";
import { StyledPage } from "../../components/shared/styled/SharedStyles.styled";

interface TextProps {
  c?: string;
  size?: string;
  ta?: string;
}

export function ErrorPage() {
  const navigate = useNavigate();

  const handleButtonClick = () => {
    navigate("/");
  };

  return (
    <StyledErrorPage>
      <StyledContainer>
        <StyledDiv>
          <p>404</p>
          <div>
            <StyledTitle>Nothing to see here</StyledTitle>
            <StyledText c="dimmed" size="lg" ta="center">
              Page you are trying to open does not exist. You may have mistyped
              the address, or the page has been moved to another URL. If you
              think this is an error contact support.
            </StyledText>
            <StyledGroup justify="center">
              <StyledButton size="md" onClick={handleButtonClick}>
                Take me back to home page
              </StyledButton>
            </StyledGroup>
          </div>
        </StyledDiv>
      </StyledContainer>
    </StyledErrorPage>
  );
}
