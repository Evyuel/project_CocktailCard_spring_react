import React from 'react';
import { Link } from 'react-router-dom';
import LogoutImage from "./icons/logout.png";
import './LogoutButton.css';
import AuthenticationService from './AuthComponents/AuthenticationService';


class LogoutButton extends React.Component {

  handleClick = () => {
    AuthenticationService.logout();
  }

  render() {
    return (
        <div>
          <Link
            to='/'
            variant='outline-secondary'
            size='sm'
            style={{ textDecoration: 'none' }}
            onClick={this.handleClick}
          >
            <img src={LogoutImage} className="image" alt="Logout button"/>
          </Link>
        </div>
      );
  }
}

export default LogoutButton;
