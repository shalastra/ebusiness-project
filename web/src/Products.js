import React, {Component} from 'react';
import axios from 'axios';
import {Button, Table} from 'react-bootstrap';

class Products extends Component {

    constructor(props) {
        super(props);
        this.state = {
            products: [],
        };
    }

    componentDidMount() {
        axios.get('http://localhost:9000/products').then(results => {
            return results.data;
            }).then(data => {
            this.setState({products: data})
        })
    }

    addToCart(id) {
        console.log(id);

        var selectedProduct = this.state.products.find(selected => selected.id === id);
        this.props.handleAddToCart(selectedProduct);
    }

    render() {
        var items = this.state.products.map(product => {
            return <tr key={product.toString()}>
                <td>{product.name}</td>
                <td>{product.description}</td>
                <td>
                    <Button bsStyle="primary" data={product.id} onClick={() => this.addToCart(product.id)}>
                        Do koszyka
                    </Button>
                </td>
            </tr>;
        });

        return (
            <div className="products">
                <Table striped bordered condensed hover>
                    <tbody>
                    {items}
                    </tbody>
                </Table>
            </div>
        )
    }
}

export default Products;