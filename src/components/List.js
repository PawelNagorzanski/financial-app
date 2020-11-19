import React from 'react';
import Item from './Item.js';

const List = ({ expenses }, { categories }) => {

  return (
    <>
      {categories.map(category => { return category })}
      <div class="list">
      {expenses.map(expense => {
          return (
            <Item expense={expense} category={categories.name} key={expense.id}/>
          );
        })}
        
          {expenses.lenght > 0 && (<button>Clear inputs</button>)}
      </div>
        
    </>
  );
}
export default List;
