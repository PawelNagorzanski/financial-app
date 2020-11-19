
import React from "react";
import Category from "./Category";

const Form = ({handleAmount, handleDescription, handleSubmit, description, amount, categories}) => {
      return (
        <form onSubmit={handleSubmit}>
            <div class="column">
              <h3>Input amount:</h3>
              <input 
                class="input"
                type="number"
                placeholder="How much"
                onChange={handleAmount}
                value={amount} 
                />
            </div>
            <div class="column">
              <h3>Input description:</h3>
              <input 
                class="input1"
                placeholder="Description of input "
                onChange={handleDescription}
                value={description} 
                />
            </div>
            <div class="column">
              <label for="category-select">
            <div class="row">
              <h3>Choose category: </h3>
            </div>
            <div class="column">
              <div class="select-box">
                <select>
                  <Category categories={categories}/>
                </select>
              <button type="submit" class="submit">Submit</button>     
              </div>
            </div>
              </label>
            </div>
        </form>
      );
  }
  export default Form;