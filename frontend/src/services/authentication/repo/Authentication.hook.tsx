import { useCallback, useState } from "react";
import aAxios from "../utilities/aAxios";

const useAuthHook = () => {
  const [isLoading, setIsLoading] = useState(true);
  const [currentUser, setCurrentUser] = useState(null);
  const [error, setError] = useState("");

  // logs in user using email and password
  const login = useCallback(async (email, password) => {
    setIsLoading(false);
  }, []);

  // registers new user using email and password
  const register = useCallback(async (email, password) => {
    setIsLoading(false);
    const isValid = validateInput(email, password);

    if (isValid) {
      try {
        const response = await aAxios.post("/register", {
          email,
          password,
        });
        console.log(response);
      } catch (e) {
        console.log(e);
      }
    } else {
      console.log(error);
    }
  }, []);

  // validates input fields
  const validateInput = (email: string, password: string) => {
    const isEmailValid = validateEmail(email);
    if (!isEmailValid) {
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
  // validates email using the regular expression
  const validateEmail = (email: string) => {
    const expression =
      /(?!.*\.{2})^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([\t]*\r\n)?[\t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([\t]*\r\n)?[\t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;

    return expression.test(String(email).toLowerCase());
  };

  // logs out user
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
