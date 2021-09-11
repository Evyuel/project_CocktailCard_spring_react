import React from 'react';
import "bootstrap/dist/css/bootstrap.css";
import { ListGroup, Button, Form, Container, Row, Col } from "react-bootstrap";
import { Link } from "react-router-dom";
import HomeButton from "./HomeButton";
import AuthenticationService from './AuthComponents/AuthenticationService';
import LogoutButton from './LogoutButton';
import axios from 'axios';

class Welcome extends React.Component {
constructor(props) {
    super(props);
    this.state = {
      isLoaded: false,
      coсktailData: [],
      cocktailName: ""
    };
  }

  componentDidMount(){
    document.title = "Коктейльная карта";
  }

toggleButtonState = () => {
        if (AuthenticationService.isUserLoggedIn()) {
          let url = "http://" + process.env.REACT_APP_MICRO_HOST_NAME + ":8080/api/cocktails";
          if (this.cocktailName){
            url += "?";
            url = url + "name=" + this.state.cocktailName;
          }
          axios.get(url,)
          .then(result => {
            this.setState({ coсktailData: result.data, isLoaded: true });
          });
        }
        else {
          window.location.href = '/login';
        }

      };

setCoktailSubStr = (e) => {
  this.setState({cocktailName: e.target.value});
}

handleKeyPress = (event) => {
  if(event.key === 'Enter'){
    event.preventDefault();
    this.toggleButtonState();
  }
}

  render(){
      return (
        <div>
          <LogoutButton/>
          <Container>
              <Row>
                <Col>
                  <HomeButton/>
                </Col>
                <Col lg="11">
                  <h1>Коктейльная карта </h1>
                </Col>
              </Row>
              <br/>
              <Form>
                <Form.Control size="lg" type="text" placeholder="Название..." onChange={(e) => {this.setCoktailSubStr(e)}} onKeyPress={this.handleKeyPress}/>
                <br/>
                <Button variant="primary" onClick={this.toggleButtonState} >Найти</Button>{' '}
              </Form>
              <br/>
              <CocktailList isLoaded={this.state.isLoaded} coсktailData={this.state.coсktailData}/>
          </Container>
        </div>
      );
  }

}

class CocktailList extends React.Component {

  render () {
  if (this.props.isLoaded && this.props.coсktailData.length>0) {
    return (<div>
              <ListGroup>
              {this.props.coсktailData.map(c =>(
                  <ListGroup.Item key={c.name} action >
                    <Link
                      to={`/card/` + c.name}
                      variant='outline-secondary'
                      size='sm'
                      style={{ textDecoration: 'none' }}
                    >
                      {c.name}
                    </Link>
                  </ListGroup.Item>
                ))}
              </ListGroup>
            </div>
          );
  } else return null;
}
}

export default Welcome;
