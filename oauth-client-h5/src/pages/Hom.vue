<template>
  <div class="home-page">
    <OAuth2Flow></OAuth2Flow>
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