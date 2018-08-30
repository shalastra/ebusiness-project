import React, {Component} from 'react';
import axios from 'axios';

class Products extends Component {

    constructor() {
        super();
        this.state = {
            products: [],
        };
    }

    componentDidMount() {
        axios.get('http://localhost:9000/products', {
            headers: {
                'Access-Control-Allow-Origin': '*',
            }
        }).then(results => {
                return results.json();
            }).then(data => {
            let products = data.map((prod) => {
                return (
                    <div key={prod.id}>
                        <div className="title">{prod.name}</div>
                        <div>{prod.description}</div>
                        <div>{prod.category}</div>
                    </div>
                )
            })
            this.setState({products: products})
        })
    }

    render() {
        return (
            <div className="products">
                {this.state.products}
            </div>
        )
    }
}

export default Products;