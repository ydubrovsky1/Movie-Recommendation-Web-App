<template>
  <div class="home">
    <Header />
    <div class="main">
      <section id="left-panel">
<<<<<<< HEAD
        <h2 id="genre-choice">Choose A Genre:</h2>
        <form id="genre-dropdown" v-on:submit.prevent="addGenre">
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
=======
        <h2 id="genre-choice">Add Genres:</h2>
        <form id="genre-dropdown" v-on:submit.prevent="addGenre">
          <select id="genres" v-model="genres" multiple>
            <option value="28" v-if="!containsGenre(28)">Action</option>
            <option value="12" v-if="!containsGenre(12)">Adventure</option>
            <option value="16" v-if="!containsGenre(16)">Animation</option>
            <option value="35" v-if="!containsGenre(35)">Comedy</option>
            <option value="80" v-if="!containsGenre(80)">Crime</option>
            <option value="99" v-if="!containsGenre(99)">Documentary</option>
            <option value="18" v-if="!containsGenre(18)">Drama</option>
            <option value="10751" v-if="!containsGenre(18)">Family</option>
            <option value="14" v-if="!containsGenre(14)">Fantasy</option>
            <option value="36" v-if="!containsGenre(36)">History</option>
            <option value="27" v-if="!containsGenre(27)">Horror</option>
            <option value="10402" v-if="!containsGenre(10402)">Music</option>
            <option value="9648" v-if="!containsGenre(9648)">Mystery</option>
            <option value="10749" v-if="!containsGenre(10749)">Romance</option>
            <option value="878" v-if="!containsGenre(878)">Science Fiction</option>
            <option value="10770" v-if="!containsGenre(10770)">TV Movie</option>
            <option value="53" v-if="!containsGenre(53)">Thriller</option>
            <option value="10752" v-if="!containsGenre(10752)">War</option>
            <option value="37" v-if="!containsGenre(37)">Western</option>
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
          </select>
          <br />
          <button id="update-genre-button" type="submit">Update Genres</button>
        </form>
      </section>
      <section id="right-panel">
        <h1>Browse Movie</h1>
        <div id="right-panel-middle-row">
          <button id="abhore-button">Abhore</button>
<<<<<<< HEAD
          <button
            v-if="this.movies.length < 1"
            v-on:click="loadMoviesByGenre()"
          >
            Start Swiping Movies In Your Recommended Genres
          </button>
          <tbody v-if="this.movies.length > 0">
            <tr>
              <div id="poster-flex-box">
                <img
                  :src="
                    `https://www.themoviedb.org/t/p/w600_and_h900_bestv2/` +
                    currentMovie.poster_path
                  "
                  v-if="this.movies.length > 0"
                  id="movie-poster"
                />
              </div>
            </tr>
            <tr></tr>
            <tr>
              <td>{{ currentMovie.genre_ids }}</td>
            </tr>
            <tr>
              <div id="overview-box">
                <td>{{ currentMovie.overview }}</td>
              </div>
            </tr>
            <tr>
              <td>Release Date: {{ currentMovie.release_date }}</td>
            </tr>
          </tbody>
          <button id="adore-button">Adore</button>
        </div>

        <!--show one movie at a time in table-->

        <button v-on:click="updateCurrentMovie()">Get Next Movie</button>

        <button v-on:click="addToFavorites()">Add To Faves</button>
=======
          <button v-if="this.movies.length < 1" v-on:click="loadMoviesByGenre()">Start Swiping Movies In Your Recommended Genres</button> 
          <tbody v-if="this.movies.length > 0">
            <tr>
            <img :src="`https://www.themoviedb.org/t/p/w600_and_h900_bestv2/` + currentMovie.poster_path" v-if="this.movies.length  > 0" id="movie-poster"/>
            </tr>
          <tr>
          </tr>
          <tr>
          <td>{{ currentMovie.genre_ids }}</td>
          </tr>
          <tr>
          <td>{{ currentMovie.overview }}</td>
          </tr>
          <tr>
          <td>Release Date: {{ currentMovie.release_date }}</td>
          </tr>
                       </tbody>
          <button id="adore-button" v-on:click="addToFavorites(); updateCurrentMovie()">Adore</button>
 
        </div>




        <!--show one movie at a time in table-->



