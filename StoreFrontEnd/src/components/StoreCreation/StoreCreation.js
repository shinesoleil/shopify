import React, { Component } from 'react';
// import PropTypes from 'prop-types';
import { Form, FormGroup, FormControl, Button, ControlLabel } from 'react-bootstrap';
import './StoreCreation.css';

import axios from 'axios';

class StoreCreation extends Component {
	handleSumbit(event) {
		event.preventDefault();
		console.log('提交表单...');
		console.log(this.name.value)

		axios.post('http://localhost:1337/spring.storeservice.docker.localhost/stores', {
			name: this.name.value,
		})
			.then(res => {
				console.log("suc")
				console.log(res)
			}, error => {
				console.log("err")
				console.log(error)
			});
	}

	render() {
		return (
			<div className="store-creation">
				<Form inline onSubmit={this.handleSumbit.bind(this)}>
					<FormGroup controlId="formInlineName" style={{ margin: '0 auto' }}>
						<ControlLabel>Store Name: </ControlLabel>{' '}
						<FormControl type="text" placeholder="Input store name" inputRef={ref => {
							this.name = ref;
						}}/>
					</FormGroup>{' '}
					<FormGroup controlId="formInlineName2" style={{ margin: '0 auto' }}>
						<Button type="submit" bsStyle="primary">Create Store</Button>
					</FormGroup>{' '}
				</Form>
			</div>
		);
	}
}


export default StoreCreation;