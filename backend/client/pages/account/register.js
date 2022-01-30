import React, { useState, useEffect } from "react";
import { Form, Button, Row, Col } from "react-bootstrap";
import Link from "next/link";
import { useRouter } from "next/router";
import { QueryClient, QueryClientProvider, useQuery } from "react-query";

import Message from "../../components/Message";
import Loader from "../../components/Loader";
import FormContainer from "../../components/FormContainer";
import useRequest from "../../hooks/useRequest";
import HTTP_METHOD from "../../constants/request-constants";
import useLocalStorage from "../../hooks/useLocalStorage";

const RegisterScreen = ({ location, history }) => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [message, setMessage] = useState(null);
  const [error, setError] = useState("");

  const { doRequest, errors } = useRequest({
    url: "/api/v1/authentication/register",
    method: HTTP_METHOD.POST,
    body: {
      email,
      password,
      name,
    },
  });

  const { updateUserInfo, userInfo, getUserInfo } = useLocalStorage();

  useEffect(() => {
    getUserInfo();
    if (userInfo) {
      router.push("/");
    }
  }, []);

  const loading = false;
  const router = useRouter();

  const submitHandler = async (e) => {
    e.preventDefault();
    validateForm(email, password, name, confirmPassword);
    if (!error) {
      try {
        const res = await doRequest();
        if (res) {
          updateUserInfo(res);
          router.push("/");
        }
      } catch (err) {
        setError(err.response.data.message);
      }
    }
  };

  const validateForm = (email, password, name, confirmPassword) => {
    if (!name) {
      setError("Name missing");
    } else if (!email) {
      setError("Email missing");
    } else if (!password) {
      setError("Password missing");
    } else if (!confirmPassword) {
      setError("Please re-enter the password");
    } else if (password !== confirmPassword) {
      setError("Passwords do not match");
    } else {
      setError("");
    }
  };

  return (
    <FormContainer>
      <h1 className="row justify-content-center">Sign Up</h1>
      {message && <Message variant="danger">{message}</Message>}
      {error && <Message variant="danger">{error}</Message>}
      {errors}
      {loading && <Loader />}
      <Form onSubmit={submitHandler}>
        <Form.Group controlId="name" className="mt-2">
          <Form.Label>Name</Form.Label>
          <Form.Control
            type="name"
            placeholder="Enter name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          ></Form.Control>
        </Form.Group>

        <Form.Group controlId="email" className="mt-2">
          <Form.Label>Email Address</Form.Label>
          <Form.Control
            type="email"
            placeholder="Enter email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          ></Form.Control>
        </Form.Group>

        <Form.Group controlId="password" className="mt-2">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Enter password"
            autoComplete="new-password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          ></Form.Control>
        </Form.Group>

        <Form.Group controlId="confirmPassword" className="mt-2">
          <Form.Label>Confirm Password</Form.Label>
          <Form.Control
            type="password"
            placeholder="Confirm password"
            autoComplete="new-password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          ></Form.Control>
        </Form.Group>

        <Button type="submit" variant="primary" className="mt-3">
          Register
        </Button>
      </Form>

      <Row className="py-3">
        <Col>
          Have an Account? <Link href="/account/login">Login</Link>
        </Col>
      </Row>
    </FormContainer>
  );
};

export default RegisterScreen;
