import axios from "../plugins/axios";
import qs from "qs"

export default class {
    static consumeAuthorizationCode(code) {
        let endpoint = "/oauth/token";

        let data = qs.stringify({
            grant_type: "authorization_code",
            code: code,
            redirect_uri: "http://localhost:8081/oauth2/transfer-page"
            // client_id: ""
        })

        return axios.post(endpoint, data, {
                headers: {
                    "Authorization": "Basic Y2xpZW50SWQ6Y2xpZW50U2VjcmV0",
                    "Content-Type": "application/x-www-form-urlencoded"
                }
            }
        )
    }

    static fetchUser(userId, accessToken) {
        let endpoint = `/oauth/userinfo?userId=${userId}`;
        return axios.get(endpoint, {headers: {"Authorization": `Bearer ${accessToken}`}});
    }
}