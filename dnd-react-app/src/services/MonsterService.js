import axios from 'axios';

const http = axios.create( {
    baseURL: 'http://localhost:8080/monster/api'
});

export default {
    getAllMonsters() {
        return http.get();
    },

    getMonsterDetail(monsterName) {
        return http.get(`/${monsterName}`);
    },
}