<template>
  <div class="uaa-page">
    <OAuth2Flow :authorization-server="authorizationServer" :client-id="clientId" :redirect-uri="redirectUri"></OAuth2Flow>
  </div>
</template>

<script>
import OAuth2Flow from "@/components/OAuth2Flow";

export default {
  name: "UaaPage",
  components: {OAuth2Flow},
  data() {
    return {
      authorizationServer: "http://localhost:8088",
      clientId: "clientId",
      redirectUri: "http://localhost:8082/oauth2/transfer-page",
    }
  },
  created() {
    window.document.title = "UAA 授权中心";
  }
}
</script>

<style scoped>

</style>