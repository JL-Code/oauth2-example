// const isProduction = process.env.NODE_ENV === "production";
const path = require("path");

Object.keys(process.env).forEach((key) => {
    // 仅在 development 下显示 VUE_APP_ 前缀的环境变量
    if (key.indexOf("VUE_APP_") > -1 && process.env.NODE_ENV === "development") {
        console.log("环境变量：" + key, process.env[key]);
    }
});

module.exports = {
    lintOnSave: false,
    publicPath: process.env.VUE_APP_PUBLIC_PATH,
    chainWebpack: (config) => {
        // alias 别名设置语法参考：
        // https://www.kancloud.cn/ctwee/ctwee/916439
        // https://github.com/vuejs/vue-cli/issues/2398
        // 参考： https://code.visualstudio.com/docs/languages/jsconfig 加上让 vscode path intellisense 能正确识别
        config.resolve.alias
            .set("@$", resolve("src"));
    },
    devServer: {
        disableHostCheck: true, // 禁用主机检查
        proxy: {
            "/oauth/": {
                target: process.env.VUE_APP_API,
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    "^/oauth": "/oauth",
                },
            },
            "/api/uaa/": {
                target: process.env.VUE_APP_API,
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    "^/api/uaa": "/api/uaa",
                },
            },
            "/api/user/": {
                target: process.env.VUE_USER_SERVER_API,
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    "^/api/user/": "/api/user/",
                },
            },
            "/api-uaa": {
                target: process.env.VUE_APP_API,
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    "^/api-uaa": "/api-uaa",
                },
            }
        }
    },
    // disable source map in production
    productionSourceMap: true,
    // babel-loader no-ignore node_modules/*
    transpileDependencies: ["@babel", "resize-detector"],
};

// 解析路径
function resolve(dir) {
    return path.join(__dirname, dir);
}