import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import Home from "@/pages/Hom";
import UaaPage from "@/pages/uaa/UaaPage";
import CloudUaaPage from "@/pages/cloud/UaaPage";
import CloudTransferPagee from "@/pages/cloud/TransferPage";
import OtherPage from "@/pages/uaa/OtherPage";
import OAuthTransferPage from "@/pages/oauth2/OAuthTransferPage";
import User from "@/pages/oauth2/User";
import CloudUser from "@/pages/cloud/User";

// 路由规则数组
const routes = [
    { path: "/", component: Home },
    { path: "/uaa", component: UaaPage },
    { path: "/other", component: OtherPage },
    { path: "/cloud/uaa", component: CloudUaaPage },
    { path: "/oauth2/cloud/transfer-page", component: CloudTransferPagee },
    { path: "/oauth2/transfer-page", name: "oauth2TransferPage", component: OAuthTransferPage },
    { path: "/oauth2/user", name: "userPage", component: User },
    { path: "/oauth2/cloud/user", name: "cloudUserPage", component: CloudUser }
];

const router = new VueRouter(
    {
        mode: "history",
        routes
    }
);

export default router;