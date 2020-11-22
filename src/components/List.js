import React from 'react';
import Item from './Item.js';

const List = ({ expenses } ) => {

  return (
    <>
      <div class="list">
      {expenses.map(expense => {
          return (
            <Item expense={expense} key={expense.id}/>
          );
        })}
        
          {expenses.lenght > 0 && (<button>Clear inputs</button>)}
      </div>
        
    </>
  );
}
export default List;
