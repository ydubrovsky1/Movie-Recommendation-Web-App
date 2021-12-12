<template>
  <div class="home">
    <Header />
    <div class="main">
      <section id="left-panel">
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
          </select>
          <br />
          <button id="update-genre-button" type="submit">Update Genres</button>
        </form>
      </section>
      <section id="right-panel">
        <h1>Browse Movie</h1>
        <div id="right-panel-middle-row">
          <button id="abhore-button">Abhore</button>
          <img id="movie-poster" src="../img/trapped-movie-poster.jpg" />
          <button id="adore-button">Adore</button>
        </div>

        <br />
        <br />
        <div id="right-panel-row-button">
          <button>Previous</button>
          <button>Add To Favorites</button>
          <button>Next</button>
        </div>
      </section>
    </div>
    <!-- <tbody>
      <tr v-for="genre in this.genres" v-bind:key="genre.id">
        <td>{{ genre}}</td>
      </tr>
    </tbody> -->

    <tbody>
      <!--$store.state.genres-->
      <tr v-for="genre in $store.state.genres" v-bind:key="genre.id">
        <td>{{ genre.genreName }}</td>
      </tr>
    </tbody>

    <form v-on:submit.prevent="loadMoviesByGenre()">
      <button type="submit">See Movies By Genre</button>
    </form>

    <form v-on:submit.prevent="loadMovieRecs()">
      <button type="submit">See Movie Recs Based on Favorite Movie</button>
    </form>

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
    };
  },
  methods: {
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
}
h2 {
  font-family: fantasy;
  color: black;
  text-align: left;
  text-shadow: 10px 10px 10px orange;
}
#genre-choice {
  font-family: fantasy;
  color: black;
  text-align: left;
  text-shadow: 10px 10px 10px orange;
  margin: 20px 100px;
  align-content: center;
  align-items: center;
  justify-content: center;
}
#update-genre-button {
  height: 40px;
  width: 75%;
  font-size: 80%;
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
  height: 42vh;
  width: 100%;
  font-size: 100%;
  border-radius: 10px;
  color: orange;
  font-family: fantasy;
  font-weight: bold;
  text-decoration: underline;
  display: flex;
  align-content: center;
  align-items: center;
  justify-content: center;
  margin: 0px 20px;
  text-indent: 1%;
  border: 2px solid green;
  border-radius: 4px;
}

img {
  height: 100%;
  width: 50%;
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
}

#movie-poster {
}

#abhore-button {
  background: url(../img/movie mar 2.png) center / cover no-repeat;
}

#adore-button {
  background: url(../img/movie mar 2.png) center / cover no-repeat;
}
</style>
