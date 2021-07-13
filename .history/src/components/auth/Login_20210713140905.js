import React, { Component } from 'react'
import Alert from '../../components/main/Alert'
import { login, siema } from '../api/APIUtils.js'

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
      alert: {
        show: false,
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

  handleAlert({ text }) {
    this.setState({ alert: { show: true, text } })
    setTimeout(() => {
      this.setState({ show: false })
    }, 2000)
  }

  handleSubmit(event) {
    event.preventDefault()
    const loginRequest = {
      email: this.state.email.value,
      password: this.state.password.value,
    }
    if (localStorage !== null) {
      this.handleAlert({ text: 'You are already logged in aplication' })
    } else {
      login(loginRequest).then((response) => {
        console.log(response)
      })
      this.props.history.push('/panel')
    }
  }

  render() {
    return (
      <div>
        {alert.show && <Alert text={alert.text} />}
        <Alert />
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
