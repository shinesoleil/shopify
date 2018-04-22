import React, { Component } from 'react';
import './StoreInfo.css';

import axios from 'axios';
import { upperCase } from 'lodash';


import ProductList from '../ProductList/ProductList';

class StoreInfo extends Component {
	constructor(props) {
		super(props);

		this.state = {
			store: {},
		};
	}

	componentDidMount() {
		axios.get("http://localhost:1337/spring.storeservice.docker.localhost/stores/" + this.props.match.params.storeId)
			.then(res => {
				this.setState({ store: res.data });
			});
	}

	render() {
		return (
			<div className="store-info">
				<div className="store-info-name">{upperCase(this.state.store.name)}</div>
				<div className="store-info-id">id: {this.state.store.id}</div>

				<ProductList storeId={this.props.match.params.storeId}/>
			</div>
		);
	}
}

export default StoreInfo;