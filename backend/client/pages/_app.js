import "../styles/globals.css";
import "../styles/bootstrap.min.css";
import buildClient from "../api/build-client";
import Header from "../components/Header";
import { QueryClient, QueryClientProvider, useQuery } from "react-query";
import "@fortawesome/fontawesome-svg-core/styles.css"; // import Font Awesome CSS
import { config } from "@fortawesome/fontawesome-svg-core";
import AuthenticationContextProvider from "../state/repo/Authentication.context";

config.autoAddCss = false; // Tell Font Awesome to skip adding the CSS automatically since it's being imported above
const queryClient = new QueryClient();
function MyApp({ Component, pageProps }) {
  return (
    <>
      <QueryClientProvider client={queryClient}>
        <AuthenticationContextProvider>
          <div className="container">
            <Header />
            <Component {...pageProps} />
          </div>
        </AuthenticationContextProvider>
      </QueryClientProvider>
    </>
  );
}

MyApp.getInitialProps = async (appContext) => {
  const client = buildClient(appContext.ctx);

  let pageProps = {};
  if (appContext.Component.getInitialProps) {
    pageProps = await appContext.Component.getInitialProps(
      appContext.ctx,
      client
    );
  }
  return { pageProps };
};

export default MyApp;
