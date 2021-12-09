<template>
  <body>
    <div id="register" class="text-center">
      <form class="form-register" @submit.prevent="register">
        <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
        <div class="alert alert-danger" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div id="labels">
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
          <label for="userage" class="sr-only">Age: </label>
          <input
            type="text"
            id="userage"
            class="form-control"
            placeholder="Age"
            v-model="user.age"
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
          <br />
          <label for="confirmpassword" class="sr-only"
            >Confirm Password:
          </label>
          <input
            type="password"
            id="confirmPassword"
            class="form-control"
            placeholder="Confirm Password"
            v-model="user.confirmPassword"
            required
          />
          <br />
        </div>
        <div id="createaccount">
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Create Account
          </button>
          <br />
          <router-link :to="{ name: 'login' }">Have an account?</router-link>
        </div>
      </form>
    </div>
  </body>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
#register {
  font-family: fantasy;
  color: orange;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: row;
  min-height: 100vh;
  font-size: 200%;
}

#labels {
  align-items: center;
  justify-content: center;
  display: flex;
  flex-direction: column;
}
#createaccount {
  align-items: center;
  justify-content: center;
  display: flex;
  flex-direction: column;
}
body {
  margin: 0;
  padding: 0;
  min-height: 100%;
  min-width: 1024px;

  background: url(../img/Bugs Bunny left.jpg) center / cover no-repeat;
}
a:visited {
  color: orange;
}
a:active {
  color: lightblue;
}
.btn {
  height: 100%;
  width: 45%;
  font-size: 75%;
  border-radius: 10px;
  font-family: fantasy;
  background: orange;
  color: green;
}
</style>
