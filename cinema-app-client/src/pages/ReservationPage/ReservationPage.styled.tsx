import styled from "styled-components";
import SeatStatus from "../../models/Enums/SeatStatus";
export const ImageSection = styled.article`
  width: 250px;
  display: flex;
  align-items: center;
  flex-direction: column;
  margin: 20px;
`;
export const SeatBox = styled.div<{ status: SeatStatus }>`
  width: 20px;
  height: 20px;
  margin: 1px;
  border: 1px solid black;
  border-radius: 4px;
  background-color: ${({ status, theme }) => {
    switch (status) {
      case SeatStatus.AVAILABLE:
        return "grey";
      case SeatStatus.OCCUPIED:
        return "red";
      case SeatStatus.SELECTED:
        return theme.colors.main;
      default:
        return "grey";
    }
  }};
  &:hover {
    background-color: ${({ theme }) => theme.colors.main};
    cursor: pointer;
  }
`;

export const SeatsGrid = styled.section<{ columns: number | undefined }>`
  margin-top: 30px;
  display: grid;
  grid-template-columns: repeat(${(props) => props.columns}, 1fr);
  gap: 2px;
  justify-self: center;
`;
export const SeatsContainer = styled.section`
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;
export const Container = styled.section`
  display: flex;
  justify-content: center;
  align-items: flex-start;
  gap: 40px;
  margin-top: 40px;

  @media (max-width: 768px) {
    flex-direction: column;
    align-items: center;
  }
`;

export const TicketContainer = styled.div`
  margin-bottom: 10px;
  width: auto;
`;

export const PriceContainer = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  label {
    border: 2px solid ${({ theme }) => theme.colors.main};
    padding: 10px;
    border-radius: 7px;
  }
  button {
    margin-top: 20px;
  }
  input {
    border: 2px solid ${({ theme }) => theme.colors.main};
    padding: 10px;
    border-radius: 7px;
  }
`;
