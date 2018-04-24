import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Button } from 'react-bootstrap';
import './CartItem.css';

import axios from 'axios';
import { upperCase } from 'lodash';

class CartItem extends Component {
	constructor(props) {
		super(props);

		this.state = {
			product: {},
			price: 0
		}
	}

	componentDidMount() {
		axios.get('http://localhost:1337/spring.storeservice.docker.localhost/products/' + this.props.cartItem.productId)
			.then(res => {
				this.setState({ product: res.data });
			});

		axios.get('http://localhost:1337/spring.priceservice.docker.localhost/products/' + this.props.cartItem.productId + '/current-price')
			.then(res => {
				this.setState({ price: res.data.unitPrice });
			});
	}

	render() {
		const { id, quantity } = this.props.cartItem;

		return (
			<div className="cart-item">
				<div className="cart-item-product-name">{upperCase(this.state.product.name)}</div>
				<div className="cart-item-quantity">quantity: {quantity}</div>
				<div className="cart-item-quantity">total amount: {this.state.price * quantity}</div>
				<div className="cart-item-remove-button">
					<Button bsSize="small" bsStyle="danger" onClick={() => {
						this.props.removeFromCart()
					}}>
						Remove
					</Button>
				</div>
				<div className="cart-item-id">id: {id}</div>

			</div>
		);
	}
}


CartItem.propTypes = {
	product: PropTypes.object,
}

export default CartItem;