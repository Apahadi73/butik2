import { useEffect, useState } from "react";

export default () => {
  const [userInfo, setUserInfo] = useState(null);

  const getUserInfo = () => {
    const userLocal = localStorage.getItem("userInfo");
    if (userLocal) {
      setUserInfo(JSON.parse(userLocal));
    }
  };

  const updateUserInfo = (userInfo) => {
    localStorage.setItem("userInfo", JSON.stringify(userInfo));
    setUserInfo(userInfo);
  };

  const removeUserInfo = () => {
    localStorage.removeItem("userInfo");
    setUserInfo(null);
  };

  const clearLocalStorage = () => {
    localStorage.clear();
  };

  return {
    userInfo,
    getUserInfo,
    updateUserInfo,
    removeUserInfo,
    clearLocalStorage,
  };
};
