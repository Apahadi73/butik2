import Axios from "axios";

const DEV_URL = ""
const PROD_URL = ""
const LOCAL_URL = "http://localhost:3000"

const axiosClient = Axios.create({
    baseURL: LOCAL_URL,
    timeout: 1000,
    headers: {'X-Custom-Header': 'foobar'}
});

export default axiosClient