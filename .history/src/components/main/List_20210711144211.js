import React from 'react'
import Item from './Item.js'

const List = ({ expenses, setExpeneses }) => {
  const handleDelete = (expense) => {
    const newExpanse = expenses.filter((item) => item.id !== expense.id)
    setExpeneses(newExpanse)
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
