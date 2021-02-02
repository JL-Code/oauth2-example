<template>
  <div class="oauth-transfer-page">
    <p>code:{{ $route.query.code }}</p>
    <p v-if="!$route.query.code">加载中...</p>
    <button v-else type="button" @click="consumeAuthorizationCode">
      获取访问令牌
    </button>
    <p>accessTokenResponse</p>
    <p>{{ accessTokenResponse }}</p>
  </div>
</template>
<script>
import oauth2Client from "@/services/oauth2-client";

export default {
  name: "OAuthTransferPage",
  data() {
    return {
      accessTokenResponse: {},
    };
  },
  methods: {
    consumeAuthorizationCode() {
      let code = this.$route.query.code;
      // 向后台发起消费 code 的请求
      oauth2Client
        .consumeAuthorizationCode(code)
        .then((res) => {
          this.accessTokenResponse = res.data;
          let { access_token, openid, userid } = this.accessTokenResponse;
          this.$router.push({
            name: "userPage",
            query: {
              accessToken: access_token,
              userId: userid,
              openId: openid,
            },
          });
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
};
</script>