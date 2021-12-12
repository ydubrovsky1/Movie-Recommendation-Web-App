import axios from 'axios';

export default {

    addGenre(customUser){
        return axios.post('/genre', customUser)
    },

    addFavorite(userPlusCurrentMovieId){
        return axios.post('/addFavorite', userPlusCurrentMovieId)
    }


  

}
