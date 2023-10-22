import React from "react";
import { Card } from "../../genres/GenreCard/GenreCard.styled";
import { User } from "../../../models/User";
import { formatDate } from "../../../utils/functions/formatDateTime";
import Button from "../../shared/button/Button";
import { ButtonSize, ButtonType } from "../../../utils/enums";

export type ConsumerCardProps = {
  consumer: User;
  onBlock: () => void;
};
export default function ConsumerCard({ consumer, onBlock }: ConsumerCardProps) {
  return (
    <Card>
      <h3>{consumer.name}</h3>
      <p>
        <b>Email: </b>
        {consumer.username}
      </p>
      <p>
        <b>Date of birth: </b>
        {formatDate(consumer.dateOfBirth)}
      </p>
      <p>
        <b>Verified: </b>
        {consumer.verified ? "Yes" : "No"}
      </p>
      <p>
        <b>Blocked: </b>
        {consumer.blocked ? "Yes" : "No"}
      </p>
      {!consumer.blocked && (
        <Button
          label="BLOCK"
          variant={ButtonType.PRIMARY}
          size={ButtonSize.STANDARD}
          onClickHandler={onBlock}
        />
      )}
    </Card>
  );
}
