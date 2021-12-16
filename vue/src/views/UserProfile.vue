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
            <td>
              <img
                id="MImage"
                :src="
                  `https://www.themoviedb.org/t/p/w600_and_h900_bestv2/` +
                  favorite.poster_path
                "
              />
            </td>
            <br />
            <td id="MTitle">Movie title: {{ favorite.original_title }}</td>
            <br />
            <!-- <td id="MGenre">Genre: {{ favorite.genre_ids }}</td>
            <br /> -->
            <td id="MOverview">Movie Overview: {{ favorite.overview }}</td>
            <br />
            <td id="RDate">Release Date: {{ favorite.release_date }}</td>
            <br />
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
  justify-content: space-evenly;
  align-items: center;
  flex-direction: row;
  flex-wrap: wrap;
}
#MTitle {
  font-family: Georgia, "Times New Roman", Times, serif;
  display: flex;
  justify-content: center;
  align-items: center;
}
#RDate {
  font-family: Georgia, "Times New Roman", Times, serif;
  display: flex;
  justify-content: center;
  align-items: center;
}
#MGenre {
  font-family: Georgia, "Times New Roman", Times, serif;
  display: flex;
  justify-content: center;
  align-items: center;
}
#MOverview {
  font-family: Georgia, "Times New Roman", Times, serif;
  display: flex;
  justify-content: center;
  align-items: center;
  align-content: center;
  width: 600px;
  height: 100px;
}
#MImage {
  display: flex;
  justify-content: center;
  align-items: center;
  align-content: center;
  border: 5px solid black;
  border-radius: 25px;
  object-fit: cover;
  height: 100%;
  width: 100%;
}

@media (max-width: 450px) {
  body header {
    flex-wrap: wrap;
    justify-content: center;
  }

  #favorite-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-evenly;
    align-items: center;
    flex-direction: row;
    flex-wrap: wrap;
  }
  #MImage {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    align-content: center;
    border: 5px solid black;
    border-radius: 25px;
    object-fit: cover;
    height: 100%;
    width: 100%;
  }

  #MOverview {
    font-family: Georgia, "Times New Roman", Times, serif;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    align-content: center;
    width: 100%;
    height: 50%;
  }
}
</style>
