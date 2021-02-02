import axios from "../plugins/axios";
import qs from "qs"
import { Base64 } from 'js-base64'

export default class {
    static consumeAuthorizationCode(code, clientId = "clientId",
        clientSecret = "clientSecret", endpoint = "/oauth/token",
        redirectUri = "http://localhost:8082/oauth2/transfer-page") {

        let rawCredentials = `${clientId}:${clientSecret}`;

        // http://127.0.0.1:9900/api-uaa/oauth/token
        let data = qs.stringify({
            grant_type: "authorization_code",
            code: code,
            redirect_uri: redirectUri
            // client_id: ""
        })

        console.log(rawCredentials);
        console.log(Base64.encode(rawCredentials));
        console.log(data);

        return axios.post(endpoint, data, {
            headers: {
                "Authorization": `Basic ${Base64.encode(rawCredentials)}`,
                "Content-Type": "application/x-www-form-urlencoded"
            }
        }
        )
    }

    static fetchUser(openid, accessToken, endpoint = "/api/user/userinfo") {
        endpoint = `${endpoint}?openId=${openid}`;
        return axios.get(endpoint, { headers: { "Authorization": `Bearer ${accessToken}` } });
    }
}