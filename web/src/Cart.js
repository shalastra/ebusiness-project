import React, {Component} from 'react';
import {Button, Table} from "react-bootstrap";

class Cart extends Component {

    constructor(props) {
        super(props);

        this.state = {
            selectedProducts: []
        }
    }

    componentDidMount() {
        this.setState({selectedProducts: this.props.data});
    }

    removeProduct(productToRemove) {
        var remainingProducts = this.state.selectedProducts
            .filter(product => product.id !== productToRemove.id);
        this.setState({selectedProducts: remainingProducts});
    }

    render() {
        let selectedProducts;

        if ( !this.state.selectedProducts.length ) {
            selectedProducts = (<tr><td>Koszyk jest pusty.</td></tr>)
        } else {
            selectedProducts = this.state.selectedProducts.map(product => {
                return <tr key={product.toString()}>
                    <td>{product.name}</td>
                    <td>
                        <Button bsStyle="danger" data={product.id} onClick={() => this.removeProduct(product)}>
                            Usuń
                        </Button>
                    </td>
                </tr>;
            });
        }


        return (
            <div className="cart">
                <Table striped bordered condensed hover>
                    <tbody>
                    {selectedProducts}
                    </tbody>
                </Table>
            </div>
        )
    }
}

export default Cart;