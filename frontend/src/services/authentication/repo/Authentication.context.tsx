import React, { createContext, FC } from "react";
import useAuthHook from "./Authentication.hook";

// our custom authentication context
export const AuthenticationContext = createContext({
  isLoggedIn: false,
  isLoading: true,
  error: "",
  currentUser: null,
  login: async (email: string, password: string) => {},
  register: async (email: string, password: string) => {},
  logout: async () => {},
});
interface Props {
  // any props that come into the component
}
// our authentication context provider object
const AuthenticationContextProvider: FC<Props> = ({ children }) => {
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
