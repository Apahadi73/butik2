import axios from "axios";

const productAxios = axios.create({
  baseURL: "https://dda9-64-189-13-133.ngrok.io/api/v1/products",
  timeout: 1000,
  headers: { "X-Custom-Header": "foobar" },
});

export default productAxios;
