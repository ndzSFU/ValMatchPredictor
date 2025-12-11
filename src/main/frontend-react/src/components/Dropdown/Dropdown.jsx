import React, { useState, useEffect, useRef} from 'react';
import DropdownButton from "../DropdownButton/DropdownButton";
import DropdownContent from "../DropdownContent/DropdownContent";
import './Dropdown.css';

const Dropdown = ({buttonText, content, open, setOpen}) => {
    const dropdownRef = useRef();

    const toggleOpen = () => setOpen((open) => !open);

    useEffect(() => {
        if (!open) return;

        const handleClick = (event) => {
            // Don’t close if clicking inside the dropdown
            if (dropdownRef.current && dropdownRef.current.contains(event.target)) return;
            setOpen(false);
        };

        document.addEventListener('click', handleClick);
        return () => document.removeEventListener('click', handleClick);
    }, [open]);

    // Wrap onSelect to close dropdown
    const handleSelect = () => {
        //if (onSelect) onSelect(value);
        setOpen(false);
    };


    return (
        <div className="dropdown" ref={dropdownRef}>
            <DropdownButton toggle={toggleOpen} open={open}>
                {buttonText}
            </DropdownButton>

            <DropdownContent open={open}>
                {React.Children.map(content, (child) =>
                    React.isValidElement(child)
                        ? React.cloneElement(child, {
                            onClick: () => {
                                handleSelect();
                            },
                        })
                        : child
                )}
            </DropdownContent>
        </div>
    );
};

export default Dropdown;