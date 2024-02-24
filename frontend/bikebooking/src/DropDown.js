import React, { useState } from 'react';

const DropDown = () => {
  const [isDropdownOpen, setDropdownOpen] = useState(false);

  const handleDropdownToggle = (e) => {
    e.preventDefault();
    setDropdownOpen(!isDropdownOpen);
  };

  var username = sessionStorage.getItem("userName");

  return (
    <div style={{ position: 'relative', display: 'inline-block' }}>
      <button onClick={handleDropdownToggle} style={{ padding: '10px' }}>
        <span style={{ marginRight: '5px' }}>{username}</span> &#9660;
      </button>

      {isDropdownOpen && (
        <div
          style={{
            position: 'absolute',
            top: '100%',
            left: '0',
            backgroundColor: '#f9f9f9',
            minWidth: '160px',
            boxShadow: '0px 8px 16px 0px rgba(0, 0, 0, 0.2)',
            zIndex: 1,
          }}
        >
          <p><a href='./edit' style={{textDecoration:'none',font:'-moz-initial',fontSize:16, fontWeight:'bold'}}>Profile</a></p>
          <p><a href='./editAddress'style={{textDecoration:'none',font:'-moz-initial',fontSize:16, fontWeight:'bold'}}>My Address</a></p>
          <p><a href='/orders' style={{textDecoration:'none',font:'-moz-initial',fontSize:16, fontWeight:'bold'}} >Orders</a></p>
        </div>
      )}
    </div>
  );
}

export default DropDown;