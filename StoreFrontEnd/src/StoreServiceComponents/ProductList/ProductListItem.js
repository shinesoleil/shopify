import React, { Component } from 'react';
// import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import './ProductListItem.css';

import axios from 'axios';
import { upperCase } from 'lodash';
import AddToCart from '../../CartServiceComponent/AddToCart/AddToCart';

class ProductListItem extends Component {
	constructor(props) {
		super(props);

		this.state = {
			price: {
				unitPrice: 0,
			},
		};
	}

	componentDidMount() {
		axios.get('http://localhost:1337/spring.priceservicejersey.docker.localhost/products/' + this.props.product.id + '/current-price')
			.then(res => {
					this.setState({ price: res.data });
				},
				error => {
					console.log(error);
				});
	}

	render() {
		return (
			<div className="product-list-item">
				<div className="product-name">
					{/*<Link to={"/stores/" + this.props.store.id}*/}
					{/*style={{ textDecoration: 'none' }}>*/}
					{upperCase(this.props.product.name)}
					{/*</Link>*/}
				</div>
				<div className="product-price">price: {this.state.price.unitPrice}</div>
				<AddToCart productId={this.props.product.id}/>
				<div className="product-id">id: {this.props.product.id}</div>

			</div>
		);
	}
}

ProductListItem.propTypes = {
	product: PropTypes.object,
}

export default ProductListItem;