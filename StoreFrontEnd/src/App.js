import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css';

import StoreList from './components/StoreList';
import StoreInfo from './components/StoreInfo/StoreInfo';
import StoreCreation from './components/StoreCreation/StoreCreation';

class App extends Component {
	render() {
		return (
			<div className="App">
				<Router>
					<div>
						<header className="App-header">
							<h1 className="App-title">Welcome to React</h1>
							<span className="App-link"><Link to="/">Home</Link></span>
							<span className="App-link"><Link to="/stores">Stores</Link></span>
							<span className="App-link"><Link to="/new-store">Create Stores</Link></span>
							{/*<span className="App-link"><Link to="/topics">Topics</Link></span>*/}
						</header>

						<Route exact path="/stores" component={StoreList}/>
						<Route path="/new-store" component={StoreCreation}/>
						<Route path="/stores/:storeId" component={StoreInfo}/>
						{/*<Route path="/topics" component={StoreList}/>*/}
					</div>
				</Router>
			</div>
		);
	}
}

export default App;
