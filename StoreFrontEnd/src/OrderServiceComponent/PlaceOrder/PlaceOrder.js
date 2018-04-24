import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Form, FormGroup, FormControl, Button, ControlLabel } from 'react-bootstrap';
import './PlaceOrder.css';

import axios from 'axios';
import { omit } from 'lodash';

class PlaceOrder extends Component {
	handleSumbit(event) {
		event.preventDefault();

		// change object name format
		const cartItems = this.props.cartItems.map(cartItem => {
			let item =  {
				...cartItem,
				product_id: cartItem.productId,
			};
			return omit(item, ['id','productId']);
		});

		console.log(cartItems)

		axios.post('http://localhost:1337/localhost:8080/orders', {
			name: this.name.value,
			address: this.address.value,
			phone: this.phone.value,
			order_items: cartItems,
		})
			.then(res => {
				console.log('suc');
				alert('order creation success');
			}, error => {
				console.log('err')
				alert('order creation error');
			});
	}

	//
	// getSomething(productId) {
	// 	axios.get('http://localhost:1337/spring.storeservice.docker.localhost/products/' + productId)
	// 		.then(res => {
	// 			this.setState({ product: res.data });
	// 		});
	//
	// 	axios.get('http://localhost:1337/spring.priceservice.docker.localhost/products/' + productId + '/current-price')
	// 		.then(res => {
	// 			this.setState({ price: res.data.unitPrice });
	// 		});
	// }

	render() {
		return (
			<div className="place-order">
				<Form inline onSubmit={this.handleSumbit.bind(this)}>
					<FormGroup controlId="formInlineName" style={{ margin: '0 auto' }}>
						<ControlLabel>name: </ControlLabel>{' '}
						<FormControl type="text" placeholder="Input name" inputRef={ref => {
							this.name = ref;
						}}/>
					</FormGroup>{' '}

					<FormGroup controlId="formInlineName" style={{ margin: '0 auto' }}>
						<ControlLabel>address: </ControlLabel>{' '}
						<FormControl type="text" placeholder="Input address" inputRef={ref => {
							this.address = ref;
						}}/>
					</FormGroup>{' '}

					<FormGroup controlId="formInlineName" style={{ margin: '0 auto' }}>
						<ControlLabel>phone: </ControlLabel>{' '}
						<FormControl type="text" placeholder="Input phone" inputRef={ref => {
							this.phone = ref;
						}}/>
					</FormGroup>{' '}

					<FormGroup controlId="formInlineName2" style={{ margin: '0 auto' }}>
						<Button type="submit" bsStyle="primary">Place Order</Button>
					</FormGroup>{' '}
				</Form>
			</div>
		);
	}
}

PlaceOrder.propTypes = {
	cartItems: PropTypes.array,
}

export default PlaceOrder;