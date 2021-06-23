import React from 'react';

const Item = ({ expense: { id, description, amount, SelectedCategory }, handleDelete }) => {
  return (
    <div class="list">
      <tr>
        <td>{amount}$</td>
        <td>{SelectedCategory}</td>
        <td>{description}</td> 
        <button class="delete_button" onClick={handleDelete}>Delete</button>
      </tr>
      
    </div>
  );
};

export default Item;
