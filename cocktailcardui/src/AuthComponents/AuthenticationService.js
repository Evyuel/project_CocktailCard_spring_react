import axios from 'axios';

const API_URL = 'http://' + process.env.REACT_APP_MICRO_HOST_NAME + ':8080';

export const JWT_ATTRIBUTE_NAME = 'jwt_token';

class AuthenticationService {


  executeJwtAuthenticationService(username, password) {
    return axios.post(`${API_URL}/auth/authenticate`, {
        username,
        password
    })
}

registerSuccessfulLoginForJwt(username, token) {
  sessionStorage.setItem(JWT_ATTRIBUTE_NAME, this.createJWTToken(token))
  this.setupAxiosInterceptors(this.createJWTToken(token))
}

createJWTToken(token) {
  return 'Bearer ' + token
}

logout() {
    sessionStorage.removeItem(JWT_ATTRIBUTE_NAME);
}

isUserLoggedIn() {
    let jwt_token = sessionStorage.getItem(JWT_ATTRIBUTE_NAME)
    if (jwt_token === null) return false
    return true
}

    setupAxiosInterceptors(token) {
        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.authorization = token
                }
                return config
            }
        )
    }
}

export default new AuthenticationService()
