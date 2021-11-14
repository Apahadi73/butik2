import { useCallback, useState } from "react";

const useAuthHook = () => {
  const [isLoading, setIsLoading] = useState(true);
  const [currentUser, setCurrentUser] = useState(null);
  const [error, setError] = useState(null);

  const login = useCallback(async (email, password) => {
    setIsLoading(false);
  }, []);

  const register = useCallback(async (email, password) => {
    setIsLoading(false);
  }, []);

  const logout = useCallback(async () => {
    setIsLoading(false);
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
