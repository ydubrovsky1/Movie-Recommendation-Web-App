<template>
  <div class="home">
    <h1>Choose A Genre:</h1>
    <form v-on:submit.prevent="addGenre(genreId)">
      <select id="genres"  multiple>
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
        <button type="submit">Update Genres</button>
        
    </form>
    
      <button type="submit">Show Genres</button>

   <tbody>
      <tr v-for="genre in $store.state.genres" v-bind:key="genre.id">
        <td>{{ genre.id }}</td>
        <td>{{ genre.genreName }}</td>
        <td>please show up</td>
      </tr>
      <tr>also please show up</tr>
    </tbody>

    
  </div>
</template>

<script>
import Vue from "vue";
import movieService from "../services/MovieService";
import VueSimpleAlert from "vue-simple-alert";
Vue.use(VueSimpleAlert);


export default {
  name: "home",
  components: {},
  data(){
    return {
      genres: []   
      
    };
  },
  methods: {
    addGenre(genreId){
      
      movieService.addGenre(genreId, this.$store.state.user) //this calls movie service in the back-end
      //response
      .then(response => {this.$store.commit("SET_GENRES", response.data)}); //
       this.$alert("Hello Vue Simple Alert.");
    },



  },

  
};
</script>


<style scoped>
</style>
