import React from 'react';

const Item = ({ expense: { id, description, amount} }, { categories }) => {

  return (
    <div class="list">
      <tr>
        <td>{description}</td> 
        <td>{categories}</td>
        <td>{amount}$</td>
      </tr>
    </div>
  );
};

export default Item;
