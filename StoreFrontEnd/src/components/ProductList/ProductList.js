import React, { Component } from 'react';
import PropTypes from 'prop-types';
import './ProductList.css';

import ProductListItem from "./ProductListItem";

import axios from 'axios';
import { map } from 'lodash';

class ProductList extends Component {
	constructor(props) {
		super(props);

		this.state = {
			products: []
		};
	}

	componentDidMount() {
		axios.get("http://spring.storeservice.docker.localhost/stores/" + this.props.storeId + "/products")
			.then(res => {
				this.setState({ products: res.data });
			});
	}

	render() {
		return (
			<div className="product-list">
				{
					map(this.state.products, (product) => (
						<ProductListItem product={product} key={product.id}/>
					))
				}
			</div>
		);
	}
}

ProductList.propTypes = {
	storeId: PropTypes.string,
}

export default ProductList;