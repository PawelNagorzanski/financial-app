import React from 'react';

const Item = ({ expense: { id, description, amount }}) => {

  return (
    <div>
      <tr>
        <td>{description}</td> 
        <td>{amount}$</td>
      </tr>
    </div>
  );
};

export default Item;
