import React from 'react';
import { Link } from 'react-router-dom';
import HomeImage from "./icons/home.png";
import './HomeButton.css';


class HomeButton extends React.Component {
  render() {

    return (
        <div>
          <Link
            to='/'
            variant='outline-secondary'
            size='sm'
            style={{ textDecoration: 'none' }}
          >
            <img src={HomeImage} className="image" alt="Home button"/>
          </Link>
        </div>
      );
  }
}

export default HomeButton;
