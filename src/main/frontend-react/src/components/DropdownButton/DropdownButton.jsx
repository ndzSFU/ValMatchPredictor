import React from 'react';
import { FaChevronDown, FaChevronUp } from 'react-icons/fa';
import './DropdownButton.css';

const DropdownButton = ({children, open, toggle, handleSelect}) => {
    const handleClick = (e) => {
        e.stopPropagation(); // prevent clicks from bubbling up to parent
        toggle(open);
    };

    return (
        <button
            type="button"
            onClick={handleClick}
            className={`dropdown-button ${open ? "button-open" : ""}`}
        >
            <span className="button-text">{children}</span>
            <span className="toggle-icon">
                {open ? <FaChevronUp /> : <FaChevronDown />}
            </span>
        </button>
    );
};

export default DropdownButton;