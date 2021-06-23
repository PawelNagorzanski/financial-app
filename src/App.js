import React, { Component } from "react";
import "./App.css";
import Form from "./components/main/Form.js";
import List from "./components/main/List.js"
import Alert from "./components/main/Alert.js"
import Signup from "./components/auth/Signup.js"
import Panel from "./components/main/Panel.js"
import { components } from "react-select";
import {
  Route,
  withRouter,
  Switch
} from 'react-router-dom';

class App extends Component {
 
render() {
  return (
        <Switch>
          <Route path="/signup" component={Signup}></Route>
          <Route path="/panel" component={Panel}></Route>
          
        </Switch>
    );
}
}

export default withRouter(App);
