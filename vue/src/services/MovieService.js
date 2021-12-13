import axios from 'axios';

export default {

    addGenre(userAndGenresToAdd){
        return axios.post('/genre', userAndGenresToAdd)
    },


    addFavorite(userPlusCurrentMovieId){
        return axios.post('/addFavorite', userPlusCurrentMovieId)
    }


  

}
