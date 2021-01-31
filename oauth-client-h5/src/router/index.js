import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import OAuth2Flow from "@/components/OAuth2Flow";
import UaaPage from "@/pages/uaa/UaaPage";
import OAuthTransferPage from "@/pages/oauth2/OAuthTransferPage";
import User from "@/pages/oauth2/User";

// 路由规则数组
const routes = [
    {path: "/", component: OAuth2Flow},
    {path: "/uaa", component: UaaPage},
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