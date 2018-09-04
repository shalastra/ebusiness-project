import React, {Component} from 'react';
import axios from 'axios';
import './App.css';
import Products from "./Products";
import ProductForm from "./ProductForm";
import Cart from "./Cart";
import {Modal, Nav, Navbar, NavItem} from "react-bootstrap";

class App extends Component {

    constructor(props, context) {
        super(props, context);

        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleCloseCart = this.handleCloseCart.bind(this);
        this.handleShowCart = this.handleShowCart.bind(this);

        this.state = {
            isLoggedIn: false,
            show: false,
            showCart: false,
            selectedProducts: []
        };
    }

    handleAddToCart(selectedProduct){
        console.log(selectedProduct);

        if(!this.state.selectedProducts.some(product => product.name === selectedProduct.name)) {
            this.state.selectedProducts.push(selectedProduct);
        }
    }

    handleClose() {
        this.setState({show: false});
    }

    handleShow() {
        this.setState({show: true});
    }

    handleCloseCart() {
        this.setState({showCart: false});
    }

    handleShowCart() {
        this.setState({showCart: true});
    }

    componentDidMount() {
        axios.get('/getCurrentUser').then(data => {
            localStorage.setItem("USER_ID",data.data.email);
            localStorage.setItem("userToken",data.data.token)
        });
    }

    render() {
        let login;
        let cart;
        var userID = localStorage.getItem("USER_ID");
        if ( userID && userID !== "undefined"){
            cart = (<NavItem onClick={this.handleShowCart}>
                    Koszyk
                </NavItem>);
            login = <Nav pullRight>
                <Navbar.Text>{userID}</Navbar.Text>
                <NavItem href='http://localhost:9000/signOut'>Wyloguj</NavItem>
            </Nav>
        } else {
            cart = (<div></div>);
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

                        {cart}

                        <Modal show={this.state.showCart} onHide={this.handleCloseCart}>
                            <Modal.Header closeButton>
                                <Modal.Title>Koszyk</Modal.Title>
                            </Modal.Header>
                            <Modal.Body>
                                <Cart data={this.state.selectedProducts}/>
                            </Modal.Body>
                        </Modal>
                    </Nav>
                    {login}
                </Navbar>
                <Products handleAddToCart={this.handleAddToCart.bind(this)} />
            </div>
        );
    }
}

export default App;
