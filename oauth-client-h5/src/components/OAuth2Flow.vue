<template>
  <div class="hello">
    <img alt="Vue logo" src="../assets/logo.png">
    {{ msg }}
    <button type="button" @click="startup">网页授权</button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String,
    domain: {
      type: String,
      default: "http://localhost:8080"
    },
    clientId: {
      type: String,
      default: "clientId"
    },
    redirectUri: {
      type: String,
      default: "http://localhost:8081/oauth2/transfer-page"
    },
    scope: {
      type: String,
      default: "api_userinfo"
    },
    state: {
      type: String,
      default: () => Date.now().toString()
    }
  },
  methods: {
    buildAuthorizeUri() {
      let authorize_uri =
          `${this.domain}/oauth/authorize?client_id=${this.clientId}&response_type=code&redirect_uri=${encodeURIComponent(this.redirectUri)}&scope=${this.scope}&state=${this.state}`;
      // alert(authorize_uri);
      return authorize_uri;
    },
    startup() {
      window.location.href = this.buildAuthorizeUri();
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
