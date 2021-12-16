import axios from 'axios';

//base URL
/*
const http = axios.create({
    baseURL: "https://api.themoviedb.org/3/movie/"
});


const apiDiscoverUrl= axios.create({
    baseURL: "https://api.themoviedb.org/3/discover/movie?api_key=fbc258611c329af8b2fa24446de942f6&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate&with_genres="

});
*/
const movieBaseUrl = axios.create({
    baseURL: "https://api.themoviedb.org/3"
});

/*
const recommendedMovieUrl = axios.create({
    baseURL: "/recommendations?api_key=fbc258611c329af8b2fa24446de942f6&language=en-US&page=1"
});

const apiKey = "?api_key=fbc258611c329af8b2fa24446de942f6&language=en-US";
*/
export default {

    /*
    getMoviesByGenre(genreId){
        return get(`${apiDiscoverUrl}${genreId}`)
        //apiDiscoverUrl.get(`${genreId}%2C`);
    }
*/
//input genre Id, get a list of movies which include that genre
getMoviesByGenre(genreId){
    return movieBaseUrl.get(`discover/movie?api_key=fbc258611c329af8b2fa24446de942f6&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate&with_genres=${genreId}`)
    //apiDiscoverUrl.get(`${genreId}%2C`);
},

//input movie Id, get a list of movies recommended based on that movie
getMovieRecs(movieId){
    return movieBaseUrl.get(`movie/${movieId}/recommendations?api_key=fbc258611c329af8b2fa24446de942f6&language=en-US&page=1`)
},

//get movie deets based on movie Id
getMovieById(movieId){
    return movieBaseUrl.get(`movie/${movieId}?api_key=fbc258611c329af8b2fa24446de942f6&language=en-US`);
}

}