import React from 'react';
import { FaChevronDown } from 'react-icons/fa';
import './DropdownButton.css';

const DropdownButton = ({children}) => {
    return (
        <div className="dropdown-button">
            {children}
            <span className={"toggle-icon"}> <FaChevronDown /></span>
        </div>
    );
};

export default DropdownButton;