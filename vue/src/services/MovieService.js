import axios from 'axios';

export default {

    addGenre(genreId, user){
        return axios.post('/genre', genreId, user)
    }
  

}
