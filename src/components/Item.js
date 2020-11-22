import React from 'react';

const Item = ({ expense: { id, description, amount, SelectedCategory } }) => {

  return (
    <div class="list">
      <tr>
        <td>{description}</td> 
        <td>{SelectedCategory}</td>
        <td>{amount}$</td>
      </tr>
    </div>
  );
};

export default Item;
