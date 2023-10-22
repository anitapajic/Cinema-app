import { useEffect, useState } from "react";
import Genre from "../../models/Genre";
import { ButtonSize, ButtonType } from "../../utils/enums";
import GenresList from "../../components/genres/GenresList/GenresList";
import Modal from "../../components/shared/modal/Modal";
import GenreForm from "../../components/genres/GenreForm/GenreForm";
import {
  BtnContainer,
  StyledPage,
} from "../../components/shared/styled/SharedStyles.styled";
import Button from "../../components/shared/button/Button";
import "react-toastify/dist/ReactToastify.css";
import { showToast } from "../../components/shared/toast/CustomToast";
import GenreService from "../../services/GenreService/GenreService";
import ConfirmationForm from "../../components/shared/confirmationForm/ConfirmationForm";

export default function GenresPage() {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [editingGenre, setEditingGenre] = useState<Genre | null>(null);
  const [genres, setGenres] = useState<Genre[]>([]);
  const [isConfirmDialogVisible, setIsConfirmDialogVisible] = useState(false);
  const [genreToDelete, setGenreToDelete] = useState<Genre | null>(null);

  console.log(process.env.REACT_APP_API_BASE_URL);

  useEffect(() => {
    GenreService.getGenres()
      .then((response) => {
        setGenres(response.data);
      })
      .catch((error) => {
        console.error("An error occurred while fetching the genres", error);
      });
  }, []);

  const handleEditGenre = (genre: Genre) => {
    setEditingGenre(genre);
    setIsModalVisible(true);
  };

  const onDeleteGenre = (genre: Genre) => {
    setGenreToDelete(genre);
    setIsConfirmDialogVisible(true);
  };

  const onConfirmDelete = () => {
    if (!genreToDelete) return;

    GenreService.deleteGenre(genreToDelete.id as number)
      .then(() => {
        setGenres((prevGenres) =>
          prevGenres.filter((g) => g.id !== genreToDelete.id)
        );
        showToast(`Genre ${genreToDelete.id} deleted successfully`);
      })
      .catch((error) => {
        // TODO:
        console.error("Error deleting genre: ", error);
        showToast("Error deleting genre: " + genreToDelete.type);
      })
      .finally(() => {
        setIsConfirmDialogVisible(false);
        setGenreToDelete(null);
      });
  };

  const handleDeleteCancel = () => {
    setIsConfirmDialogVisible(false);
  };

  const handleFormSubmit = (genre: Genre) => {
    if (!genre.type) {
      showToast("Genre cannot be empty!");
      return;
    }
    const doesGenreExist = genres.some(
      (g) => g.type.toLowerCase() === genre.type.toLowerCase()
    );

    // EDIT GENRE
    if (editingGenre) {
      if (
        doesGenreExist &&
        editingGenre.type.toLowerCase() !== genre.type.toLowerCase()
      ) {
        showToast("Genre already exists!");
        return;
      }

      if (editingGenre.id !== null) {
        GenreService.updateGenre(editingGenre.id, {
          id: editingGenre.id,
          type: genre.type,
        })
          .then((updatedGenre) => {
            setGenres((prevGenres) =>
              prevGenres.map((genre) =>
                genre.id === editingGenre.id ? updatedGenre.data : genre
              )
            );
            showToast("Genre updated successfully!");
          })
          .catch((error) => {
            console.error("Failed to update genre", error);
            showToast("Failed to update genre!");
          })
          .finally(() => {
            setIsModalVisible(false);
            setEditingGenre(null);
          });
      }
    } else {
      if (doesGenreExist) {
        showToast("Genre already exists!");
        return;
      }

      GenreService.createGenre({ id: null, type: genre.type })
        .then((newGenre) => {
          setGenres((prevGenres) => [...prevGenres, newGenre.data]);
          showToast("Genre added successfully!");
        })
        .catch((error) => {
          console.error("Failed to create genre", error);
          showToast("Failed to create genre!");
        })
        .finally(() => {
          setIsModalVisible(false);
        });
    }

    setIsModalVisible(false);
  };

  const handleFormCancel = () => {
    setEditingGenre(null);
    setIsModalVisible(false);
  };

  return (
    <StyledPage>
      <h2>Genre Menagment</h2>
      <BtnContainer>
        <Button
          size={ButtonSize.STANDARD}
          variant={ButtonType.PRIMARY}
          label="Add new genre"
          onClickHandler={() => setIsModalVisible(true)}
        />
      </BtnContainer>
      <Modal isVisible={isModalVisible} onClose={handleFormCancel}>
        <GenreForm
          onSubmit={handleFormSubmit}
          onCancel={handleFormCancel}
          initialGenre={editingGenre}
        />
      </Modal>
      <Modal isVisible={isConfirmDialogVisible} onClose={handleDeleteCancel}>
        <ConfirmationForm
          label="Are you sure you want to delete this genre?"
          confirmLabel="Delete"
          onCancel={handleDeleteCancel}
          onConfirm={onConfirmDelete}
        />
      </Modal>
      <GenresList
        genres={genres}
        onEditGenre={handleEditGenre}
        onDeleteGenre={onDeleteGenre}
      />
    </StyledPage>
  );
}
