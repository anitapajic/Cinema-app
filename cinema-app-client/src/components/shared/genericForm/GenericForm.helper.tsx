import { faClose } from "@fortawesome/free-solid-svg-icons";
import { ButtonType, ButtonSize, IconButtonType } from "../../../utils/enums";
import { GenreItem, GenreSelect, GenresSection, ImagePreview, SelectedGenresList } from "../../movies/MovieForm/MovieForm.styled"
import Button from "../button/Button";
import IconButton from "../iconButton/IconButton";
import { StyledInput } from "../styled/SharedStyles.styled";

export type InputProps = {
    label? : string;
    name : string;
    type : 'text' | 'number' | 'password' | 'date' | 'datetime-local' ; 
    value?: string | number;
    onChange?: (name: string, value: string | number | null) => void;
    required?: boolean;
    isValid : boolean
}

interface SelectProps {
    selectedValue: string;
    handleChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    options: { id:number | null; type: string }[];
    addButtonLabel: string;
    handleAddClick: () => void;
}

export interface ListProps<T> {
    listItem: T[];
    handleItemRemove: (item: T) => void;
}

interface FileInputProps {
    handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
    fileSource: string | null;
    altText: string;
}

export const renderStyledInput = (input : InputProps) => {
    return (
        <StyledInput
                    type={input.type}
                    name={input.name}
                    value={input.value}
                    onChange={(e) => input.onChange?.(input.name ?? "", e.target.value)}
                    required={input.required}
                    isValid={input.isValid}
                  />
    )
}

export const renderSelect = ({
    selectedValue,
    handleChange,
    options,
    addButtonLabel,
    handleAddClick,
}: SelectProps) => {
    return (
        <>
            <GenreSelect
                name={selectedValue}
                onChange={handleChange}>
                    <option value="">Select an option</option>
                {options.map((option) => (
                    <option key={option.id} value={option.type}>
                        {option.type}
                    </option>
                ))}  
            </GenreSelect>
            <Button 
            variant={ButtonType.PRIMARY}
            label={addButtonLabel}
            size={ButtonSize.SMALL}
            onClickHandler={handleAddClick}
            type="button"
        />
    </>
 )
}

export const renderSelectedItems = <T extends { id: null|number, type: string }> ({listItem, handleItemRemove} : ListProps<T>) => (
    <GenresSection>
        <SelectedGenresList>
            {listItem.map((item, index) => (
                <GenreItem key={index}>
                    {item.type}
                    <IconButton icon={faClose} onClickHandler={() => handleItemRemove(item)} variant={IconButtonType.WHITE}/>
                </GenreItem>
            ))}
        </SelectedGenresList>
    </GenresSection>
);

export const renderFileInputSection = ({ handleChange, fileSource, altText } : FileInputProps) => (
    <>               
        <StyledInput 
            type="file" 
            accept="image/*" 
            onChange={handleChange} 
            isValid={true}
        />            
        {fileSource && typeof fileSource === 'string' && (
            <ImagePreview src={fileSource} alt={altText} />
        )}    
    </>
);

