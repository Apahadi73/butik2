import { useState } from "react";

export default () => {
  const [token, setToken] = useState(null);

  const getToken = () => {
    const token = localStorage.getItem("token");
    if (token) {
      setToken(token);
    }
  };

  const updateToken = (token) => {
    localStorage.setItem("token", token);
    setToken(token);
  };

  const removeToken = () => {
    localStorage.removeItem("token");
    setToken(null);
  };

  const clearLocalStorage = () => {
    localStorage.clear();
  };

  return { token, getToken, updateToken, removeToken, clearLocalStorage };
};
