import styled from "styled-components";

export const GenreSelect = styled.select`
  margin-left: 3px;
  margin-bottom: 5px;
  padding: 8px;
  font-size: 16px;
  margin-right: 4px;
  border: 1.5px solid ${({ theme }) => theme.colors.main};
`;

export const GenresSection = styled.section`
  margin-top: 10px;
`;

export const SelectedGenresList = styled.ul`
  list-style-type: none;
  padding: 0;
`;

export const GenreItem = styled.li`
  background-color: ${({ theme }) => theme.colors.secondColor};
  border: none;
  color: ${({ theme }) => theme.colors.textColor};
  padding: 4px 10px;
  text-align: center;
  text-decoration: none;
  display: block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 10px;
  width: fit-content;
`;

export const ImagePreview = styled.img`
  display: flex;
  flex-direction: column;
  max-width: 200px;
  max-height: 300px;
  margin: 10px;
`;

export const IconButtonContainer = styled.div`
  margin-left: 5px;
`;
