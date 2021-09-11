import React, { Component } from 'react'
import { Route } from 'react-router-dom'
import AuthenticationService from './AuthenticationService';

class AuthenticatedRoute extends Component {

  redirect = () => {
      window.location.href = '/login';
      return null;
    }

    render() {
        if (AuthenticationService.isUserLoggedIn()) {
            return <Route {...this.props} />
        } else {
            return this.redirect();
        }

    }
}

export default AuthenticatedRoute
