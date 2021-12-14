import axios from 'axios';

export default {

    addGenre(userAndGenresToAdd){
        return axios.post('/genre', userAndGenresToAdd)
    },


    addFavorite(userPlusCurrentMovieId){
        return axios.post('/addFavorite', userPlusCurrentMovieId)
    },

    getGenres(userAndGenresToAdd){
        return axios.post('/getGenres', userAndGenresToAdd)
    },
    addToWatchlist(userPlusCurrentMovieId){
        return axios.post('/addToWatchlist', userPlusCurrentMovieId)
    },
    deleteGenre(userId, genreId){
        return axios.delete(`/genre/${userId}/${genreId}`)
    }


  

}
