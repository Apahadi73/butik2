import axios from "axios";

const authAxios = axios.create({
  baseURL: "http://127.0.0.1:3000/api/v1/authentication",
  timeout: 1000,
  headers: { "X-Custom-Header": "foobar" },
});

export default authAxios;
