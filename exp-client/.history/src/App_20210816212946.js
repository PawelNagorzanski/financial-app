import React from 'react'
import './App.css'
import Signup from './components/auth/Signup.js'
import Login from './components/auth/Login.js'
import Panel from './components/main/Panel.js'
import { Route, withRouter, Switch, useHistory } from 'react-router-dom'
let history = useHistory()
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
      <button
        onClick={() => {
          this.props.history.push('/login')
        }}
      >
        Go to /login
      </button>
      <button
        onClick={() => {
          this.props.history.push('/panel')
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
