import "../styles/globals.css";
import "../styles/bootstrap.min.css";
import buildClient from "../api/build-client";

function MyApp({ Component, pageProps }) {
  return (
    <div className="container">
      <Component {...pageProps} />
    </div>
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
