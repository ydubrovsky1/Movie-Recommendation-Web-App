<template>
  <div class="header">
    <div id="header-title">
      <h1>Movie Baker</h1>
    </div>
    <nav class="main-nav">
      <burger-menu />
    </nav>
    <sidebar>
      <ul class="sidebar-panel-nav">
        <li><router-link v-bind:to="{ name: 'home' }">Home</router-link></li>
        <li><a href="#about">User Profile</a></li>
        <li><a href="#contact">Watch List</a></li>
        <li>
          <router-link v-bind:to="{ name: 'logout' }">Log Out</router-link>
        </li>
      </ul>
    </sidebar>
  </div>
</template>


<script>
import BurgerMenu from "../components/BurgerMenu.vue";
import Sidebar from "../components/Sidebar.vue";

export default {
  name: "Header",
  components: {
    BurgerMenu,
    Sidebar,
  },
};
</script>
<style scoped>
.header {
  background-color: black;
}
#header-title {
  font-family: fantasy;
  color: orange;
  text-align: center;
  text-shadow: 10px 10px 10px grey;
  font-size: 250%;
}
.nav {
  font-family: fantasy;
  color: lightblue;
  text-align: right;
}

html {
  height: 100%;
  overflow: hidden;
}

body {
  border: 0;
  margin: 0;
  padding: 0;
  font-family: "Lato";
  height: 100%;
  background: rgb(101, 31, 87);
  background: linear-gradient(
    45deg,
    rgba(101, 31, 87, 1) 0%,
    rgba(225, 113, 87, 1) 48%,
    rgba(249, 248, 113, 1) 100%
  );
}

.logo {
  align-self: center;
  color: #fff;
  font-weight: bold;
  font-family: "Lato";
}

.main-nav {
  display: flex;
  justify-content: flex-end;
  padding: 0.5rem 0.8rem;
}

ul.sidebar-panel-nav {
  list-style-type: none;
}

ul.sidebar-panel-nav > li > a {
  color: #fff;
  text-decoration: none;
  font-size: 1.5rem;
  display: block;
  padding-bottom: 0.5em;
}
</style>
