import React, { Component } from "react";
import { signup } from '../api/APIUtils';
import Alert from '../Alert'

class Signup extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: {
                value: ''
            },
            email: {
                value: ''
            },
            username: {
                value: ''
            },
            password: {
                value: ''
            },
        }
        this.handleInputChange = this.handleInputChange.bind(this);
    }

    handleInputChange(e, validationFunction) {
        const target = e.target;
        const inputName = target.name;
        const inputValue = target.value;

        this.setState({
            [inputName] : {
                value: inputValue,
                ...validationFunction(inputValue)
            }
        })
    }

    handleSubmit(e) {
        e.preventDefault();

        const signupRequest = {
            name: this.state.name.value,
            username: this.state.username.value,
            email: this.state.email.value,
            password: this.state.password.value,
        };
        signup(signupRequest)
        .then(res => {
            this.props.history.push("/login");

        }).catch(error => {
            <Alert text={"Signup error! Please try again"} />
        })
    }




    render() {
        return (
            <div>
                
            </div>
        )
    }
}