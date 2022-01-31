import React, { useState, useEffect, useContext } from "react";
import { Form, Button, Row, Col } from "react-bootstrap";
import Link from "next/link";
import { useRouter } from "next/router";

import Message from "../../components/Message";
import Loader from "../../components/Loader";
import FormContainer from "../../components/FormContainer";
import { AuthenticationContext } from "../../state/repo/Authentication.context";

const LoginScreen = ({ location, history }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const router = useRouter();

  const { currentUser, login, isLoading, error } = useContext(
    AuthenticationContext
  );

  useEffect(() => {
    if (currentUser) {
      router.push("/");
    }
  }, [currentUser]);

  const loginHandler = async (e) => {
    e.preventDefault();
    if (!error) {
      await login(email, password);
      if (!error && !isLoading && currentUser) {
        router.push("/");
      }
    } else {
      console.log(error);
    }
  };

  return (
    <FormContainer>
      <h1 className="row justify-content-center">Login In</h1>
      {error && <Message variant="danger">{error}</Message>}
      {isLoading && <Loader />}
      <Form onSubmit={loginHandler}>
        <Form.Group controlId="email">
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
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          ></Form.Control>
        </Form.Group>

        <Button
          type="submit"
          variant="primary"
          className="mt-3"
          onClick={loginHandler}
        >
          Sign In
        </Button>
      </Form>

      <Row className="py-3">
        <Col>
          New Customer?
          <Link href={"/account/register"}> Register</Link>
        </Col>
      </Row>
    </FormContainer>
  );
};

export default LoginScreen;
