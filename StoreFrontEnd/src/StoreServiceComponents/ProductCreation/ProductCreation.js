import React, { Component } from 'react';
import { Form, FormGroup, FormControl, Button, ControlLabel } from 'react-bootstrap';
import './ProductCreation.css';

import axios from 'axios';
import {upperCase} from 'lodash';

class ProductCreation extends Component {
	constructor(props) {
		super(props);

		this.state = {
			store: {},
		};
	}

	componentDidMount() {
		axios.get('http://localhost:1337/spring.storeservice.docker.localhost/stores/' + this.props.match.params.storeId)
			.then(res => {
				this.setState({ store: res.data });
			});
	}

	handleSumbit(event) {
		event.preventDefault();

		axios.post('http://localhost:1337/spring.storeservice.docker.localhost/stores/' + this.props.match.params.storeId + '/products', {
			name: this.name.value,
			store_id: this.props.match.params.storeId,
		})
			.then(res => {
				console.log("suc");
				alert('product creation success');
			}, error => {
				console.log("err")
				alert('product creation error');
			});
	}


	render() {
		return (
			<div className="product-creation">
				<div className="product-creation-store-name">{upperCase(this.state.store.name)}</div>
				<Form inline onSubmit={this.handleSumbit.bind(this)}>
					<FormGroup controlId="formInlineName" style={{ margin: '0 auto' }}>
						<ControlLabel>Product Name: </ControlLabel>{' '}
						<FormControl type="text" placeholder="Input product name" inputRef={ref => {
							this.name = ref;
						}}/>
					</FormGroup>{' '}
					<FormGroup controlId="formInlineName2" style={{ margin: '0 auto' }}>
						<Button type="submit" bsStyle="primary">Add Product</Button>
					</FormGroup>{' '}
				</Form>
			</div>
		);
	}
}

export default ProductCreation;