import axios from "axios";
import { useState } from "react";

export default ({ url, method, body }) => {
  const [errors, setErrors] = useState(null);

  const doRequest = async () => {
    setErrors(null);
    const response = await axios[method](url, body);
    return response.data;
    try {
    } catch (err) {
      if (err && err.response) {
        console.table(err);
        setErrors(
          <div>
            {err.response.data.errors.map((error) => {
              <Message key={error.message} variant="danger">
                {error.message}
              </Message>;
            })}
          </div>
        );
      }
    }
  };

  return { doRequest, errors };
};
