import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export const store = Vue.observable({
  isNavOpen: false
});

export const mutations = {
  toggleNav() {
    store.isNavOpen = !store.isNavOpen
  }
};

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    genres: [{ "id": 12, "genreName": "Adventure" }],
    customUser: { "user": currentUser, "genres": [] },
    favorites: [],
    watchlist: [],
    abhorred: [],
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user', JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_GENRES(state, data) {
      state.user.preferredGenres = data;
      state.genres = data;
    },
    SET_FAVORITES(state, data) {
      state.favorites = data;
    },
    SET_WATCHLIST(state, data){
      state.watchlist.push(data);
    },
    ADD_ABHORRED(state, data){
      state.abhorred.push(data);
    },
    ADD_FAVORITES(state, data){
      state.favorites.push(data);
    },
    SET_ABHORRED(state, data){
      state.abhorred = data;
    }

  }
})
