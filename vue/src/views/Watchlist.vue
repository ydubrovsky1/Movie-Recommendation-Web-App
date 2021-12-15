<template>
  <div>
    <Header />
    <header>
      <h1>My Watch List</h1>
    </header>

    <body>
      <p>Here is the list of movies you would like to watch</p>
      <main></main>
    </body>
  </div>
</template>

<script>
import Header from "./Header.vue";
export default {
  components: {
    Header,
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
</style>