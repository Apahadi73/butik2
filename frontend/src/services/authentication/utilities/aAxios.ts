import axios from "axios";

const aAxios = axios.create({
  baseURL: "http://127.0.0.1:3000/api/authentication/v1",
  timeout: 1000,
  headers: { "X-Custom-Header": "foobar" },
});

export default aAxios;
