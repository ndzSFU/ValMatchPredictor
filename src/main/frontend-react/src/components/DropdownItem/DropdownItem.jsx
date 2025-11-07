import React from 'react';
import './DropdownItem.css';

const DropdownItem = ({children, onClick, disabled}) => {

    const handleClick = (e) => {
        e.stopPropagation();
        if (!disabled && onClick) onClick();
    };

    return (
        <div
            onClick={handleClick}
            className={`dropdown-item ${disabled ? "disabled" : ""}`}
            style={{
                opacity: disabled ? 0.5 : 1,
                pointerEvents: disabled ? "none" : "auto",
                cursor: disabled ? "not-allowed" : "pointer"
            }}
        >
            {children}
        </div>
    );
}

export default DropdownItem;
