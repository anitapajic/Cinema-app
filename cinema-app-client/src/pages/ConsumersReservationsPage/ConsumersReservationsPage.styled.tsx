import { Tabs, TabList, Tab, TabPanel } from "react-tabs";
import styled from "styled-components";

export const StyledTabs = styled(Tabs)`
  font-family: "Arial", sans-serif;
  width: 95%;
  margin: 40px auto;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
`;

export const StyledTabList = styled(TabList)`
  display: flex;
  flex-wrap: nowrap;
  justify-content: space-around;
  list-style-type: none;
  padding: 0;
  margin: 0;
  border: 2px solid #ccc;
`;

export const StyledTab = styled(Tab)`
  cursor: pointer;
  padding: 15px 20px;
  transition: 0.3s ease;

  &:hover {
    background: #f4f4f4;
  }

  &.react-tabs__tab--selected {
    border-bottom: 3px solid ${({ theme }) => theme.colors.main};
    font-weight: bold;
    background: #fff;
  }
`;

export const StyledTabPanel = styled(TabPanel)`
  border-top: none;
  padding: 20px;
  border-radius: 0 0 5px 5px;
  background-color: transparent;
`;
