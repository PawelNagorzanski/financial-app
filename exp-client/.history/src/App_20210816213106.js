import React from 'react'
import './App.css'
import Signup from './components/auth/Signup.js'
import Login from './components/auth/Login.js'
import Panel from './components/main/Panel.js'
import { Route, withRouter, Switch, useHistory } from 'react-router-dom'
const App = () => {
  let history = useHistory()
  return (
    <>
      <button
        onClick={() => {
          history.push('/signup')
        }}
      >
        Go to /signup
      </button>
      <button
        onClick={() => {
          history.push('/login')
        }}
      >
        Go to /login
      </button>
      <button
        onClick={() => {
          history.push('/panel')
        }}
      >
        Go to /panel
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
