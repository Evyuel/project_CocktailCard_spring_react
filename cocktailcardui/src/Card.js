import React from 'react';
import './App.css';
import "bootstrap/dist/css/bootstrap.css";
import { Container, Row, Col, Table } from "react-bootstrap";
import { Link } from 'react-router-dom';
import HomeButton from "./HomeButton";
import axios from 'axios';
import LogoutButton from './LogoutButton';

class Card extends React.Component {
  constructor(props){
    super(props);
    this.state={
      coсktailData: null,
      cocktailName: null,
      isLoaded: false,
    }
  }

  callCocktailAPI = (name) => {
    let url = "http://" + process.env.REACT_APP_MICRO_HOST_NAME + ":8080/api/cocktail/" + name;
    axios.get(url,)
    .then(result => {
      this.setState({ coсktailData: result.data, isLoaded: true });
    });
  }

  componentDidMount() {
    this.setState({
      cocktailName: this.props.match.params.name
    });
    this.callCocktailAPI(this.props.match.params.name);
    document.title = this.props.match.params.name;
  }

  render(){
    if (this.state.isLoaded) {
    return (
      <div>
        <LogoutButton/>
        <Container>
            <Row>
              <Col>
                <HomeButton/>
              </Col>
              <Col lg="11">
                <h1>Коктейль: {this.state.coсktailData.name}</h1>
              </Col>
            </Row>
            <br/>
            <Row>
              <Col>
                <Table striped bordered hover variant="white">
                  <thead>
                    <tr>
                      <th colSpan="3">Необходимые ингредиенты</th>
                    </tr>
                  </thead>
                  <tbody>
                  {this.state.coсktailData.ingredients.map(i => (
                            <tr key ={i.ingredient.name}>
                              <td>
                                <Link
                                  to={`/ingredients/` + i.ingredient.name}
                                  variant='outline-secondary'
                                  size='sm'
                                  style={{ textDecoration: 'none' }}
                                >
                                  {i.ingredient.name}
                                </Link>
                              </td>
                              <td>{i.value}</td>
                              <td>{i.valueType}</td>
                            </tr>
                          ))
                  }
                  </tbody>
                </Table>
              </Col>
              <Col>
                <Table striped bordered hover variant="white">
                  <thead>
                    <tr>
                      <th colSpan="3">Необходимые инструменты</th>
                    </tr>
                  </thead>
                  <tbody>
                  {this.state.coсktailData.instruments.map(i => (
                    <tr key={i.instrument.name}>
                      <td>
                        <Link
                          to={`/instruments/` + i.instrument.name}
                          variant='outline-secondary'
                          size='sm'
                          style={{ textDecoration: 'none' }}
                        >
                          {i.instrument.name}
                        </Link>
                      </td>
                      <td>{i.value}</td>
                      <td>{i.valueType}</td>
                    </tr>
                  ))}
                  </tbody>
                </Table>
              </Col>
            </Row>
            <Row>
              <Table striped bordered hover variant="white">
                <thead>
                  <tr>
                    <th colSpan="2">Рецепт: </th>
                  </tr>
                </thead>
                <tbody>
                  {this.state.coсktailData.steps.map(s => (
                    <tr key={s.step.number}>
                      <td>
                        {s.step.number}
                      </td>
                      <td>
                        {s.value}
                      </td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            </Row>
        </Container>
      </div>
    )
  }
  else return null;
}
}

export default Card;
