import React from 'react'
import Item from './Item.js'
import { expenseDelete } from '../api/APIUtils.js'

const List = ({ expenses, setExpeneses, SelectedCategory }) => {
  const handleDelete = (expense) => {
    const newExpanse = expenses.filter((item) => item.id !== expense.id)
    setExpeneses(newExpanse)
    expenseDelete(expense.id)
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
            SelectedCategory={SelectedCategory}
          />
        )
      })}
    </>
  )
}
export default List
