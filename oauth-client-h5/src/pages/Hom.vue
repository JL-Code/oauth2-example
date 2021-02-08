<template>
  <div class="home-page">
    <OAuth2Flow></OAuth2Flow>
    <p><router-link to="/uaa">UAA 授权中心</router-link></p>
    <p><router-link to="/other">Other 授权中心</router-link></p>
    <p><router-link to="/cloud/uaa">Cloud UAA 授权中心</router-link></p>

    <p>
      <button @click="getIntegrationUser">获取集成用户信息</button>
    </p>
    <p>{{ integrationUser }}</p>
  </div>
</template>

<script>
import OAuth2Flow from "@/components/OAuth2Flow";
import axios from "../plugins/axios";
export default {
  name: "Home",
  components: { OAuth2Flow },
  data() {
    return {
      integrationUser: {},
    };
  },
  methods: {
    getIntegrationUser() {
      axios
        .get("/integration/user")
        .then((res) => {
          this.integrationUser = res.data;
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
};
</script>

<style scoped>
</style>