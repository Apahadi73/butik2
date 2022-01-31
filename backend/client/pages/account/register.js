import React, { useState, useEffect, useContext } from "react";
import { Form, Button, Row, Col } from "react-bootstrap";
import Link from "next/link";
import { useRouter } from "next/router";

import Message from "../../components/Message";
import Loader from "../../components/Loader";
import FormContainer from "../../components/FormContainer";
import { AuthenticationContext } from "../../state/repo/Authentication.context";

const RegisterScreen = ({ location, history }) => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [message, setMessage] = useState(null);

  const { currentUser, register, isLoading, error } = useContext(
    AuthenticationContext
  );

  const router = useRouter();

  useEffect(() => {
    if (currentUser) {
      router.push("/");
    }
  }, [currentUser]);

  const registerHandler = async (e) => {
    e.preventDefault();
    if (!error) {
      await register(email, name, password, confirmPassword);
    }
  };

  return (
    <FormContainer>
      <h1 className="row justify-content-center">Sign Up</h1>
      {message && <Message variant="danger">{message}</Message>}
      {error && <Message variant="danger">{error}</Message>}
      {isLoading && <Loader />}
      <Form onSubmit={registerHandler}>
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

        <Button
          type="submit"
          variant="primary"
          className="mt-3"
          onClick={registerHandler}
        >
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
