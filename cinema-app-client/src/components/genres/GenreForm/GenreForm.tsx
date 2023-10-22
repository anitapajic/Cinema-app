import { useState } from "react";
import GenericForm, { FormInput } from "../../shared/genericForm/GenericForm";
import { renderStyledInput } from "../../shared/genericForm/GenericForm.helper";
import { showToast } from "../../shared/toast/CustomToast";
import Genre from "../../../models/Genre";

export type GenreFormProps = {
    onSubmit: (genre: Genre) => void;
    onCancel: () => void;
    initialGenre?: Genre | null;
}

const validateGenre = (value: string) => {
    const isValid = value.trim().length > 0 && isNaN(Number(value)); 
    return isValid; 
};

export default function GenreForm({onSubmit, onCancel, initialGenre} : GenreFormProps){
    const [genre, setGenre] = useState(initialGenre || { id: null, type: "" });
    const [isGenreValid, setIsGenreValid] = useState(false);

    const handleGenreChange = (name: string, value: string | number | null) => {
        if (typeof value === 'string') {
            setGenre((prevGenre) => ({ ...prevGenre, [name]: value }));
            setIsGenreValid(validateGenre(value)); 
        }
    };

    const handleSubmit = () => {
        if (isGenreValid) { 
            onSubmit(genre);
            setGenre({ id: null, type: "" });
            setIsGenreValid(false); 
        } else {
            showToast("Invalid type: " + genre.type);
        }
    };

    const isEditMode = Boolean(initialGenre);
    const buttonLabel = isEditMode ? "Edit Genre" : "Add New Genre";

    const formInputs: FormInput[] = [
        { 
            label: 'Genre Type: ',
            customRender: () => (
                <>                 
                    {renderStyledInput({
                        type: 'text',
                        name: 'type',
                        value: genre.type,
                        onChange: handleGenreChange,
                        required: true,
                        isValid: isGenreValid
                    })}
                </>
            ),
        },      
    ];

    return (      
        <GenericForm 
            inputs={formInputs}
            onSubmit={handleSubmit}
            onCancel={onCancel}
            submitButtonLabel={buttonLabel}
            cancelButtonLabel="Cancel"
        />       
    );   
}