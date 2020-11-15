
import React from "react";

const Form = ({handleAmount, handleDescription, handleSubmit, description, amount}) => {
      return (
        <form onSubmit={handleSubmit}>
          <h3>Input description:</h3>
          <input 
            type="text" 
            id="desc" 
            name="desc" 
            placeholder="Description of input. Example: Food "
            onChange={handleDescription}
            value={description} 
            />

          <h3>Input amount:</h3>
          <input 
            type="value" 
            id="amou" 
            name="amou" 
            placeholder="How much? Example: 10"
            onChange={handleAmount}
            value={amount} 
            />

          <button type="submit">Submit</button>
        </form>
      );
  }
  export default Form;