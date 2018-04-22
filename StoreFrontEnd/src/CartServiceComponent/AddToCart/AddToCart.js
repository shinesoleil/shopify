import React, { Component } from 'react';
import { ButtonGroup, Button } from 'react-bootstrap';
import PropTypes from 'prop-types';
import './AddToCart.css';

import axios from 'axios/index';

class AddToCart extends Component {
	constructor(props) {
		super(props);

		this.state = {
			quantity: 0,
		}
	}

	addQuantity() {
		this.setState({ quantity: this.state.quantity + 1 });
	}

	reduceQuantity() {
		if (this.state.quantity !== 0) {
			this.setState({ quantity: this.state.quantity - 1 });
		}
	}

	addToCart() {
		const quantity = this.state.quantity;

		if (quantity === 0) {
			alert('please add product quantity');
		} else {
			axios.post('http://localhost:1337/spring.cartservice.docker.localhost/cart-items', {
				product_id: this.props.productId,
				quantity: this.state.quantity,
			})
				.then(res => {
					alert('product added to cart')
				}, err => {
					alert('error: ' + err)
				});
		}
	}

	render() {
		return (
			<div className="add-to-cart">
				<div className="add-to-cart-buttons">
					<ButtonGroup>
						<Button bsSize="small" bsStyle="success" onClick={() => {
							this.addQuantity();
						}}>+</Button>
						<Button bsSize="small" bsStyle="danger" onClick={() => {
							this.reduceQuantity();
						}}>-</Button>
					</ButtonGroup>
				</div>
				<div className="add-to-cart-quantity">
					<span>{this.state.quantity}</span>
				</div>
				<div className="add-to-cart-add">
					<Button bsSize="small" bsStyle="primary" onClick={() => {
						this.addToCart();
					}}>Add to Cart</Button>
				</div>
			</div>
		);
	}
}

AddToCart.propTypes = {
	productId: PropTypes.string,
}

export default AddToCart;