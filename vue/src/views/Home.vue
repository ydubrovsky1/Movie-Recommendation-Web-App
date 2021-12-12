<template>
  <div class="home">
    <Header />
    <h2>Choose A Genre:</h2>
    <form v-on:submit.prevent="addGenre">
      <select id="genres" v-model="genres" multiple>
        <option value="28">Action</option>
        <option value="12">Adventure</option>
        <option value="16">Animation</option>
        <option value="35">Comedy</option>
        <option value="80">Crime</option>
        <option value="99">Documentary</option>
        <option value="18">Drama</option>
        <option value="10751">Family</option>
        <option value="14">Fantasy</option>
        <option value="36">History</option>
        <option value="27">Horror</option>
        <option value="10402">Music</option>
        <option value="9648">Mystery</option>
        <option value="10749">Romance</option>
        <option value="878">Science Fiction</option>
        <option value="10770">TV Movie</option>
        <option value="53">Thriller</option>
        <option value="10752">War</option>
        <option value="37">Western</option>
      </select>
      <br />
      <button id="update-genre-button" type="submit">Update Genres</button>
    </form>




        <!--show one movie at a time in table-->
    <tbody v-if="this.movies.length > 0">
        <tr>
        <td>{{ currentMovie.original_title }}</td>
        </tr>
        <tr>
        <td>{{ currentMovie.genre_ids }}</td>
        </tr>
        <tr>
        <td>{{ currentMovie.overview }}</td>
        </tr>
        <tr>
        <td>{{ currentMovie.release_date }}</td>
        </tr>
        <tr>
        <td><img :src="`https://www.themoviedb.org/t/p/w600_and_h900_bestv2/` + currentMovie.poster_path" alt="Girl in a jacket" width="500" height="600"></td>
        </tr>
    </tbody>
    <button v-on:click="updateCurrentMovie()">Get Next Movie</button>

    <!--MEH
    <button v-on:click="addToFavorites()">Get Next Movie</button>
    -->
    <tbody>
      <!--$store.state.genres-->
      <tr v-for="genre in $store.state.genres" v-bind:key="genre.id">
        <td>{{ genre.genreName }}</td>
      </tr>
    </tbody>

    <form v-on:submit.prevent="loadMoviesByGenre()">
      <button type="submit">Start Swiping Movies In Your Recommended Genres</button>
    </form>

    <form v-on:submit.prevent="loadMovieRecs()">
      <button type="submit">See Movie Recs Based on Favorite Movie</button>
    </form>

  <!--show all movies in table-->
    <tbody>
      <tr v-for="movie in movies" v-bind:key="movie.id">
        <td>my movie:</td>
        <td>{{ movie.id }}</td>
        <td>{{ movie.original_title }}</td>
        <td>{{ movie.genre_ids }}</td>
        <td>{{ movie.overview }}</td>
        <td>{{ movie.release_date }}</td>
        <td>{{ movie.poster_path }}</td>
        <!--to see the photo att to img path: https://www.themoviedb.org/t/p/w600_and_h900_bestv2/-->
      </tr>
    </tbody>




  </div>
</template>

<script>
import Vue from "vue";
import movieService from "../services/MovieService";
import VueSimpleAlert from "vue-simple-alert";
import apiMovieService from "../services/APIMovieService";
Vue.use(VueSimpleAlert);

Vue.use(VueSimpleAlert);
import Header from "./Header.vue";
export default {
  name: "home",
  components: {
    Header,
  },
  data() {
    return {
      genre: "",
      genres: [],
      movies: [],
      currentMovieIndex: 0,
    };
  },
  methods: {
    /*MEH
    addToFavorites(){
      let userPlusCurrentMovieId = {user: this.$store.state.user, movieId: this.movies[this.currentMovieIndex].id};
      movieService
      .addFavorite(userPlusCurrentMovieId)
      .then((response) =>{
        this.$store.commit("SET_FAVORITES", response.data)
      });
      this.$alert("Favorites Updated!");
    },
    */
    updateCurrentMovie(){
      if(this.currentMovieIndex < this.movies.length - 1){
          this.currentMovieIndex++;
      }
      else{
        this.currentMovieIndex = 0;
      }
    },
    addGenre() {
      let myCustomUser = { user: this.$store.state.user, genres: this.genres };
      movieService
        .addGenre(myCustomUser) //this calls movie service in the back-end
        //response
        .then((response) => {
          this.$store.commit("SET_GENRES", response.data);
        });
      this.$alert("Genres Updated!");
    },
    loadMoviesByGenre() {
      if (this.$store.state.genres.length > 0) {
        let myGenreUrl = "";
        for (let i = 0; i < this.$store.state.genres.length; i++) {
          myGenreUrl = myGenreUrl + this.$store.state.genres[i].id + "%2C";
        }
        apiMovieService.getMoviesByGenre(myGenreUrl).then((response) => {
          this.movies = response.data.results;
          this.$alert("Genres Updated!");
        });
      } else {
        apiMovieService.getMoviesByGenre("28").then((response) => {
          this.movies = response.data.results;
        });
      }
    },
    loadMovieRecs() {
      apiMovieService.getMovieRecs("585245").then((response) => {
        this.movies = response.data.results;
      });
    },
  },
  computed: {
    currentMovie(){
      if(this.movies.length < 1){
                        return "Click Next To Swipe";
      }
      else{
        return this.movies[this.currentMovieIndex];
      }
    }
  },
};
</script>


<style scoped>
h1 {
  font-family: fantasy;
  color: black;
  text-align: center;
  text-shadow: 10px 10px 10px orange;
}
h2 {
  font-family: fantasy;
  color: black;
  text-align: left;
  text-shadow: 10px 10px 10px orange;
}
#update-genre-button {
  height: 100%;
  widows: auto;
  font-size: 75%;
  border-radius: 10px;
  font-family: fantasy;
  background: orange;
  color: black;
}
</style>
