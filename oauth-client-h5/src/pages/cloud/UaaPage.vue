<template>
  <div class="uaa-page oauth-form">
    <p>
      <select v-model="authorizationServer">
        <option value="https://identity.dev.highzap.com:8443">https://identity.dev.highzap.com:8443</option>
        <option value="http://localhost:7050">http://localhost:7050</option>
      </select>
    </p>
    <p><input type="text" v-model="clientId" /></p>
    <p><input type="text" v-model="redirectUri" /></p>
    <p><input type="text" v-model="apiServer" /></p>
    <OAuth2Flow
      :authorization-server="authorizationServer"
      :client-id="clientId"
      :redirect-uri="redirectUri"
    ></OAuth2Flow>
  </div>
</template>

<script>
import OAuth2Flow from "@/components/OAuth2Flow";

export default {
  name: "CloudUaaPage",
  components: { OAuth2Flow },
  data() {
    return {
      apiServer: "",
      authorizationServer: "https://identity.dev.highzap.com:8443",
      clientId: "clientId",
      redirectUri: "http://localhost:8080/oauth2/cloud/transfer-page",
    };
  },
  created() {
    window.document.title = "Cloud UAA 授权中心";
  },
};
</script>
<style>
.oauth-form input,select {
  width: 300px;
}
</style>