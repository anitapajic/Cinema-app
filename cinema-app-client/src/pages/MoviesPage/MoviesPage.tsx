import { useEffect, useState } from "react";
import Movie from "../../models/Movie";
import MoviesList from "../../components/movies/MoviesList/MoviesList";
import Button from "../../components/shared/button/Button";
import { BtnContainer, StyledPage } from "../../components/shared/styled/SharedStyles.styled";
import { ButtonSize, ButtonType } from "../../utils/enums";
import Modal from "../../components/shared/modal/Modal";
import MovieForm from "../../components/movies/MovieForm/MovieForm";
import MovieService from "../../services/MovieService/MovieService";
import { showToast } from "../../components/shared/toast/CustomToast";
import ConfirmationForm from "../../components/shared/confirmationForm/ConfirmationForm";


export default function MoviesPage() {
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [editingMovie, setEditingMovie] = useState<Movie | null>(null);
    const [moviesList, setMoviesList] = useState<Movie[]>([]);
    const [isConfirmDialogVisible, setIsConfirmDialogVisible] = useState(false);
    const [movieToDelete, setMovieToDelete] = useState<Movie | null>(null);

    useEffect(() => {
        MovieService.getMovies().then(response => {
            setMoviesList(response.data); 
        }).catch(error => {
            console.error("Error fetching movies: ", error);
        });
    }, []);
    
    const handleEditMovie = (movie: Movie) => {
        setEditingMovie(movie);
        setIsModalVisible(true);
    };

    const onDeleteMovie = (movie: Movie) => {
        setMovieToDelete(movie);
        setIsConfirmDialogVisible(true);
    };

    const onConfirmDelete = () => {
        if (!movieToDelete) return;
    
        MovieService.deleteMovie(movieToDelete.id as number) 
            .then(() => {
                setMoviesList(prevMovies => prevMovies.filter(m => m.id !== movieToDelete.id));
                showToast(`Movie ${movieToDelete.id} deleted successfully`);
            })
            .catch(error => {
                console.error("Error deleting movie: ", error);  
                showToast("Error deleting movie: " + movieToDelete.name);     
            })
            .finally(() => {
                setIsConfirmDialogVisible(false);
                setMovieToDelete(null);
            });
    };
    const handleDeleteCancel = () => {
        setIsConfirmDialogVisible(false);
    };

    // DELETE MOVIE
    // const onDeleteMovie = (movie: Movie) => {       
    //     MovieService.deleteMovie(movie.id as number) 
    //         .then(() => {
    //             setMoviesList(prevMovies => prevMovies.filter(m => m.id !== movie.id));
    //         })
    //         .catch(error => {
    //             console.error("Error deleting movie: ", error);  
    //             showToast("Error deleting movie: " + movie.name);     
    //         })
    // };

    const handleFormSubmit = (movie: Movie) => {
        if(movie.name==='' || movie.duration===0){
            return
        }
        // EDIT MOVIE
        if (editingMovie && editingMovie.id !== null) {
            MovieService.updateMovie(editingMovie.id, movie).then(response => {
               
                setMoviesList(prevMovies => 
                    prevMovies.map(m => (m.id === movie.id ? response.data : m))
                );
                setEditingMovie(null);
            }).catch(error => {
                console.error("Error updating movie: ", error);
            });
       
        } else {  
            // CREATE MOVIE
            console.log('movie ', movie);      
            MovieService.createMovie(movie).then(response => {
                setMoviesList(prevMovies => [...prevMovies, response.data]);
                setEditingMovie(null);
            }).catch(error => {
                console.error("Error creating movie: ", error);
            });
        }   
         
        setIsModalVisible(false); 
    };

    const handleFormCancel = () => {
        setEditingMovie(null);
        setIsModalVisible(false);
    };
    return (
        <StyledPage>
            <h2>Movie Menagment</h2>
            <BtnContainer>
                <Button 
                    size={ButtonSize.STANDARD} 
                    variant={ButtonType.PRIMARY}
                    label="Add new movie"
                    onClickHandler={() => setIsModalVisible(true)} 
                /> 
            </BtnContainer>
            <Modal isVisible={isModalVisible} onClose={handleFormCancel}>
                <MovieForm onSubmit={handleFormSubmit} onCancel={handleFormCancel} initialMovie={editingMovie}/>
            </Modal>
            <Modal isVisible={isConfirmDialogVisible} onClose={handleDeleteCancel}>
                <ConfirmationForm
                                    label="Are you sure you want to delete this movie?"
                                    confirmLabel="Delete"
                                    onCancel={handleDeleteCancel}
                                    onConfirm={onConfirmDelete}/>
            </Modal>
            <MoviesList movies={moviesList} onEditMovie={handleEditMovie} onDeleteMovie={onDeleteMovie}/>
        </StyledPage>

    )
}