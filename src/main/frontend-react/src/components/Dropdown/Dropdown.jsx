import React, { useState, useEffect, useRef} from 'react';
import DropdownButton from "../DropdownButton/DropdownButton";
import DropdownContent from "../DropdownContent/DropdownContent";
import './Dropdown.css';

const Dropdown = ({buttonText, content, onSelect}) => {
    const [open, setOpen] = React.useState(false);
    const dropdownRef = useRef();

    const toggleOpen = () => setOpen((open) => !open);

    useEffect(() => {
        const handler = (event) => {
            if(dropdownRef.current && !dropdownRef.current.contains(event.target)) {
                setOpen(false);
            }
        };
        document.addEventListener("click", handler);
        return () => document.removeEventListener("click", handler);
    }, [dropdownRef]);

    // Wrap onSelect to close dropdown
    const handleSelect = (value) => {
        if (onSelect) onSelect(value);
        setOpen(false);
    };

    return (
        <div className="dropdown" ref={dropdownRef}>
            <DropdownButton toggle={toggleOpen} open={open}>{buttonText}</DropdownButton>
            <DropdownContent open={open}>
                {content}
            </DropdownContent>
        </div>
    );
};

export default Dropdown;