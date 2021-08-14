import React, { Component } from 'react'
import './App.css'
import Signup from './components/auth/Signup.js'
import Login from './components/auth/Login.js'
import Panel from './components/main/Panel.js'
import Home from './components/main/Home.js'
import { Route, withRouter, Switch, useHistory, Link } from 'react-router-dom'

const App = () => {
  const history = useHistory()

  // <button type="button" onClick={() => history.push('/')}>Go to home</button>
  // <button type="button" onClick={() => history.push('/signup')}>Go to signup</button>
  // <button type="button" onClick={() => history.push('/login')}>Go to login</button>
  // <button type="button" onClick={() => history.push('/panel')}>Go to panel</button>
  return (
    <>
      <Switch>
        <Route path="/signup" component={Signup}></Route>
        {/* <Route path="/" component={Home}></Route> */}
        <Route path="/panel" component={Panel}></Route>
        <Route path="/login" component={Login}></Route>
      </Switch>
    </>
  )
}

export default withRouter(App)
