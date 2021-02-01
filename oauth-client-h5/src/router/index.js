import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import Home from "@/pages/Hom";
import UaaPage from "@/pages/uaa/UaaPage";
import OtherPage from "@/pages/uaa/OtherPage";
import OAuthTransferPage from "@/pages/oauth2/OAuthTransferPage";
import User from "@/pages/oauth2/User";

// 路由规则数组
const routes = [
    {path: "/", component: Home},
    {path: "/uaa", component: UaaPage},
    {path: "/other", component: OtherPage},
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