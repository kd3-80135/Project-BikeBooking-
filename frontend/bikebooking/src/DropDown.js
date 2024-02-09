import React, { useState } from 'react';

function Dropdown() {
  const [isDropdownOpen, setDropdownOpen] = useState(false);

  const handleDropdownToggle = () => {
    setDropdownOpen(!isDropdownOpen);
  };
var username = sessionStorage.getItem("userName");
  return (
    <div className="user-dropdown">
      <button onClick={handleDropdownToggle}>{username}</button>

      {isDropdownOpen && (
        <div className="dropdown-content">
          <p><a href='./edit'>Profile</a></p>
          <p><a href='./editAddress'>My Address</a></p>
          <p>Logout</p>
        </div>
      )}
    </div>
  );
}

export default Dropdown;
