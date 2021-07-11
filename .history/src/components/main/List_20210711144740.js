import React from 'react'
import Item from './Item.js'
import { expeseDelete } from '../api/APIUtils.js'

const List = ({ expenses, setExpeneses }) => {
  const handleDelete = (expense) => {
    const newExpanse = expenses.filter((item) => item.id !== expense.id)
    setExpeneses(newExpanse)
    expeseDelete(expense.id)
    console.log(expense.id)
  }

  return (
    <>
      {expenses.map((expense) => {
        return (
          <Item
            expense={expense}
            key={expense.id}
            handleDelete={() => handleDelete(expense)}
            setExpeneses={setExpeneses}
          />
        )
      })}
    </>
  )
}
export default List
