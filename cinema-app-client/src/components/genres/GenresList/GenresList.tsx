import GenreCard from "../GenreCard/GenreCard";
import Genre from "../../../models/Genre";
import { showToast } from "../../shared/toast/CustomToast";
import { ItemsListStyle } from "../../shared/styled/SharedStyles.styled";

export type GenreListProps = {
    genres : Genre[];
    onEditGenre: (genre: Genre) => void;
    onDeleteGenre: (genre: Genre) => void;
}

export default function GenresList({genres, onEditGenre, onDeleteGenre} : GenreListProps){
    return (
        <ItemsListStyle>
            {genres.map((genre: Genre) => (
                <GenreCard key={genre.id}
                            label={genre.type} 
                            onEdit={() => onEditGenre(genre)}
                            onDelete={() => onDeleteGenre(genre)}
                />
            ))}
        </ItemsListStyle>
      
    )
}

