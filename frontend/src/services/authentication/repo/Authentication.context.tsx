import { createContext } from "react";
import useAuthHook from "./Authentication.hook";

// our custom authentication context
export const AuthenticationContext = createContext({
  isLoggedIn: false,
  isLoading: true,
  error: null,
  currentUser: null,
  login: async (email: string, password: string) => {},
  register: async (email: string, password: string) => {},
  logout: async () => {},
});

// our authentication context provider object
export const AuthenticationContextProvider = (children: React.ReactNode) => {
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
