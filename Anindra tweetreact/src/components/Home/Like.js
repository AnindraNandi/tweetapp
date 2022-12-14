import React, { Component } from "react";
import axios from "axios";
import { BiLike} from "react-icons/bi";

class Like extends Component{

    constructor(props){
        super(props);
        this.state = {
            name: window.sessionStorage.getItem("username"),
            email: window.sessionStorage.getItem("email"),
          };
    }

    likeHandler = (event) => {
        axios
          .put(
            `https://cors-everywhere.herokuapp.com/http://tweetapp-env.eba-au9yqf7p.ap-northeast-1.elasticbeanstalk.com/api/v1.0/tweets/${this.props.email}/like/${this.props.id}`
          )
          .then((res) => {
            console.log(res.data);
          })
          .catch((err) => {
            console.log("error" + err);
          });
      };

    render(){
        return(
           <div>
              <button
                    className="btn btn-primary"
                    onClick={this.likeHandler}
                  >
                    <BiLike />
                  </button>
                  <span> {this.props.like}</span>
           </div>
        )
    }
}

export default Like;