import React, {Component} from 'react';
import {Form, FormGroup, Col, FormControl, ControlLabel, Button} from 'react-bootstrap';
import axios from 'axios';

class Products extends Component {

    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = {
            name: this.name.value,
            description: this.description.value,
            category: 1
        }

        axios.post('http://localhost:9090/addProduct/', data).then(form => console.log("Form sent"));
    }

    render() {
        return (
            <Form horizontal onSubmit={this.handleSubmit}>
                <FormGroup controlId="formBasicText">
                    <Col componentClass={ControlLabel} sm={2}>
                        Nazwa
                    </Col>
                    <Col sm={10}>
                        <FormControl type="text"
                                     placeholder="Nazwa Produktu"
                                     inputRef={(ref) => {this.name = ref}} />
                    </Col>
                </FormGroup>
                <FormGroup controlId="formBasicText">
                    <Col componentClass={ControlLabel} sm={2}>
                        Opis
                    </Col>
                    <Col sm={10}>
                        <FormControl type="text"
                                     placeholder="Opis Produktu"
                                     inputRef={(ref) => {this.description = ref}} />
                    </Col>
                </FormGroup>
                <FormGroup>
                    <Col smOffset={2} sm={10}>
                        <Button type="submit">Dodaj</Button>
                    </Col>
                </FormGroup>
            </Form>
        );
    }

}


export default Products;