import React, { useEffect, useState } from 'react'
import './App.css'
import Form from './Form.js'
import List from './List.js'
import Alert from './Alert.js'
import { expensePost, expenseGet } from '../api/APIUtils'
import { components } from 'react-select'

function App() {
  const initialExpenses = [
    {
      // id: Math.random() * 100000,
      description: 'Example',
      amount: 0,
      SelectedCategory: ['Other'],
    },
  ]

  const [expenses, setExpeneses] = useState(initialExpenses)
  const [description, setDescription] = useState('')
  const [amount, setAmount] = useState('')
  const [alert, setAlert] = useState({ show: false })
  const [SelectedCategory, setSelectedCategory] = useState([])

  const handleDescription = (e) => {
    setDescription(e.target.value)
  }

  const handleAmount = (e) => {
    setAmount(e.target.value)
  }

  useEffect(() => {
    expenseGet()
      .then((json) => setExpeneses(json))
      .then((json) => console.log(json))
  }, [])

  const handleCategory = (e) => {
    setSelectedCategory(e.target.value)
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    const id = localStorage.getItem('userId')
    if (amount > 0 || description !== '') {
      var singleExpenses = {
        //id: Math.random() * 100000,
        description,
        amount,
        SelectedCategory,
        user: { id },
      }
      setExpeneses([...expenses, singleExpenses])
      expensePost(singleExpenses)
      setAmount('')
      setDescription('')
      handleAlert({ text: 'Added' })
    } else {
      handleAlert({ text: 'Value cannot be empty' })
    }
  }

  const handleAlert = ({ text }) => {
    setAlert({ show: true, text })
    setTimeout(() => {
      setAlert({ show: false })
    }, 2000)
  }

  return (
    <>
      {alert.show && <Alert text={alert.text} />}
      <Alert />
      <div class="row">
        <div class="column">
          <h3>Your balance: </h3>
          <span>
            {expenses.reduce((previous, current) => {
              return (previous += parseInt(current.amount))
            }, 0)}
            $
          </span>
        </div>
        <Form
          amount={amount}
          description={description}
          SelectedCategory={SelectedCategory}
          handleAmount={handleAmount}
          handleDescription={handleDescription}
          handleCategory={handleCategory}
          handleSubmit={handleSubmit}
        />
      </div>
      <List
        expenses={expenses}
        setExpeneses={setExpeneses}
        SelectedCategory={SelectedCategory}
      />
    </>
  )
}

export default App
