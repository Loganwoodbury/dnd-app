import axios from 'axios';

const http = axios.create( {
    baseURL: 'http://localhost:8080/monster/api'
});

const httpMonster = axios.create({
    baseURL: 'http://localhost:8080/monster'
})

export default {
    getAllMonsters() {
        return http.get();
    },

    getMonsterDetail(monsterName) {
        return http.get(`/${monsterName}`);
    },

    createMonster(monster) {
        console.log("Adding new monster:", monster);
        return httpMonster.post('/add', monster)
    }
}