>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
        <br />
        <br />
        <div id="right-panel-row-button">
          <button id="previous-button">Previous</button>
<<<<<<< HEAD
          <button id="favorites-button">Add To Favorites</button>
          <button id="next-button">Next</button>
=======
          <!--<button id="favorites-button">Add To Favorites</button>-->
          <button id="next-button" v-on:click="updateCurrentMovie()">Next</button>
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
        </div>
      </section>
    </div>

    <tbody>
      <!--$store.state.genres-->
      <tr v-for="genre in $store.state.genres" v-bind:key="genre.id">
        <td>{{ genre.genreName }}</td>
      </tr>
    </tbody>

<<<<<<< HEAD
=======


>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
    <form v-on:submit.prevent="loadMovieRecs()">
      <button type="submit">See Movie Recs Based on Favorite Movie</button>
    </form>

<<<<<<< HEAD
    <!--show all movies in table-->
=======

  <!--show all movies in table
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
    <tbody>
      <tr v-for="movie in movies" v-bind:key="movie.id">
        <td>my movie:</td>
        <td>{{ movie.id }}</td>
        <td>{{ movie.original_title }}</td>
        <td>{{ movie.genre_ids }}</td>
        <td>{{ movie.overview }}</td>
        <td>{{ movie.release_date }}</td>
        <td>{{ movie.poster_path }}</td>
      </tr>
    </tbody>

-->


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
<<<<<<< HEAD
    addToFavorites() {
      let userPlusCurrentMovieId = {
        userId: this.$store.state.user.id,
        movieId: this.movies[this.currentMovieIndex].id,
      };
      movieService.addFavorite(userPlusCurrentMovieId).then((response) => {
        this.$store.commit("SET_FAVORITES", response.data);
=======
    containsGenre(genreId){
 
     for(let i=0; i< this.$store.state.genres.length; i++ ){
        if(this.$store.state.genres[i].id == genreId){
          return true;
        }
     }
     return false;
     /*
       this.$store.state.genres.forEach((genre) =>{
        if(genre.id == genreId){
          return true;
        }
      });
      this.$alert(`genre.id: ${genre.id} genreId: ${genreId}`);
      return false;
      */

    },
    addToFavorites(){
      let userPlusCurrentMovieId = {userId: this.$store.state.user.id, movieId: this.movies[this.currentMovieIndex].id};
      movieService
      .addFavorite(userPlusCurrentMovieId)
      .then((response) =>{
        this.$store.commit("SET_FAVORITES", response.data)
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
      });
      this.$alert("Favorites Updated!");
    },

<<<<<<< HEAD
    updateCurrentMovie() {
      if (this.currentMovieIndex < this.movies.length - 1) {
        this.currentMovieIndex++;
      } else {
=======
    updateCurrentMovie(){
      if(this.currentMovieIndex < this.movies.length - 1){
          this.currentMovieIndex++;
      }
      else{
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
        this.currentMovieIndex = 0;
      }
    },
    addGenre() {
<<<<<<< HEAD
      let userAndGenresToAdd = {
        userId: this.$store.state.user.id,
        genres: this.genres,
      };
=======
      let userAndGenresToAdd = { userId: this.$store.state.user.id, genreIds: this.genres };
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
      movieService
        .addGenre(userAndGenresToAdd) //this calls movie service in the back-end
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
<<<<<<< HEAD
    currentMovie() {
      if (this.movies.length < 1) {
        return "Click Next To Swipe";
      } else {
        return this.movies[this.currentMovieIndex];
      }
    },
=======
    currentMovie(){
      if(this.movies.length < 1){
                        return "Click Next To Swipe";
      }
      else{
        return this.movies[this.currentMovieIndex];
      }
    }
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
  },
};
</script>


<style scoped>
#left-panel {
  background-color: #f7f7f7;
  flex-basis: 25%;
  display: flex;
  flex-direction: column;
  align-content: center;
  justify-content: flex-start;
  align-items: center;
}
#right-panel {
  display: flex;
  justify-content: flex-start;
  flex-direction: column;
  flex-basis: 75%;
  align-content: center;
}
h1 {
  font-family: fantasy;
  color: black;
  text-align: center;
  text-shadow: 10px 10px 10px orange;
  font-size: 300%;
}
h2 {
  font-family: fantasy;
  color: black;
  text-align: left;
  text-shadow: 10px 10px 10px orange;
  font-size: 150%;
}
#genre-choice {
  font-family: fantasy;
  color: black;
  text-align: center;
  text-shadow: 10px 10px 10px orange;
  align-content: center;
  align-items: center;
  justify-content: center;
  font-size: 300%;
}
#update-genre-button {
  height: 40px;
  width: 75%;
  font-size: 120%;
  border-radius: 10px;
  font-family: fantasy;
  background: orange;
  text-align: center;
  color: black;
  margin: 0px 40px;
  align-content: center;
  align-items: center;
  justify-content: center;
}
#genres {
  height: 90vh;
  width: 120%;
  font-size: 175%;
  border-radius: 10px;
  color: orange;
  font-family: fantasy;
  font-weight: bold;
  text-decoration: underline;
  display: flex;
  align-content: center;
  align-items: center;
  justify-content: flex-start;
  margin: 0px 0px;
  text-indent: 10%;
  border: 2px solid green;
  border-radius: 4px;
}

