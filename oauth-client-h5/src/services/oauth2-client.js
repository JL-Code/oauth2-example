import axios from "../plugins/axios";
import qs from "qs"

export default class {
    static consumeAuthorizationCode(code) {
        let endpoint = "/api-uaa/oauth/token";
        // http://127.0.0.1:9900/api-uaa/oauth/token
        let data = qs.stringify({
            grant_type: "authorization_code",
            code: code,
            redirect_uri: "http://localhost:8082/oauth2/transfer-page"
            // client_id: ""
        })

        return axios.post(endpoint, data, {
                headers: {
                    // "Authorization": "Basic Y2xpZW50SWQ6Y2xpZW50U2VjcmV0",
                    "Authorization": "Basic YXBwOmFwcA==",
                    "Content-Type": "application/x-www-form-urlencoded"
                }
            }
        )
    }

    static fetchUser(openid, accessToken) {
        let endpoint = `/oauth/userinfo?openId=${openid}`;
        return axios.get(endpoint, {headers: {"Authorization": `Bearer ${accessToken}`}});
    }
}