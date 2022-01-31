import React, { useState, useEffect, useContext } from "react";
import { Navbar, Nav, Container, NavDropdown } from "react-bootstrap";
import SearchBox from "./SearchBox";
import Link from "next/link";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faShoppingCart, faUser } from "@fortawesome/free-solid-svg-icons";
import useLocalStorage from "../state/hooks/useLocalStorage";
import { AuthenticationContext } from "../state/repo/Authentication.context";

const Header = () => {
  const { currentUser, logout } = useContext(AuthenticationContext);

  const logoutHandler = () => {
    logout();
  };

  useEffect(() => {
    console.table(currentUser);
  }, [currentUser]);

  return (
    <header>
      <Navbar bg="dark" variant="dark" expand="lg" collapseOnSelect>
        <Container>
          <Link href="/">
            <Navbar.Brand>Butik</Navbar.Brand>
          </Link>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="ms-auto">
              <div className="mx-3">
                {/* <Route
                  render={({ history }) => <SearchBox history={history} />}
                /> */}
              </div>
              <Link href="/cart">
                <Nav.Link>
                  <FontAwesomeIcon
                    icon={faShoppingCart}
                    style={{
                      fontSize: "0.8rem",
                      marginRight: "0.5rem",
                      color: "white",
                    }}
                  />
                  Cart
                </Nav.Link>
              </Link>
              {currentUser ? (
                <NavDropdown title={currentUser.name} id="username">
                  <Link href="/profile">
                    <NavDropdown.Item>Profile</NavDropdown.Item>
                  </Link>
                  <NavDropdown.Item onClick={logoutHandler}>
                    Logout
                  </NavDropdown.Item>
                  {currentUser && currentUser.isAdmin && (
                    <React.Fragment>
                      <Link href="/admin/userlist">
                        <NavDropdown.Item>Users</NavDropdown.Item>
                      </Link>
                      <Link href="/admin/productlist">
                        <NavDropdown.Item>Products</NavDropdown.Item>
                      </Link>
                      <Link href="/admin/orderlist">
                        <NavDropdown.Item>Orders</NavDropdown.Item>
                      </Link>
                    </React.Fragment>
                  )}
                </NavDropdown>
              ) : (
                <Link as="/account/login" href="/en/checkout" passHref>
                  <Nav.Link>
                    <FontAwesomeIcon
                      icon={faUser}
                      style={{
                        fontSize: "0.8rem",
                        marginRight: "0.5rem",
                        color: "white",
                      }}
                    />
                    Sign In
                  </Nav.Link>
                </Link>
              )}
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </header>
  );
};

export default Header;
