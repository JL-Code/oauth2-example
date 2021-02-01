<template>
  <div class="user">
    <p>
      <input type="text" v-model="openId">
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
      openId: "",
      accessToken: ""
    }
  },
  created() {
    this.openId = this.$route.query.openId;
    this.accessToken = this.$route.query.accessToken;
  },
  methods: {
    fetchUser() {
      oauthClient.fetchUser(this.openId, this.accessToken).then(res => {
        this.user = res.data;
        console.log(res);
      }).catch(err => {
        console.error(err)
      })
    }
  }
}
</script>