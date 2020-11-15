import React, {useState} from "react";
import "./App.css";
import Form from "./components/Form.js";
import List from "./components/List.js"
import Alert from "./components/Alert.js"

const initialExpenses = [
  { id: Math.random() * 100000, description: "Taxes", amount: 3000 },
  { id: Math.random() * 100000, description: "Lol", amount: 6969 },
];

function App() {
  const [expenses, setExpeneses] = useState(initialExpenses);
  const [description, setDescription] = useState("");
  const [amount, setAmount] = useState("");
  const [alert, setAlert] = useState({show: false})

  const handleDescription = e => {
    console.log("desc:" + e.target.value);
    setDescription(e.target.value);
  }

  const handleAmount = e => {
    console.log("amo:" + e.target.value);
    setAmount(e.target.value);
  }

  const handleSubmit = e => {
    e.preventDefault();

    if(amount > 0 || description !== "") {
      var singleExpenses = {id: Math.random() * 100000, description, amount}
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
      <Form amount={amount} description={description} 
            handleAmount={handleAmount} handleDescription={handleDescription} 
            handleSubmit={handleSubmit} />
      <List expenses={expenses} />
      <h3>Your balance: </h3> 
      <span>{expenses.reduce((previous, current) => {return (previous += parseInt(current.amount));}, 0)}$</span>
    </>
  );
}

export default App;
