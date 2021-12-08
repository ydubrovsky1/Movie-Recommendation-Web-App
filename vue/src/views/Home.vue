<template>
  <div class="home">
    <h1>Choose A Genre:</h1>
    <form>
      <select id="genres" @submit.prevent="addGenre">
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
          <button type="submit">Update Generes</button>
        </select>
    </form>
    <p>You must be authenticated to see this</p>
  </div>
</template>

<script>
export default {
  name: "home",
  components: {},
  data(){},
  methods: {
    addGenre(genreId){
      movieService.addGenre(genreId, this.$store.state.user)
      //response
    }

  }
};
</script>


<style scoped>
</style>
