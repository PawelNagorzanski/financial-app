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
        text: '',
      },
      viewButton: false,
    }
    this.handleInputChange = this.handleInputChange.bind(this)
    this.handleSubmit = this.handleSubmit.bind(this)
    this.handleAlert = this.handleAlert.bind(this)
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
      this.setState({ alert: { show: false } })
    }, 4000)
  }

  handleSubmit(event) {
    event.preventDefault()
    const loginRequest = {
      email: this.state.email.value,
      password: this.state.password.value,
    }
    if (localStorage.getItem('userId') !== null) {
      this.handleAlert({
        text: 'You are already logged in aplication. Please go to /panel',
      })
      this.setState({ viewButton: true })
      console.log(this.state.alert)
    } else {
      login(loginRequest).then((response) => {
        console.log(response)
      })
      this.props.history.push('/panel')
    }
  }

  handleRouteClick() {
    this.props.history.push('/panel')
  }

  render() {
    // renderButton() {
    //   if (localStorage.getItem('userId') !== null) {
    //     return <button>eo</button>
    //   }
    // }

    return (
      <div>
        {this.state.alert.show && <Alert text={this.state.alert.text} />}

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

        {/* {renderButton()} */}
        {this.state.viewButton && (
          <button onClick={this.handleRouteClick}>Go to /panel</button>
        )}
      </div>
    )
  }
}
export default Login
