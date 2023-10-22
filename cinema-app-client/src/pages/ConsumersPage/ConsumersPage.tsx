import React, { useEffect, useState } from "react";
import { StyledPage } from "../../components/shared/styled/SharedStyles.styled";
import ConsumersList from "../../components/consumers/ConsumersList/ConsumersList";
import { User } from "../../models/User";
import UserService from "../../services/UserService/UserService";
import { showToast } from "../../components/shared/toast/CustomToast";
import ConfirmationForm from "../../components/shared/confirmationForm/ConfirmationForm";
import Modal from "../../components/shared/modal/Modal";

export default function ConsumersPage() {
  const [consumers, setConsumers] = useState<User[]>([]);
  const [isConfirmDialogVisible, setIsConfirmDialogVisible] = useState(false);
  const [userToBlock, setUserToBlock] = useState<User | null>(null);

  const fetchAllConsumers = () => {
    UserService.getAllConsumers()
      .then((response) => {
        setConsumers(response.data);
      })
      .catch((error) => {
        console.error("Error fetching consumers: ", error);
      });
  };

  useEffect(() => {
    fetchAllConsumers();
  }, []);

  const onBlockUser = (user: User) => {
    setUserToBlock(user);
    setIsConfirmDialogVisible(true);
  };

  const handleBlockCancel = () => {
    setIsConfirmDialogVisible(false);
  };

  const onConfirmBlock = () => {
    if (!userToBlock) return;

    UserService.blockUser(userToBlock.id)
      .then(() => {
        const index = consumers.findIndex(
          (consumer) => consumer.id === userToBlock.id
        );

        const updatedConsumers = [...consumers];
        updatedConsumers[index] = {
          ...updatedConsumers[index],
          blocked: true,
        };

        setConsumers(updatedConsumers);
        setUserToBlock(null);
        setIsConfirmDialogVisible(false);
      })
      .catch((error) => {
        console.error("Error blocking user: ", error);
        showToast("Error blocking user: " + userToBlock.id);
      });
  };

  return (
    <StyledPage>
      <h2>Consumers Menagment</h2>
      <Modal isVisible={isConfirmDialogVisible} onClose={handleBlockCancel}>
        <ConfirmationForm
          label="Are you sure you want to block this user?"
          confirmLabel="BLOCK"
          onCancel={handleBlockCancel}
          onConfirm={onConfirmBlock}
        />
      </Modal>
      <ConsumersList consumers={consumers} onBlockUser={onBlockUser} />
    </StyledPage>
  );
}
