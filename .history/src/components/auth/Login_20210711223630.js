import React, { Component } from 'react'
import { login, siema } from '../api/APIUtils.js'
import { Link } from 'react-router-dom'
import { ACCESS_TOKEN } from '../api/constants/constants.js'
import { Form, Input, Button, Icon, notification } from 'antd'
const FormItem = Form.Item
class Login extends Component {
  constructor(props) {
    super(props)
    this.state = {
      email: {
        value: '',
      },
      password: {
        value: '',
      },
    }
    this.handleInputChange = this.handleInputChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
  }

  handleInputChange(event) {
    const target = event.target
    const inputName = target.name
    const inputValue = target.value

    this.setState({
      [inputName]: {
        value: inputValue,
      },
    })
  }

  handleSubmit(event) {
    event.preventDefault()
    const loginRequest = {
      email: this.state.email.value,
      password: this.state.password.value,
    }

    login(loginRequest).then((response) => {
      console.log(response)
    })
    this.props.history.push('/login')
  }

  render() {
    return (
      <div>
        <h3>Login</h3>
        <form onSubmit={this.handleSubmit}>
          <input
            placeholder="Your email"
            name="email"
            defaultValue={this.state.email.value}
            onChange={(event) => this.handleInputChange(event)}
          />
          <input
            placeholder="Your password"
            name="password"
            valdefaultValueue={this.state.password.value}
            onChange={(event) => this.handleInputChange(event)}
          />
          <button type="submit" name="submit" class="submit" htmlType="submit">
            Submit
          </button>
        </form>
        <button onClick={siema}>Siema</button>
      </div>
    )
  }
}
export default Login
