import React, { Component } from 'react';
import './CartItemList.css';

import CartItem from './CartItem';

import axios from 'axios';
import { map } from 'lodash';
import PlaceOrder from '../../OrderServiceComponent/PlaceOrder/PlaceOrder';

class CartItemList extends Component {
	constructor(props) {
		super(props);

		this.state = {
			cartItems: []
		};
	}

	componentDidMount() {
		axios.get('http://localhost:1337/spring.cartservice.docker.localhost/cart-items')
			.then(res => {
				this.setState({ cartItems: res.data });
			});
	}

	removeFromCart(cartItemId) {
		axios.delete('http://localhost:1337/spring.cartservice.docker.localhost/cart-items/' + cartItemId)
			.then(res => {
				alert('removed from cart')
				axios.get('http://localhost:1337/spring.cartservice.docker.localhost/cart-items')
					.then(res => {
						this.setState({ cartItems: res.data });
					});
			}, err => {
				alert('error: ' + err)
			});
	}

	render() {
		return (
			<div className="cart-item-list">
				<PlaceOrder cartItems={this.state.cartItems}/>

				<div className="cart-items">
				{
					map(this.state.cartItems, (cartItem) => (
						<CartItem cartItem={cartItem} key={cartItem.id} removeFromCart={() => {this.removeFromCart(cartItem.id)}}/>
					))
				}
				</div>

			</div>
		);
	}
}

export default CartItemList;