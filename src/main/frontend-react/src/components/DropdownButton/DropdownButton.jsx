import React from 'react';
import { FaChevronDown } from 'react-icons/fa';
import './DropdownButton.css';

const DropdownButton = ({children, open, toggle}) => {
    return (
        <div onClick={toggle} className="dropdown-button">
            {children}
            <span className={"toggle-icon"}> <FaChevronDown /></span>
        </div>
    );
};

export default DropdownButton;