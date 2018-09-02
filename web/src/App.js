import React, {Component} from 'react';
import axios from 'axios';
import './App.css';
import Products from "./Products";
import ProductForm from "./ProductForm";
import {Nav, Navbar, NavItem, Modal} from "react-bootstrap";

class App extends Component {

    constructor(props, context) {
        super(props, context);

        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);

        this.state = {
            isLoggedIn: false,
            show: false
        };
    }

    handleClose() {
        this.setState({show: false});
    }

    handleShow() {
        this.setState({show: true});
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
                        <NavItem onClick={this.handleShow}>
                            Dodaj produkt
                        </NavItem>

                        <Modal show={this.state.show} onHide={this.handleClose}>
                            <Modal.Header closeButton>
                                <Modal.Title>Dodawanie Produktu</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                <ProductForm/>
                            </Modal.Body>
                        </Modal>
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
