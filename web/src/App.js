import React, {Component} from 'react';
import axios from 'axios';
import './App.css';
import Products from "./Products";
import {Nav, Navbar, NavItem} from "react-bootstrap";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {isLoggedIn: false};
    }

    componentDidMount() {
        axios.get('/getCurrentUser').then(data => {
            localStorage.setItem("USER_ID",data.data.email);
            localStorage.setItem("userToken",data.data.token)
        });
    }

    render() {
        let login;
        var userID = localStorage.getItem("USER_ID");
        if ( userID && userID !== "undefined"){
            login = <Nav pullRight>
                <Navbar.Text>{userID}</Navbar.Text>
                <NavItem href='http://localhost:9000/signOut'>Wyloguj</NavItem>
            </Nav>
        } else {
            login = <Nav pullRight>
                <NavItem href='http://localhost:9000/signIn'>Zaloguj</NavItem>
            </Nav>
        }
        return (
            <div className="App">
                <Navbar>
                    <Navbar.Header>
                        <Navbar.Brand>
                            <a href="#home">SKLEP</a>
                        </Navbar.Brand>
                    </Navbar.Header>
                    <Nav>
                        <NavItem>
                            Dodaj produkt
                        </NavItem>
                        <NavItem>
                            Koszyk
                        </NavItem>
                    </Nav>
                    {login}
                </Navbar>
                <Products/>
            </div>
        );
    }
}

export default App;
