import { useEffect, useState } from "react";
import ScreeningsList from "../../components/screenings/ScreeningsList/ScreeningsList";
import Button from "../../components/shared/button/Button";
import Modal from "../../components/shared/modal/Modal";
import {
  StyledPage,
  BtnContainer,
} from "../../components/shared/styled/SharedStyles.styled";
import { ButtonSize, ButtonType } from "../../utils/enums";
import MovieScreening, {
  NewMovieScreening,
} from "../../models/MovieScreenings";
import ScreeningService from "../../services/ScreeningService/ScreeningService";
import { showToast } from "../../components/shared/toast/CustomToast";
import ScreeningForm from "../../components/screenings/ScreeningForm/ScreeningForm";
import ConfirmationForm from "../../components/shared/confirmationForm/ConfirmationForm";

export default function ScreeningsPage() {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [screeningsList, setScreeningsList] = useState<MovieScreening[]>([]);
  const [editingScreening, setEditingScreening] =
    useState<MovieScreening | null>(null);
  const [isConfirmDialogVisible, setIsConfirmDialogVisible] = useState(false);
  const [screeningToDelete, setScreeningToDelete] =
    useState<MovieScreening | null>(null);

  useEffect(() => {
    ScreeningService.getScreenings()
      .then((response) => {
        setScreeningsList(response.data);
      })
      .catch((error) => {
        console.error("Error fetching screenings: ", error);
      });
  }, []);

  const handleEditScreening = (screening: MovieScreening) => {
    setEditingScreening(screening);
    setIsModalVisible(true);
  };

  const onDeleteScreening = (screening: MovieScreening) => {
    setScreeningToDelete(screening);
    setIsConfirmDialogVisible(true);
  };

  const onConfirmDelete = () => {
    if (!screeningToDelete) return;

    ScreeningService.deleteScreening(screeningToDelete.id as number)
      .then(() => {
        setScreeningsList((prevScreenings) =>
          prevScreenings.filter((s) => s.id !== screeningToDelete.id)
        );
        showToast(`Screening ${screeningToDelete.id} deleted successfully`);
      })
      .catch((error) => {
        console.error("Error deleting screening: ", error);
        showToast("Error deleting screening: " + screeningToDelete.id);
      })
      .finally(() => {
        setIsConfirmDialogVisible(false);
        setScreeningToDelete(null);
      });
  };

  const handleFormCancel = () => {
    setEditingScreening(null);
    setIsModalVisible(false);
  };
  const handleDeleteCancel = () => {
    setIsConfirmDialogVisible(false);
  };

  const handleFormSubmit = (movieScreening: NewMovieScreening) => {
    if (movieScreening.movieId === null || movieScreening.dateTime === "") {
      return;
    }
    // EDIT SCREENING
    if (editingScreening && editingScreening.id !== null) {
      ScreeningService.updateScreening(editingScreening.id, movieScreening)
        .then((response) => {
          setScreeningsList((prevScreenings) =>
            prevScreenings.map((s) =>
              s.id === movieScreening.id ? response.data : s
            )
          );
          setEditingScreening(null);
        })
        .catch((error) => {
          console.error("Error updating screening: ", error);
        });
    } else {
      // CREATE SCREENING
      console.log("screening ", movieScreening);
      ScreeningService.createScreening(movieScreening)
        .then((response) => {
          setScreeningsList((prevScreenings) => [
            ...prevScreenings,
            response.data,
          ]);
          setEditingScreening(null);
        })
        .catch((error) => {
          console.error("Error creating screening: ", error);
        });
    }

    setIsModalVisible(false);
  };

  return (
    <StyledPage>
      <h2>Screenings Menagment</h2>
      <BtnContainer>
        <Button
          size={ButtonSize.STANDARD}
          variant={ButtonType.PRIMARY}
          label="Add new screening"
          onClickHandler={() => setIsModalVisible(true)}
        />
      </BtnContainer>
      <Modal isVisible={isModalVisible} onClose={handleFormCancel}>
        <ScreeningForm
          onSubmit={handleFormSubmit}
          onCancel={handleFormCancel}
          initialScreening={editingScreening}
        />
      </Modal>
      <Modal isVisible={isConfirmDialogVisible} onClose={handleDeleteCancel}>
        <ConfirmationForm
          label="Are you sure you want to delete this screening?"
          confirmLabel="Delete"
          onCancel={handleDeleteCancel}
          onConfirm={onConfirmDelete}
        />
      </Modal>
      <ScreeningsList
        screenings={screeningsList}
        onEditScreening={handleEditScreening}
        onDeleteScreening={onDeleteScreening}
      />
    </StyledPage>
  );
}
