<template>
  <body>
    <main>
      <div id="login" class="text-center">
        <form class="form-signin" @submit.prevent="login">
          <h1 class="h3 mb-3 font-weight-normal">Welcome to Movie Baker</h1>
          <h2>Please Sign In</h2>

          <div
            class="alert alert-danger"
            id="alert-message"
            role="alert"
            v-if="invalidCredentials"
          >
            Invalid username and password!
          </div>
          <div
            class="alert alert-success"
            id="alert-message"
            role="alert"
            v-if="this.$route.query.registration"
          >
            Thank you for registering, please sign in.
          </div>
          <div id="username">
            <label for="username" class="sr-only">Username: </label>
            <input
              type="text"
              id="username"
              class="form-control"
              placeholder="Username"
              v-model="user.username"
              required
              autofocus
            />
            <br />
            <label for="password" class="sr-only">Password: </label>
            <input
              type="password"
              id="password"
              class="form-control"
              placeholder="Password"
              v-model="user.password"
              required
            />
          </div>
          <div id="loginfooter">
            <br />
            <button type="submit" class="sign-in-button">Sign in</button>
            <br />
            <router-link :to="{ name: 'register' }"
              >Need an account?</router-link
            >
          </div>
        </form>
      </div>
    </main>
    <footer></footer>
  </body>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
body {
  margin: 0;
  padding: 0;
  min-height: 100%;
  min-width: 1024px;

  background: url(../img/movie mar 2.png) center / cover no-repeat;
}

h1 {
  font-family: fantasy;
  color: white;
  text-align: center;
  text-shadow: 10px 10px 10px orange;
}

h2 {
  font-family: fantasy;
  color: white;
  text-align: center;
  text-shadow: 10px 10px 10px blue;
}

#login {
  font-family: fantasy;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
  min-height: 100vh;
  font-size: 200%;
}

#username {
  align-items: center;
  justify-content: center;
  display: flex;
  flex-direction: column;
}

#loginfooter {
  align-items: center;
  justify-content: center;
  display: flex;
  flex-direction: column;
  text-shadow: 10px 10px 10px green;
}

.sign-in-button {
  height: 100%;
  width: 30%;
  font-size: 100%;
  border-radius: 10px;
  font-family: fantasy;
  background: orange;
  color: green;
}
#nav {
  display: none;
}
a:visited {
  color: white;
}
a:active {
  color: orange;
}
.form-control {
  font-size: 75%;
}
#alert-message {
  display: flex;
  justify-content: center;
  align-items: center;
  align-content: center;
}
</style>
