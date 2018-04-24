import React, { Component } from 'react';
import './ProductInfo.css';

import axios from 'axios';
import { upperCase } from 'lodash';
import ChangePrice from '../../PriceServiceComponent/ChangePrice/ChangePrice';



class ProductInfo extends Component {
	constructor(props) {
		super(props);

		this.state = {
			product: {},
			price: {
				unitPrice: 0,
			}
		};
	}

	componentDidMount() {
		axios.get('http://localhost:1337/spring.storeservice.docker.localhost/products/' + this.props.match.params.productId)
			.then(res => {
				this.setState({ product: res.data });
			});

		axios.get('http://localhost:1337/spring.priceservice.docker.localhost/products/' + this.props.match.params.productId + '/current-price')
			.then(res => {
					this.setState({ price: res.data });
				},
				error => {
					console.log(error);
				});
	}

	handleChangePrice(event, newPrice) {
		event.preventDefault();

		axios.post('http://localhost:1337/spring.priceservice.docker.localhost/products/' + this.props.match.params.productId + '/prices', {
			unit_price: parseFloat(newPrice),
		})
			.then(res => {
				alert('creation success');
				axios.get('http://localhost:1337/spring.priceservice.docker.localhost/products/' + this.props.match.params.productId + '/current-price')
					.then(res => {
							this.setState({ price: res.data });
						},
						error => {
							console.log(error);
						});
			}, error => {
				alert('creation error');
			});
	}

	render() {
		return (
			<div className="product-info">
				<div className="product-info-name">{upperCase(this.state.product.name)}</div>
				<div className="product-info-id">id: {this.state.product.id}</div>

				<div className="product-price">Current Price: {this.state.price.unitPrice}</div>

				<ChangePrice productId={this.state.product.id} handleChangePrice={this.handleChangePrice.bind(this)} />

			</div>
		);
	}
}

export default ProductInfo;