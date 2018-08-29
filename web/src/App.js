import React, { Component } from 'react';
import axios from 'axios';
import './App.css';
import Products from './Products'
import ProductForm from './ProductForm'

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {isLoggedIn: false};
    }

    componentDidMount() {
        axios.get('/api/getCurrentUser').then(data => {
            localStorage.setItem("USER_ID",data.data.name);
            localStorage.setItem("userToken",data.data.token)
        });
    }

    render() {
        return <h1>Hello</h1>
    }
}

export default App;
