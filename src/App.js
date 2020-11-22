import React, { useState } from "react";
import "./App.css";
import Form from "./components/Form.js";
import List from "./components/List.js"
import Alert from "./components/Alert.js"

function App() {
  const initialExpenses = [
    { id: Math.random() * 100000, description: "", amount: 0, SelectedCategory: [] },
  ];

  const [expenses, setExpeneses] = useState(initialExpenses);
  const [description, setDescription] = useState("");
  const [amount, setAmount] = useState("");
  const [alert, setAlert] = useState({show: false});
  const [SelectedCategory, setSelectedCategory] = useState([]);
  
  console.log(SelectedCategory);
  const handleDescription = e => {
    console.log("desc:" + e.target.value);
    setDescription(e.target.value);
  }

  const handleAmount = e => {
    console.log("amo:" + e.target.value);
    setAmount(e.target.value);
  }

  const handleCategory = e => {
    const value = e.target.value;
    setSelectedCategory(value);
  }

  const handleSubmit = e => {
    e.preventDefault();

    if(amount > 0 || description !== "") {
      var singleExpenses = {id: Math.random() * 100000, description, amount, SelectedCategory}
      setExpeneses([...expenses, singleExpenses]);
      setAmount("");
      setDescription("");
      handleAlert({text: "Added"});
    } else {
      handleAlert({text: "Value cannot be empty"});
    }
  }

  const handleAlert = ({ text }) => {
    setAlert({show: true, text});
    setTimeout(() => {setAlert({show: false})}, 2000)
  }

  return (
    <>
      {alert.show && <Alert text={alert.text} />}
      <Alert />
      <div class="row">
        <div class="column">
          <h3>Your balance: </h3> 
          <span>{expenses.reduce((previous, current) => {return (previous += parseInt(current.amount));}, 0)}$</span>
        </div>
        <Form amount={amount}
              description={description} 
              SelectedCategory={SelectedCategory}
              handleAmount={handleAmount} 
              handleDescription={handleDescription}
              handleCategory={handleCategory} 
              handleSubmit={handleSubmit} />
      </div>
      <List expenses={expenses} />
    </>
  );
}

export default App;
