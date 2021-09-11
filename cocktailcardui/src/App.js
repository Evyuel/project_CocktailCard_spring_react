import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import './App.css';
import Welcome from './Welcome';
import Card from './Card';
import IngredientsSearch from './IngredientsSearch';
import InstrumentSearch from './InstrumentSearch';
import AuthenticatedRoute from './AuthComponents/AuthenticatedRoute';
import LoginComponent from './AuthComponents/LoginComponent';

class App extends React.Component {
  render(){
    return (
      <BrowserRouter>
        <div>
            <Switch>
             <Route path="/login" exact component={LoginComponent} />
             <AuthenticatedRoute path="/" component={Welcome} exact/>
             <AuthenticatedRoute path="/card/:name" exact component={Card} />
             <AuthenticatedRoute path="/ingredients/:name" exact component={IngredientsSearch} />
             <AuthenticatedRoute path="/instruments/:name" exact component={InstrumentSearch} />
           </Switch>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
