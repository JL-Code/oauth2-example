<template>
  <div class="oauth-transfer-page">
    code:{{ code }}
    <p>加载中...</p>
  </div>
</template>
<script>
export default {
  name: "OAuthTransferPage",
  data() {
    return {
      code: ""
    }
  },
  created() {
    let code = this.$route.query.code;
    if (code) {
      // 向后台发起消费 code 的请求
      this.code = code;
    }
  }
}
</script>