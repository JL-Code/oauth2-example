<template>
  <div class="hello">
    <img alt="Vue logo" src="../assets/logo.png" />
    {{ msg }}
    <button type="button" @click="startup">网页授权</button>
  </div>
</template>

<script>
export default {
  name: "OAuth2Flow",
  props: {
    msg: String,
    authorizationServer: {
      type: String,
      default: "http://localhost:8080/",
    },
    clientId: {
      type: String,
      default: "clientId",
    },
    redirectUri: {
      type: String,
      default: "http://localhost:8081/oauth2/transfer-page",
    },
    scope: {
      type: String,
      default: "snsapi_userinfo",
    },
    state: {
      type: String,
      default: () => Date.now().toString(),
    },
  },
  methods: {
    buildAuthorizeUri() {
      //http://127.0.0.1:9900/api-uaa/oauth/authorize?client_id=app&redirect_uri=http://127.0.0.1:8081/callback.html&response_type=code&state=gOtmYf
      let authorize_uri = `${this.authorizationServer}/oauth/authorize?client_id=${
        this.clientId
      }&response_type=code&redirect_uri=${encodeURIComponent(
        this.redirectUri
      )}&scope=${this.scope}&state=${this.state}`;
      alert(authorize_uri);
      console.log(authorize_uri);
      return authorize_uri;
    },
    startup() {
      window.location.href = this.buildAuthorizeUri();
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
