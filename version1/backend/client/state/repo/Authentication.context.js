import React, { createContext, FC } from "react";
import useAuthHook from "./Authentication.hook";

// our custom authentication context
export const AuthenticationContext = createContext({
  isLoggedIn: false,
  isLoading: false,
  error: "",
  currentUser: null,
  login: async (email, password) => {},
  register: async (email, password, name, confirmPassword) => {},
  logout: async () => {},
});

// our authentication context provider object
const AuthenticationContextProvider = ({ children }) => {
  const { currentUser, isLoading, error, login, register, logout } =
    useAuthHook();

  return (
    <AuthenticationContext.Provider
      value={{
        isLoggedIn: currentUser != null,
        isLoading: isLoading,
        error: error,
        currentUser: currentUser,
        login: login,
        register: register,
        logout: logout,
      }}
    >
      {children}
    </AuthenticationContext.Provider>
  );
};

export default AuthenticationContextProvider;
