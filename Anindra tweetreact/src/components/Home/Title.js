import React, { Component } from "react";
import Login from "../Login/Login";

import "./Title.css";

window.sessionStorage.clear();
class Title extends Component {

  render() {
    return (
      <div className="container">
        
        <div className="card" id ="card">
          <Login />
        </div>
      </div>
    );
  }
}

export default Title;
