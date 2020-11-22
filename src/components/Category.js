import React, { useEffect, useState } from 'react'

const Category = () => {

  const [categories, setCategories] = useState([]);

  useEffect(() => { fetch('/api/categories/') 
    .then(response => response.json())
    .then(data => setCategories(data))
    .catch((e) => console.log("Error"))
  })

  return (
  <>
    {categories.map(category => {
      return <option>{category.name}</option>; // Give value of picked category to Item
    })}
  </>
  );
}


export default Category;