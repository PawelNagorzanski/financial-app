import React, { Component } from "react";
import { signup } from '../api/APIUtils.js';
import Alert from "../main/Alert.js";
import { NAME_MIN_LENGTH, } from "../api/constants/constants.js";
import { Link } from 'react-router-dom';

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
        this.handleSubmit = this.handleSubmit.bind(this);
        
    }

    handleInputChange(event, validationFunction) {
        const target = event.target;
        const inputName = target.name;
        const inputValue = target.value;

        console.log(inputName, inputValue);

        this.setState({
            [inputName] : {
                value: inputValue,
                ...validationFunction(inputValue)
            }
        })
    }

    handleSubmit(event) {
        event.preventDefault();

        const signupRequest = {
            name: this.state.name.value,
            username: this.state.username.value,
            email: this.state.email.value,
            password: this.state.password.value,
        };

        signup(signupRequest)
        .then(response => {
            console.log(response)
            this.props.history.push("/login");
        }).catch(error => {
            <Alert text={"Signup error! Please try again"} />
        })
    }

    render() {
        return (
            <div>
                <h3>Register your account</h3>
                <form onSubmit={this.handleSubmit}>
                    <input placeholder="Your name" name="name" defaultValue={this.state.name.value} onChange={(event) => this.handleInputChange(event, validateName)} />
                    <input placeholder="Your username" name="username" defaultValue={this.state.username.value} onChange={(event) => this.handleInputChange(event, validateUsername)} />
                    <input placeholder="Your email" name="email" defaultValue={this.state.email.value} onChange={(event) => this.handleInputChange(event, validateEmail)} />
                    <input placeholder="Your password" name="password" valdefaultValueue={this.state.password.value} onChange={(event) => this.handleInputChange(event, validatePassword)} />
                    <button type="submit" name="submit" class="submit" htmlType="submit" >Submit</button>
                </form>
            </div>
        )
    }
}

const validateName = (name) => {
    if(name.lenght < NAME_MIN_LENGTH) {
        <Alert text={"Your name is too short! Please enter value with 4 characters"} />
        console.log("heyy")
    }
}

const validateUsername = (name) => {
    
}

const validateEmail = (name) => {
    
}

const validatePassword = (name) => {
    
}

export default Signup;