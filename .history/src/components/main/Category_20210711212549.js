import { render } from '@testing-library/react'
import React, { useEffect, useState } from 'react'
import { Component } from 'react'
import { components } from 'react-select'

class Category extends Component {
  state = {
    isLoading: true,
    categories: ['Taxes', 'Bills'],
  }

  render() {
    const { categories, isLoading } = this.state

    return (
      <>
        {categories.map((category) => {
          return <option>{category.value}</option>
        })}
      </>
    )
  }
}

export default Category
