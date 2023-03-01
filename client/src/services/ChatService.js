import axios from "axios";

export default class ChatService{
    getAllMessages(token){
        return axios.get(process.env.REACT_APP_API+`chatMessages`,{
            headers:{
                'Authorization':"Bearer "+token
            }
        })
    }

    postMessage(obj, token){
        return axios.post(process.env.REACT_APP_API+"chatMessages",obj,{
            headers:{
                'Authorization':"Bearer "+token
            }
        })
    }
}