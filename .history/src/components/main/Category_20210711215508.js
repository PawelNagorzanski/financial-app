import { render } from '@testing-library/react'
import React, { useEffect, useState } from 'react'
import { Component } from 'react'
import { components } from 'react-select'

class Category extends Component {
  state = {
    categories: ['Other', 'Taxes', 'Bills'],
  }

  render() {
    const { categories } = this.state

    return (
      <>
        {categories.map((category) => {
          return <option>{category}</option>
        })}
      </>
    )
  }
}

export default Category
