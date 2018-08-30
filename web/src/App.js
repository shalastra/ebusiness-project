import React, {Component} from 'react';
import axios from 'axios';
import './App.css';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {isLoggedIn: false};
    }

    componentDidMount() {
        axios.get('/getCurrentUser').then(data => {
            localStorage.setItem("USER_ID",data.data.email);
            localStorage.setItem("userToken",data.data.token)
        });
    }

    render() {
        let login;
        var userID = localStorage.getItem("USER_ID");
        if ( userID && userID !== "undefined"){
            login = <div id={"login_div"}>Zalogowano jako {userID} | <a href='http://localhost:9000/signOut'>Wyloguj</a></div>
        } else {
            login = <a href='http://localhost:9000/signIn'><div id={"login_div"}>Zaloguj</div></a>
        }
        return (
            <div className="App">
                <h1>SKLEP</h1>
                {login}
            </div>
        );
    }
}

export default App;
