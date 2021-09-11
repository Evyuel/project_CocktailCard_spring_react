import React from 'react';
import "bootstrap/dist/css/bootstrap.css";
import {Container,ListGroup,Col,Row} from "react-bootstrap";
import { Link } from "react-router-dom";
import HomeButton from "./HomeButton";
import axios from 'axios';
import LogoutButton from './LogoutButton';



class IngredientsSearch extends React.Component {
  constructor(props){
    super(props);
    this.state={
      coсktailData: null,
      ingredientName: null,
      isLoaded: false,
    }
  }

  componentDidMount() {
    this.setState({
      ingredientName: this.props.match.params.name
    });
    this.getCocktailsByIngredientName(this.props.match.params.name);
    document.title = this.props.match.params.name;
  }

  getCocktailsByIngredientName = (name) => {
    let url = "http://" + process.env.REACT_APP_MICRO_HOST_NAME + ":8080/api/cocktail/ingredient/" + name;
    axios.get(url,)
    .then(result => {
      this.setState({ coсktailData: result.data, isLoaded: true });
    });
  }

  render(){
    if (this.state.isLoaded) {
      return (
        <div>
          <LogoutButton/>
          <Container>
              <br/>
              <Row>
                <Col>
                  <HomeButton/>
                </Col>
                <Col lg="11">
                  <h3>Коктейли, в которых используется: {this.state.ingredientName}</h3>
                </Col>
              </Row>
              <br/>
              <ListGroup>
                {this.state.coсktailData.map(c => (
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
          </Container>
        </div>
      )
    }
    else return null;
  }

}

export default IngredientsSearch;