.main {
  display: flex;
}

#right-panel-row-button {
  display: flex;
  justify-content: space-around;
  width: 100%;
}

#right-panel-middle-row {
  display: flex;
  justify-content: space-around;
  width: 100%;
  align-items: center;
}

#movie-poster {
  height: 100%;
  width: 50%;
<<<<<<< HEAD
  justify-content: center;
  align-items: center;
=======
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
}

#abhore-button {
  background: url(../img/abhore-ticket.jpg) center / cover no-repeat;
  width: 50%;
  height: 25%;
  margin: 5px;
  border-radius: 5px;
  font-family: fantasy;
  border: 2px solid red;
  font-size: 200%;
}

#adore-button {
  background: url(../img/adore-ticket.jpg) center / cover no-repeat;
  width: 50%;
  height: 25%;
  margin: 5px;
  border-radius: 5px;
  font-family: fantasy;
  border: 2px solid green;
  font-size: 200%;
}

#previous-button {
  height: 40px;
  width: 20%;
  font-size: 120%;
<<<<<<< HEAD
=======
  border-radius: 10px;
  font-family: fantasy;
  background: orange;
  text-align: center;
  color: black;
  margin: 0px 40px;
  align-content: center;
  align-items: center;
  justify-content: center;
}

#favorites-button {
  height: 40px;
  width: 20%;
  font-size: 120%;
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
  border-radius: 10px;
  font-family: fantasy;
  background: orange;
  text-align: center;
<<<<<<< HEAD
=======
  color: black;
  margin: 0px 40px;
  align-content: center;
  align-items: center;
  justify-content: center;
}

#next-button {
  height: 40px;
  width: 20%;
  font-size: 120%;
  border-radius: 10px;
  font-family: fantasy;
  background: orange;
  text-align: center;
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
  color: black;
  margin: 0px 40px;
  align-content: center;
  align-items: center;
  justify-content: center;
<<<<<<< HEAD
}

#favorites-button {
  height: 40px;
  width: 20%;
  font-size: 120%;
  border-radius: 10px;
  font-family: fantasy;
  background: orange;
  text-align: center;
  color: black;
  margin: 0px 40px;
  align-content: center;
  align-items: center;
  justify-content: center;
}

#next-button {
  height: 40px;
  width: 20%;
  font-size: 120%;
  border-radius: 10px;
  font-family: fantasy;
  background: orange;
  text-align: center;
  color: black;
  margin: 0px 40px;
  align-content: center;
  align-items: center;
  justify-content: center;
}

#poster-flex-box {
  display: flex;
  align-items: center;
  justify-content: center;
  align-content: center;
  height: 600px;
  width: 800px;
}

#overview-box {
  display: flex;
  align-items: center;
  justify-content: center;
  align-content: center;
=======
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
}
</style>
