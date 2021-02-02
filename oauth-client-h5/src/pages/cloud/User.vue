<template>
  <div class="user oauth-form">
    <p><input type="text" v-model="openId" /></p>
    <p><input type="text" v-model="accessToken" /></p>
    <button type="button" @click="fetchUser">获取用户信息</button>
    <p>
      {{ user }}
    </p>
  </div>
</template>
<script>
import oauthClient from "@/services/oauth2-client";

export default {
  name: "User",
  data() {
    return {
      user: {},
      openId: "",
      userId: "",
      accessToken: "",
    };
  },
  created() {
    this.openId = this.$route.query.openId;
    this.accessToken = this.$route.query.accessToken;
  },
  methods: {
    fetchUser() {
      let id = this.openId || this.userId;
      oauthClient
        .fetchUser(id, this.accessToken, "/api/uaa/common/current-user")
        .then((res) => {
          this.user = res.data;
          console.log(res);
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
};
</script>
<style>
.oauth-form input {
  width: 300px;
}
</style>