import MovieScreening from "../../../models/MovieScreenings"
import { ItemsListStyle } from "../../shared/styled/SharedStyles.styled";
import ScreeningCard from "../ScreeningCard/ScreeningCard";

export type ScreeningsListProps = {
    screenings : MovieScreening[];
    onEditScreening : (screening : MovieScreening) => void;
    onDeleteScreening : (screening : MovieScreening) => void;
}

export default function ScreeningsList({screenings, onEditScreening, onDeleteScreening} : ScreeningsListProps){
    return (
        <ItemsListStyle>
            {screenings.map((screening : MovieScreening) => (
                <ScreeningCard key={screening.id}
                                screening={screening}
                                onEdit={() => onEditScreening(screening)}
                                onDelete={() => onDeleteScreening(screening)}/>
            ))}
        </ItemsListStyle>
    )
}