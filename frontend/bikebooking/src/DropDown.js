import React, { useState } from 'react';

function Dropdown() {
  const [isDropdownOpen, setDropdownOpen] = useState(false);

  const handleDropdownToggle = (e) => {
    e.preventDefault(); 
    setDropdownOpen(!isDropdownOpen);
  };

  var username = sessionStorage.getItem("userName");

  return (
    <div className="user-dropdown" style={{ position: 'relative' }}>
      <button onClick={handleDropdownToggle} className="profile-button">
        <span className="username">{username}</span> &#9660;
      </button>

      {isDropdownOpen && (
        <div className="dropdown-content">
          <p><a href='./edit'>Profile</a></p>
          <p><a href='./editAddress'>My Address</a></p>
          <p className="logout"><a href='/' onClick={(e) => { e.preventDefault(); window.location.href = '/'; }}>Logout</a></p>
        </div>
      )}
    </div>
  );
}

export default Dropdown;
