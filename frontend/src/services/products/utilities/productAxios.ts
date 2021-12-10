import axios from "axios";

const productAxios = axios.create({
  baseURL: "http://10.0.2.2:8000/api/v1/products",
  timeout: 1000,
  headers: { "X-Custom-Header": "foobar" },
});

export default productAxios;
