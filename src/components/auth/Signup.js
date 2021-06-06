import React, { Component } from "react";

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

    handleInputChange(e, validationFun) {
        const target = e.target;
        const inputName = target.name;
        const inputValue = target.value;

        this.setState({
            [inputName] : {
                value: inputValue,
                ...validationFun(inputValue)
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
        signup(signupRequest).then(response => {

        })
    }




    render() {
        return (
            <div>
                
            </div>
        )
    }
}