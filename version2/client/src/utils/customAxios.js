import Axios from "axios";

const BASE_URL = "http://butik.dev"

const axiosClient = Axios.create({
    baseURL: BASE_URL,
    timeout: 1000,
    headers: {'X-Custom-Header': 'foobar'}
});

export default axiosClient