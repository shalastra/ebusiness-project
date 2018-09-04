import React, {Component} from 'react';
import {Table} from "react-bootstrap";

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

    render() {
        let selectedProducts;

        if ( !this.state.selectedProducts.length ) {
            selectedProducts = (<tr><td>Koszyk jest pusty.</td></tr>)
        } else {
            selectedProducts = this.state.selectedProducts.map(product => {
                return <tr key={product.toString()}>
                    <td>{product.name}</td>
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