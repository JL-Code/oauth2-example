<template>
  <div class="user">
    <p>
      <input type="text" v-model="userId">
      <input type="text" v-model="accessToken">
    </p>
    <button type="button" @click="fetchUser">获取用户信息</button>
    <p>
      {{ user }}
    </p>
  </div>
</template>
<script>
import oauthClient from '@/services/oauth2-client'

export default {
  name: "User",
  data() {
    return {
      user: {},
      userId: "",
      accessToken: ""
    }
  },
  created() {
    this.userId = this.$route.query.userId;
    this.accessToken = this.$route.query.accessToken;
  },
  methods: {
    fetchUser() {
      oauthClient.fetchUser(this.userId, this.accessToken).then(res => {
        this.user = res.data;
        console.log(res);
      }).catch(err => {
        console.error(err)
      })
    }
  }
}
</script>