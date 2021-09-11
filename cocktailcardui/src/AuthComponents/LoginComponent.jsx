import React, { Component } from 'react'
import AuthenticationService from './AuthenticationService';
import { Container, Row, Alert, Form, Button } from "react-bootstrap";
import './LoginComponent.css';

class LoginComponent extends Component {

    constructor(props) {
        super(props)

        this.state = {
            username: '',
            password: '',
            hasLoginFailed: false,
        }
        this.loginClicked = this.loginClicked.bind(this)
    }

    loginClicked() {
        AuthenticationService
            .executeJwtAuthenticationService(this.state.username, this.state.password)
            .then(res => {
                AuthenticationService.registerSuccessfulLoginForJwt(this.state.username, res.data.token)
                this.props.history.push(`/`)
            }).catch(() => {
                this.setState({ hasLoginFailed: true })
            })
    }

    handleKeyPress = (event) => {
      if(event.key === 'Enter'){
        event.preventDefault();
        this.toggleButtonState();
      }
    }

    handleUserName = (e) => {
      e.preventDefault();
      this.setState({
        username : e.target.value
      })
    }
    handlePass = (e) => {
      e.preventDefault();
      this.setState({
        password : e.target.value
      })
    }

    render() {
        return (
            <div>
              <Container className="form">
                <Row>
                  <h1 align="center">Авторизация</h1>
                </Row>
                <br/>
                {this.state.hasLoginFailed && <Alert variant="danger">Неверные логин или пароль</Alert>}
                <Row><Form.Control className="input_element" size="lg" type="text" placeholder="Пользователь..." onChange={(e) => {this.handleUserName(e)}}/></Row>
                <br/>
                <Row><Form.Control className="input_element" size="lg" type="password" placeholder="Пароль..." onChange={(e) => {this.handlePass(e)}}/></Row>
                <br/>
                <Row><Button className="login_button" variant="primary" onClick={this.loginClicked}>Подтвердить</Button>{' '}</Row>
              </Container>
            </div>
        )
    }
}

export default LoginComponent
