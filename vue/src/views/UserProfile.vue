<template>
  <div>
    <Header />
    <header>
      <h1>My Profile</h1>
    </header>

    <body>
      <p>Below here you can find a list of the movies you have favorited</p>
      <main>
        <tbody>
          <tr v-for="movie in $store.state.movies" v-bind:key="movie.id">
            <td>my movie:</td>
            <td>{{ movie.id }}</td>
            <td>{{ movie.original_title }}</td>
            <td>{{ movie.genre_ids }}</td>
            <td>{{ movie.overview }}</td>
            <td>{{ movie.release_date }}</td>
            <td>{{ movie.poster_path }}</td>
          </tr>
        </tbody>
      </main>
    </body>
  </div>
</template>

<script>
import Header from "./Header.vue";

export default {
  components: {
    Header,
  },
  data() {
    return {
      movies: [],
    };
  },
};
</script>

<style scoped>
h1 {
  font-family: fantasy;
  color: black;
  text-align: center;
  text-shadow: 10px 10px 10px orange;
  font-size: 300%;
}
</style>
