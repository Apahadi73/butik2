import axios from "axios";

export default ({ req }) => {
  //TODO: dont forget to undo the comments later on
  if (typeof window === "undefined") {
    // We are on the server
    return axios.create({
      baseURL:
        "http://ingress-nginx-controller.ingress-nginx.svc.cluster.local",
      headers: req.headers,
    });
  } else {
    // We must be on the browser
    return axios.create({
      baseUrl: "/",
    });
  }
};
