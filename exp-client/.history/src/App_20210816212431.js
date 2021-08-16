import React from 'react'
import './App.css'
import Signup from './components/auth/Signup.js'
import Login from './components/auth/Login.js'
import Panel from './components/main/Panel.js'
import { Route, withRouter, Switch } from 'react-router-dom'

const App = () => {
  return (
    <>
      <button
        onClick={() => {
          this.props.history.push('/signup')
        }}
      >
        Go to /signup
      </button>
      <Switch>
        <Route path="/signup" component={Signup}></Route>
        <Route path="/panel" component={Panel}></Route>
        <Route path="/login" component={Login}></Route>
      </Switch>
    </>
  )
}

export default withRouter(App)
