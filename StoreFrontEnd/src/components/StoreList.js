import React, { Component } from 'react';
import './StoreList.css';

import StoreListItem from "./StoreListItem";

import axios from 'axios';
import { map } from 'lodash';

class StoreList extends Component {
	constructor(props) {
		super(props);

		this.state = {
			stores: []
		};
	}

	componentDidMount() {
		axios.get("http://localhost:1337/spring.storeservice.docker.localhost/stores")
			.then(res => {
				this.setState({ stores: res.data });
			});
	}

	render() {
		return (
			<div className="store-list">
				{
					map(this.state.stores, (store) => (
						<StoreListItem store={store} key={store.id}/>
					))
				}
			</div>
		);
	}
}

export default StoreList;