import axios from "axios";

const httpClient = axios.create({
    timeout: 25000
});

export default httpClient;
