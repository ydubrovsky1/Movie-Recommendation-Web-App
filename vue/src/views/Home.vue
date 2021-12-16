<template>
  <div class="home">
    <Header />
    <div class="main">
      <section id="left-panel">
        <h2>Selected Genres</h2>
        <tbody>
          <!--$store.state.genres-->
          <tr
            id="selected-genres"
            v-for="genre in $store.state.genres"
            v-bind:key="genre.id"
          >
            <td>
              {{ genre.genreName }}
            </td>

            <td>
              <button v-on:click="deleteGenre(genre.id)">X</button>
            </td>
          </tr>
        </tbody>

        <form v-on:submit.prevent="loadMovieRecs()">
          <button id="rec-movie" type="submit">
            See Movie Recs Based on Favorite Movie
          </button>
        </form>
        <h2 id="genre-choice">Add Genres:</h2>
        <form id="genre-dropdown" v-on:submit="addGenre">
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
            <option value="878" v-if="!containsGenre(878)">
              Science Fiction
            </option>
            <option value="10770" v-if="!containsGenre(10770)">TV Movie</option>
            <option value="53" v-if="!containsGenre(53)">Thriller</option>
            <option value="10752" v-if="!containsGenre(10752)">War</option>
            <option value="37" v-if="!containsGenre(37)">Western</option>
          </select>
          <br />
          <button id="update-genre-button" type="submit">Update Genres</button>
        </form>
      </section>
      <section id="right-panel">
        <h1>Browse Movie</h1>
        <div id="swipe-box">
          <button
            id="swiping-button"
            v-if="this.movies.length < 1"
            v-on:click="loadMoviesByGenre()"
          >
            Start Swiping Movies In Your Recommended Genres
          </button>
        </div>
        <section id="movie-table" v-if="this.movies.length > 0">
          <div id="right-panel-middle-row" v-if="this.movies.length > 0">
            <button id="abhore-button">Abhore</button>
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
              <br />
              <div id="right-panel-row-button">
                <button id="previous-button">Previous</button>
                <button id="watchlist-button" v-on:click="addToWatchlist()">
                  Add To Watchlist
                </button>
                <button id="next-button" v-on:click="updateCurrentMovie()">
                  Next
                </button>
              </div>

              <br />
              <div id="overview-box">
                <tr>
                  <td>Genre: {{ currentMovie.genre_ids }}</td>
                </tr>
                <tr>
                  <td id="movie-desc">Overview: {{ currentMovie.overview }}</td>
                </tr>
                <tr>
                  <td>Release Date: {{ currentMovie.release_date }}</td>
                </tr>
              </div>
            </tbody>

            <button
              id="adore-button"
              v-on:click="
                addToFavorites();
                updateCurrentMovie();
              "
            >
              Adore
            </button>
          </div>

          <!--show one movie at a time in table-->
        </section>
      </section>
    </div>

    <!--show all movies in table
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
  beforeMount() {
    let userAndGenresToAdd = {
      userId: this.$store.state.user.id,
      genreIds: [],
    };
    movieService.getGenres(userAndGenresToAdd).then((response) => {
      this.$store.commit("SET_GENRES", response.data);
    });
  },
  methods: {
    deleteGenre(genreId) {
      let userId = this.$store.state.user.id;
      movieService
        .deleteGenre(userId, genreId) //this calls movie service in the back-end
        //response
        .then((response) => {
          if (response.data == true) {
            this.$alert("Delete Worked!");
            let userAndGenresToAdd = {
              userId: this.$store.state.user.id,
              genreIds: [],
            };
            movieService.getGenres(userAndGenresToAdd).then((response) => {
              this.$store.commit("SET_GENRES", response.data);
            });
          } else {
            this.$alert("Delete No Work!");
          }
        });
    },
    addToWatchlist() {
      let userPlusCurrentMovieId = {
        userId: this.$store.state.user.id,
        movieId: this.movies[this.currentMovieIndex].id,
      };
      movieService.addToWatchlist(userPlusCurrentMovieId).then((response) => {
        this.$store.commit("SET_WATCHLIST", response.data);
      });
      this.$alert("Favorites Updated!");
    },
    containsGenre(genreId) {
      for (let i = 0; i < this.$store.state.genres.length; i++) {
        if (this.$store.state.genres[i].id == genreId) {
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
    addToFavorites() {
      this.$store.commit("SET_FAVORITES", this.movies[this.currentMovieIndex]);

      let userPlusCurrentMovieId = {
        userId: this.$store.state.user.id,
        movieId: this.movies[this.currentMovieIndex].id,
      };
      movieService.addFavorite(userPlusCurrentMovieId).then((response) => {
        if (response.data == true) {
          this.$store.commit(
            "SET_FAVORITES",
            this.movies[this.currentMovieIndex]
          );
          this.$alert("Favorites Updated!");
        } else {
          this.$alert("Already In Favorites!");
        }
      });
    },

    updateCurrentMovie() {
      if (this.currentMovieIndex < this.movies.length - 1) {
        this.currentMovieIndex++;
      } else {
        this.currentMovieIndex = 0;
      }
    },
    addGenre() {
      let userAndGenresToAdd = {
        userId: this.$store.state.user.id,
        genreIds: this.genres,
      };
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
          //do %2C instead of + to get movies that include every genre, instead of movies that include at least one of the genres
          myGenreUrl = myGenreUrl + this.$store.state.genres[i].id + "+";
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
    currentMovie() {
      if (this.movies.length < 1) {
        return "Click Next To Swipe";
      } else {
        return this.movies[this.currentMovieIndex];
      }
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
  align-items: center;
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
  height: 50vh;
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
  border: 5px solid black;
  align-content: left;
  border-radius: 25px;
  object-fit: cover;
}

#abhore-button {
  background: url(../img/abhore-ticket.jpg) center / cover no-repeat;
  width: 50%;
  height: 25%;

  border-radius: 5px;
  font-family: fantasy;
  border: 2px solid red;
  font-size: 200%;
}

#adore-button {
  background: url(../img/adore-ticket.jpg) center / cover no-repeat;
  width: 100%;
  height: 50%;

  border-radius: 5px;
  font-family: fantasy;
  border: 2px solid green;
  font-size: 200%;
}

#previous-button,
#watchlist-button {
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
  font-family: Georgia, "Times New Roman", Times, serif;
  font-size: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  align-content: center;
  flex-direction: column;
  border: black;
  max-width: 100%;
  flex-grow: inherit;
}
#swipe-box {
  display: flex;
  justify-content: center;
}

#swiping-button {
  height: 55px;
  width: 30%;
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

#movie-table {
  text-align: center;
}
#movie-desc {
  width: 800px;
  height: 150px;
}

#selected-genres {
  width: 125%;
  font-size: 120%;
  border-radius: 10px;
  color: orange;
  font-family: fantasy;
  display: flex;
  font-weight: bold;
  text-decoration: underline;
  align-content: center;
  align-items: center;
  justify-content: space-between;
  margin: 0px 0px;

  border: 2px solid green;
  border-radius: 4px;
}

#rec-movie {
  height: 40px;
  width: 100%;
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

@media (max-width: 450px) {
  body header {
    flex-wrap: wrap;
    justify-content: center;
  }

  #left-panel {
    flex-wrap: wrap;
    justify-content: center;
  }

  #right-panel {
    flex-wrap: wrap;
    justify-content: center;
  }

  #delete-genre {
    font-size: 500%;
    border: black;
    background: pink;
    color: red;
  }
}
</style>
