import React from "react";
import DropdownButton from "../DropdownButton/DropdownButton";
import DropdownContent from "../DropdownContent/DropdownContent";
import './Dropdown.css';

const Dropdown = ({buttonText, content}) => {
    const [open, setOpen] = React.useState(false);

    const toggleOpen = () => {
        setOpen((open) => !open);
    }

    return <div className="dropdown">
        <DropdownButton toggle={toggleOpen} open={open}>{buttonText}</DropdownButton>
        <DropdownContent open={open}>{content}</DropdownContent>
    </div>
}

export default Dropdown;