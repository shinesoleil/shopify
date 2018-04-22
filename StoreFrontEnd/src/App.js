import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';

import StoreList from './StoreServiceComponents/StoreList';
import StoreInfo from './StoreServiceComponents/StoreInfo/StoreInfo';
import StoreCreation from './StoreServiceComponents/StoreCreation/StoreCreation';
import CartItemList from './CartServiceComponent/CartList/CartItemList';

class App extends Component {
	render() {
		return (
			<div className="App">
				<Router>
					<div>
						<header className="App-header">
							<h1 className="App-title">Welcome</h1>
							<span className="App-link"><Link to="/">Home</Link></span>
							<span className="App-link"><Link to="/stores">Stores</Link></span>
							<span className="App-link"><Link to="/cart">Cart</Link></span>
							{/*<span className="App-link"><Link to="/topics">Topics</Link></span>*/}
						</header>

						<Route exact path="/stores" component={StoreList}/>
						<Route path="/new-store" component={StoreCreation}/>
						<Route path="/stores/:storeId" component={StoreInfo}/>
						<Route path="/cart" component={CartItemList}/>
						{/*<Route path="/topics" component={StoreList}/>*/}
					</div>
				</Router>
			</div>
		);
	}
}

export default App;
