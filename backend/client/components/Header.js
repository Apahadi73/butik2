import React from "react";
import { Navbar, Nav, Container, NavDropdown } from "react-bootstrap";
import SearchBox from "./SearchBox";
import Link from "next/link";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faShoppingCart, faUser } from "@fortawesome/free-solid-svg-icons";

const Header = () => {
  const logoutHandler = () => {
    // dispatch(logout());
  };

  let userInfo = null;

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
              {userInfo ? (
                <NavDropdown title={userInfo.name} id="username">
                  <Link href="/profile">
                    <NavDropdown.Item>Profile</NavDropdown.Item>
                  </Link>
                  <NavDropdown.Item onClick={logoutHandler}>
                    Logout
                  </NavDropdown.Item>
                  {userInfo && userInfo.isAdmin && (
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
                  <Nav.Link onClick={() => console.log("clicked")}>
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
