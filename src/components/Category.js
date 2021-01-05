import { render } from '@testing-library/react';
import React, { useEffect, useState } from 'react'
import { Component } from 'react';
import { components } from 'react-select';

class Category extends Component {

  state = {
    isLoading: true,
    categories: [],
  };

  async componentDidMount() {
    const response = await fetch('/api/categories/');
    const body = await response.json();
    this.setState({ categories: body, isLoading: false });
  }

  // const [categories, setCategories] = useState([]);

  // useEffect(() => { fetch('/api/categories/') 
  //   .then(response => response.json())
  //   .then(data => setCategories(data))
  //   .catch((e) => console.log("Error"))
  // }, [categories] ) 
render() {
  const {categories, isLoading} = this.state;
  
  if (isLoading) {
    return <h2>Loading...</h2>
  }
  return (
  <>
    {categories.map(category => {
      return <option>{category.name}</option>;
    })}
  </>
  );
}
}

export default Category;