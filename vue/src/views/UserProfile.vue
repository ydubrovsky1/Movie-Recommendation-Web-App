<template>
  <div>
    <Header />
    <header>
      <h1>My Profile</h1>
    </header>

    <body>
      <p>Below here you can find a list of the movies you have favorited</p>
      <main>
        <tbody id="favorite-list">
          <tr
            v-for="favorite in $store.state.favorites"
            v-bind:key="favorite.id"
          >
            <td id="MImage">
              <img
                :src="
                  `https://www.themoviedb.org/t/p/w600_and_h900_bestv2/` +
                  favorite.poster_path
                "
              />
            </td>
            <td id="MTitle">Movie title: {{ favorite.original_title }}</td>
            <td id="MGenre">Genre: {{ favorite.genre_ids }}</td>
            <td id="MOverview">Movie Overview: {{ favorite.overview }}</td>
            <td id="RDate">Release Date: {{ favorite.release_date }}</td>
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
      favorites: [],
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
p {
  font-family: fantasy;
  text-align: center;
}

#favorite-list {
  display: flex;
  justify-content: center;
  align-items: center;
}

#MTitle {
  display: flex;
  justify-content: center;
  align-items: center;
}

#RDate {
  display: flex;
  justify-content: center;
  align-items: center;
}

#MGenre {
  display: flex;
  justify-content: center;
  align-items: center;
}

#MOverview {
  display: flex;
  justify-content: center;
  align-items: center;
}
#MImage {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
