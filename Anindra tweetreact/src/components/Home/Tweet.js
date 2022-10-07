import React, { Component } from 'react';

import axios from 'axios';

class Tweet extends Component {

    constructor(){
      super();
      this.tweetDesc = React.createRef();
      this.state = {
        name: window.sessionStorage.getItem("username"),
        email: window.sessionStorage.getItem("email"),
      };
    }

    onSubmitHandler = (event) => {
        let body = this.tweetDesc.current.value;
        console.log(body);
        axios
          .post(
            `https://cors-everywhere.herokuapp.com/http://tweetapp-env.eba-au9yqf7p.ap-northeast-1.elasticbeanstalk.com/api/v1.0/tweets/${this.state.email}/add`,
            body
          )
          .then((res) => {
            this.tweetDesc.current.value = "";
          })
          .catch((err) => {
            console.log("error" + err);
          });
        event.preventDefault();
      };

    render(){
        return(
           
            <div>
              <br></br>
                <form className="border" onSubmit={this.onSubmitHandler}>
          <div className="form-group">
            <br></br>
            <br></br>
            <div className="row">
              <h3>Post a New Tweet here!</h3>
            </div>
            <br></br>
            <div className="row" id="tweetRow">
              <div></div>
              <div className="col-sm-4">
                <textarea
                  required
                  placeholder="Enter the tweet"
                  id="tweet"
                  name="tweet"
                  ref={this.tweetDesc}
                />
              </div>
              <div className="col-sm">
                <button className="btn btn-lg btn-primary">post</button>
              </div>
            </div>
          </div>
        </form>
            </div>

        )
    }
}

export default Tweet;