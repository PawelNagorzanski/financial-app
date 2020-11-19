import React, {useEffect ,useState} from "react";
import "./App.css";
import Form from "./components/Form.js";
import List from "./components/List.js"
import Alert from "./components/Alert.js"

function App() {
  const initialExpenses = [
    { id: Math.random() * 100000, description: "Heyy", amount: 3000, categories: [] },
    { id: Math.random() * 100000, description: "Lol", amount: 6969, categories: [] },
  ];

  const [expenses, setExpeneses] = useState(initialExpenses);
  const [description, setDescription] = useState("");
  const [amount, setAmount] = useState("");
  const [alert, setAlert] = useState({show: false})
  const [categories, setCategories] = useState([]);
  
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
      var singleExpenses = {id: Math.random() * 100000, description, amount, categories}
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

  useEffect(() => {
    fetch('/api/categories')
    .then(response => response.json())
    .then((response) => setCategories(response))

    .catch(() => console.log("Error")) 
  }, [])

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
              categories={categories}
              handleAmount={handleAmount} 
              handleDescription={handleDescription} 
              handleSubmit={handleSubmit} />
      </div>
      <List expenses={expenses}
            categories={categories} />
    </>
  );
}

export default App;
