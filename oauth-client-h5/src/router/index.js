import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import HelloWorld from "@/components/OAuth2Flow";
import OAuthTransferPage from "@/pages/oauth2/OAuthTransferPage";
import User from "@/pages/oauth2/User";
// 路由规则数组
const routes = [
    {path: "/", component: HelloWorld},
    {path: "/oauth2/transfer-page", name: "oauth2TransferPage", component: OAuthTransferPage},
    {path: "/oauth2/user", name: "userPage", component: User}
];

const router = new VueRouter(
    {
        mode: "history",
        routes
    }
);

export default router;