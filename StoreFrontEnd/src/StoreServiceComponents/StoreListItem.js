import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import './StoreListItem.css';

import { upperCase } from 'lodash';

class StoreListItem extends Component {

	render() {
		return (
			<div className="store-list-item">
				<div className="store-name">
					<Link to={"/stores/" + this.props.store.id}
								style={{ textDecoration: 'none' }}>{upperCase(this.props.store.name)}
					</Link>
				</div>
				<div className="store-id">id: {this.props.store.id}</div>
			</div>
		);
	}
}

StoreListItem.propTypes = {
	store: PropTypes.object,
}

export default StoreListItem;