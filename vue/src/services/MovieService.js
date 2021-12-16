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
    },
    getFavorites(userPlusCurrentMovieId){
        return axios.post('/getFavorites', userPlusCurrentMovieId);
    },
    addAbhore(userPlusCurrentMovieId){
        return axios.post('/addAbhorred', userPlusCurrentMovieId);
    },
    getAbhorred(userPlusCurrentMovieId){
        return axios.post('/getAbhorred', userPlusCurrentMovieId);
    }



  

}
