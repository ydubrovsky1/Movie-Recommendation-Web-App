<template>
  <body>
    <main>
      <div id="login" class="text-center">
        <!--
        <form class="form-signin" @submit.prevent="login">
          <h1 class="h3 mb-3 font-weight-normal">Welcome to Baker Movies</h1>
          <h2>Please Sign In</h2>

          <div
            class="alert alert-danger"
            role="alert"
            v-if="invalidCredentials"
          >
            Invalid username and password!
          </div>
          <div
            class="alert alert-success"
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
        -->
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
  min-height: 100vh;
  background: url(../img/old stage cropped.jpg) center / cover no-repeat;
}

h1 {
  font-family: fantasy;
  color: white;
  text-align: center;
}

h2 {
  font-family: fantasy;
  color: white;
  text-align: center;
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
}

.sign-in-button {
  height: 100%;
  width: 50%;
  font-size: 100%;
  font-family: fantasy;
  background: rgb(201, 172, 7);
  color: black;
}
</style>
