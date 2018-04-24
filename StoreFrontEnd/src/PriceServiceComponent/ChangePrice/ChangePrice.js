import React, { Component } from 'react';
import { Form, FormGroup, FormControl, Button, ControlLabel } from 'react-bootstrap';
import './ChangePrice.css';

class ChangePrice extends Component {

	render() {
		return (
			<div className="change-price">
				<Form inline onSubmit={(e) => (this.props.handleChangePrice(e, this.unitPrice.value))}>
					<FormGroup controlId="formInlineName" style={{ margin: '0 auto' }}>
						<ControlLabel>New Price: </ControlLabel>{' '}
						<FormControl type="text" placeholder="Input new price" inputRef={ref => {
							this.unitPrice = ref;
						}}/>
					</FormGroup>{' '}
					<FormGroup controlId="formInlineName2" style={{ margin: '0 auto' }}>
						<Button type="submit" bsStyle="primary">Change Price</Button>
					</FormGroup>{' '}
				</Form>
			</div>
		);
	}
}


export default ChangePrice;