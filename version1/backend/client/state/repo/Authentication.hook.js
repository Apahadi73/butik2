import { useCallback, useEffect, useState } from "react";
import axios from "axios";
import useLocalStorage from "../hooks/useLocalStorage";

const authAxios = axios.create({
  baseURL: "/api/v1/authentication",
  timeout: 1000,
  headers: { "X-Custom-Header": "foobar" },
});

const useAuthHook = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [currentUser, setCurrentUser] = useState(null);
  const [error, setError] = useState("");

  const { updateUserInfo, getUserInfo, removeUserInfo } = useLocalStorage();

  useEffect(() => {
    const userInfoLocal = getUserInfo();
    if (userInfoLocal) {
      setCurrentUser(userInfoLocal);
    }
  }, []);

  // logs in user using email and password
  const login = useCallback(async (email, password) => {
    setIsLoading(true);
    const isValid = validateInput(email, password);
    try {
      if (isValid) {
        // api response handling
        const { data } = await authAxios.post("/login", {
          email,
          password,
        });
        if (data) {
          updateUserInfo(data);
          setCurrentUser(data);
        }
      }
    } catch (err) {
      setError(err.response.data.message);
    }
    setIsLoading(false);
  }, []);

  // registers new user using email and password
  const register = useCallback(
    async (name, email, password, confirmPassword) => {
      setIsLoading(true);
      const isValid = validateRegistration(
        name,
        email,
        password,
        confirmPassword
      );

      try {
        if (isValid) {
          // api response handling
          const { data } = await authAxios.post("/register", {
            email,
            password,
            name,
          });
          if (data) {
            updateUserInfo(data);
            setCurrentUser(data);
          }
        }
      } catch (err) {
        setError(err.response.data.message);
      }
      setIsLoading(false);
    },
    []
  );

  const validateRegistration = (name, email, password, confirmPassword) => {
    if (!name) {
      setError("Name missing");
    } else if (!email) {
      setError("Email missing");
    } else if (!password) {
      setError("Password missing");
    } else if (password.length < 6) {
      setError("Password must at least 6 characters long");
    } else if (!confirmPassword) {
      setError("Please re-enter the password");
    } else if (password !== confirmPassword) {
      setError("Passwords do not match");
    } else {
      setError("");
      return true;
    }
    return false;
  };

  // validates input fields
  const validateInput = (email, password) => {
    if (!email) {
      setError("Please enter a valid email");
    } else {
      if (!password) {
        setError("Empty password field");
      } else if (password.length < 6) {
        setError("Password must at least 6 characters long");
      } else {
        return true;
      }
    }
    return false;
  };

  // logs out user
  const logout = useCallback(async () => {
    setIsLoading(false);
    setError("");
    setCurrentUser(null);
    removeUserInfo();
  }, []);

  return {
    currentUser,
    isLoading,
    error,
    login,
    register,
    logout,
  };
};

export default useAuthHook;
