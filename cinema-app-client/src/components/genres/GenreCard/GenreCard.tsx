import { Card} from './GenreCard.styled'; 
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons';
import IconButton from '../../shared/iconButton/IconButton';
import { ButtonContainer } from '../../shared/styled/SharedStyles.styled';
import { IconButtonType } from '../../../utils/enums';

export type GenreCardProps = {
    label : string;
    onEdit: () => void;
    onDelete: () => void;
}

export default function GenreCard({label, onEdit, onDelete}:GenreCardProps){

    return(
        <Card>
            <h3>{label}</h3>
            <ButtonContainer>
            <IconButton 
                icon={faEdit} 
                onClickHandler={onEdit}
                variant={IconButtonType.DARK} 
            />           
            <IconButton 
                icon={faTrash} 
                onClickHandler={onDelete}
                variant={IconButtonType.DARK} 
            />
            </ButtonContainer>
        </Card>
    )
}