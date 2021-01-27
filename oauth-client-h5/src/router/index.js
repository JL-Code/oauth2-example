import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import HelloWorld from "@/components/OAuth2Flow";
import OAuthTransferPage from "@/pages/auth/OAuthTransferPage";
// 路由规则数组
const routes = [
    {path: "/", component: HelloWorld},
    {path: "/oauth/transfer-page", component: OAuthTransferPage}
];

const router = new VueRouter(
    {routes}
);

export default router;