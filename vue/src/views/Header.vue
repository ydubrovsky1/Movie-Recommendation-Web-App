<template>
  <div class="header">
    <div id="header-title">
      <h1>Movie Baker</h1>
    </div>
    <div class="nav">
      <a href="#">Account Settings</a>
      <br />
      <a href="#">Favorite Movies</a>
      <br />
      <a href="#">Watch List</a>
      <br />
      <a href="#">Log Out</a>
    </div>
  </div>
</template>
<script>
export default {
  name: "Header",
};
</script>
<style scoped>
.header {
  background-color: black;
}
#header-title {
  font-family: fantasy;
  color: lightblue;
  text-align: center;
  text-shadow: 10px 10px 10px orange;
}
.nav {
  font-family: fantasy;
  color: lightblue;
  text-align: right;
}
</style>
