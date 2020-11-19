import React from 'react'

const Category = ({categories}) => {
  return (
  <>
    {categories.map(category => {
      return (<option>{category.name}</option>);
    })}
  </>
  );
}


export default Category;