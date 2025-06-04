import axios from "axios";

const http = axios.create( {
    baseURL: 'http://localhost:8080/upload'
});

export default {

    addImage(formData){
        http.post('', formData)
    }

}

