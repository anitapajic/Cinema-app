import { styled } from "styled-components";

export const StyledPage = styled.section`
  position: relative;
  padding-top: 50px;

  h2 {
    text-align: center;
    font-weight: bold;
    font-size: ${({ theme }) => theme.fontSizes.header};
    color: ${({ theme }) => theme.colors.secondColor};
  }
  input {
    text-align: center;
  }
`;
export const HeaderWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const StyledForm = styled.form`
  display: flex;
  flex-direction: column;
  align-items: start;
  gap: 10px;
  padding: 20px;
  background-color: white;
  border-radius: 5px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
`;

export const StyledLabel = styled.label`
  margin: 5px;
  font-size: ${({ theme }) => theme.fontSizes.large};
  color: ${({ theme }) => theme.colors.secondColor};
  font-weight: bold;
  padding: 5px;
`;

export const StyledInput = styled.input<{ isValid?: boolean }>`
  width: 150px;
  padding: 8px;
  border: 1.5px solid;
  border-color: ${({ theme, isValid }) =>
    isValid ? theme.colors.main : "red"};
  border-radius: ${({ theme }) => theme.radius.buttons};
  font-size: ${({ theme }) => theme.fontSizes.standard};
  margin: 5px;
`;

export const ButtonGroup = styled.div`
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-left: 55px;
`;

export const ButtonContainer = styled.article`
  position: absolute;
  top: 10px;
  right: 10px;
`;

export const ItemsListStyle = styled.div`
  margin-top: 50px;
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
  justify-content: space-around;

  @media (max-width: 768px) {
    gap: 8px;
  }
`;
export const BtnContainer = styled.div`
  position: absolute;
  top: 0;
  right: 0;
  padding: 70px;
  @media (max-width: 680px) {
    position: static;
    text-align: center;
    padding: 10px 0;
  }
`;

export const Card = styled.article`
  width: 200px;
  padding: 5px;
  position: relative;
  border: 2px solid ${({ theme }) => theme.colors.main};
  margin: 20px;
  text-align: center;
  border-radius: 10px;
  overflow: hidden;

  &:hover {
    border: 2px solid ${({ theme }) => theme.colors.secondColor};
  }

  h2 {
    color: ${({ theme }) => theme.colors.secondColor};
    font-size: 24px;
  }

  p {
    margin-bottom: 0;
    color: ${({ theme }) => theme.colors.secondColor};
    font-size: 15px;
  }
`;

export const GenresSection = styled.section`
  margin-top: 10px;
`;
export const ScreeningsSection = styled.section`
  margin-top: 10px;
`;

export const GenreList = styled.ul`
  list-style: none;
  padding: 0;
  margin-bottom: 10px;
  p {
    margin-top: 2px;
    margin-bottom: 8px;
  }
`;
export const ScreeningList = styled.ul`
  list-style: none;
  padding: 0;
  margin-bottom: 10px;
  p {
    margin-top: 2px;
    margin-bottom: 8px;
  }
`;

export const GenreItem = styled.li`
  display: inline-block;
  margin-right: 8px;
  margin-bottom: 3px;
  background-color: ${({ theme }) => theme.colors.secondColor};
  color: ${({ theme }) => theme.colors.textColor};
  border-radius: 4px;
  padding: 2px 6px;
  font-size: 14px;
  margin-top: 5px;
`;
export const ScreeningItem = styled.li`
  display: inline-block;
  margin-right: 8px;
  margin-bottom: 3px;
  background-color: ${({ theme }) => theme.colors.main};
  color: ${({ theme }) => theme.colors.secondColor};
  border-radius: 4px;
  padding: 2px 6px;
  font-size: 14px;
  margin-top: 5px;
`;
