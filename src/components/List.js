import React from 'react';
import Item from './Item.js';

const List = ({ expenses, categories }) => {

  return (
    <>
      <div class="list">
      {expenses.map(expense => {
          return (
            <Item expense={expense} categories={categories.name} key={expense.id}/>
          );
        })}
        
          {expenses.lenght > 0 && (<button>Clear inputs</button>)}
      </div>
        
    </>
  );
}
export default List;
