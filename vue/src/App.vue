<template>
  <div id="app">
    <!-- <div id="nav">
      <router-link class="topbanner" v-bind:to="{ name: 'home' }"
        >Home</router-link
      >&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
    -->
    <router-view />
  </div>
</template>

<style scoped>
</style>
