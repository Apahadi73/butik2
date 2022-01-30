import React, { useState, useEffect } from "react";
import { Form, Button, Row, Col } from "react-bootstrap";
import Link from "next/link";
import { useRouter } from "next/router";

import Message from "../../components/Message";
import Loader from "../../components/Loader";
import FormContainer from "../../components/FormContainer";

const LoginScreen = ({ location, history }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const loading = false;
  const router = useRouter();

  const submitHandler = (e) => {
    e.preventDefault();
    validateForm(email, password);
    if (!error) {
      console.table({ email, password });
    }
  };

  const validateForm = (email, password) => {
    if (!email && !password) {
      setError("Invalid email and password");
    } else if (!email) {
      setError("Invalid email");
    } else if (!password) {
      setError("Invalid password");
    } else {
      setError("");
    }
  };

  return (
    <FormContainer>
      <h1 className="row justify-content-center">Login In</h1>
      {error && <Message variant="danger">{error}</Message>}
      {loading && <Loader />}
      <Form onSubmit={submitHandler}>
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

        <Button type="submit" variant="primary" className="mt-3">
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
