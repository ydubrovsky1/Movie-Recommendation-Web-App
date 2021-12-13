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
<<<<<<< HEAD
    genres: [{ "id": 12, "genreName": "Adventure" }],
    customUser: { "user": currentUser, "genres": [] },
=======
    genres: [{"id": 12, "genreName": "Adventure"}],
    customUser: {"user": currentUser, "genres": []},
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
    favorites: []
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

<<<<<<< HEAD
    SET_FAVORITES(state, data) {
=======
    SET_FAVORITES(state, data){
>>>>>>> 3452f2a56b2f90d5f338530d91c4ad4a22c2cbae
      state.favorites.push(data);
    }

  }
})
