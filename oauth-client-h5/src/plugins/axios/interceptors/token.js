//
// export default {
//     use(axios) {
//         // 添加请求拦截器
//         axios.interceptors.request.use(
//             config => {
//                 // 附加令牌信息
//                 try {
//                     const accessToken = util.auth.getAccessToken();
//                     // 当 accessToken 有效且不存在 Authorization 自动添加。
//                     if (accessToken && !config.headers.Authorization) {
//                         config.headers.Authorization = "Bearer " + accessToken;
//                     }
//                 } catch (e) {
//                     util.log.danger(e.message);
//                 }
//                 return config;
//             },
//             error => {
//                 return Promise.reject(error);
//             }
//         );
//
//         // 添加响应拦截器
//         axios.interceptors.response.use(
//             response => {
//                 // 重置服务器时间
//                 if (response.data.timestamp) {
//                     util.time.resetTime(response.data.timestamp);
//                 }
//                 return response.data;
//             },
//             error => {
//                 // 1.请求已完成 得到服务器响应 但是http状态码不是2xx范围内
//                 if (error.response) {
//                     switch (error.response.status) {
//                         case 401:
//                             util.auth.clear();
//                             util.auth.redirectToAuth({ path: util.auth.getAuthPath() });
//                             return Promise.reject(error.response.data);
//                         default:
//                             return Promise.reject(error.response.data);
//                     }
//                 }
//                 // 2.发起请求时出错未收到任何响应
//                 else if (error.request) {
//                     let data = {
//                         errcode: error.code,
//                         errmsg: error.message,
//                         error: error
//                     };
//                     if (error.code === "ECONNABORTED") {
//                         data.errmsg = "服务不可用,网络超时";
//                     }
//
//                     return Promise.reject(data);
//                 }
//                 // 3.一些错误是在设置请求时触发的
//                 else {
//                     // Something happened in setting up the request that triggered an Error
//                     // console.log("Error", error.message, error.code);
//                     let data = {
//                         errcode: error.code,
//                         errmsg: error.message,
//                         error: error
//                     };
//                     return Promise.reject(data);
//                 }
//             }
//         );
//     }
// };
