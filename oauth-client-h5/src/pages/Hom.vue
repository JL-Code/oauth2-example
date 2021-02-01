<template>
  <div class="home-page">
    <OAuth2Flow></OAuth2Flow>
    <p>
      <router-link to="/uaa">UAA 授权中心</router-link>
      <router-link to="/other">Other 授权中心</router-link>
    </p>
  </div>
</template>

<script>
import OAuth2Flow from "@/components/OAuth2Flow";

export default {
  name: "Home",
  components: {OAuth2Flow}
}
</script>

<style scoped>

</style>