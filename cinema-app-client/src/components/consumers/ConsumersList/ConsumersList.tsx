import React from "react";
import { User } from "../../../models/User";
import { ItemsListStyle } from "../../shared/styled/SharedStyles.styled";
import ConsumerCard from "../ConsumerCard/ConsumerCard";

export type ConsumersListProps = {
  consumers: User[];
  onBlockUser: (user: User) => void;
};

export default function ConsumersList({
  consumers,
  onBlockUser,
}: ConsumersListProps) {
  return (
    <ItemsListStyle>
      {consumers.map((consumer: User) => (
        <ConsumerCard
          key={consumer.id}
          consumer={consumer}
          onBlock={() => onBlockUser(consumer)}
        />
      ))}
    </ItemsListStyle>
  );
}
