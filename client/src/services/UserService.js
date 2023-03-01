import axios from 'axios'

export default class UserService{

    getAllUsers(token){
        return axios.get(process.env.REACT_APP_API+"users/getall",{
            headers:{
                'Authorization':"Bearer "+token
            }
        })
    }

    getById(id,token){
        return axios.get(process.env.REACT_APP_API+"users/getbyid/"+id,{
            headers:{
                'Authorization':"Bearer "+token
            }
        });
    }

    isFollowing(userId,followingId,token){
        return axios.get(process.env.REACT_APP_API+`users/isfollowing?userId=${userId}&followingId=${followingId}`,{
            headers:{
                'Authorization':"Bearer "+token
            }
        });
    }

    profilePicUpload(values, token){
        return axios.post(process.env.REACT_APP_API + "userimages/upload", values, {
            headers: {
                'content-type': 'multipart/form-data',
                'Authorization':"Bearer "+token
            }
        })
    }
}