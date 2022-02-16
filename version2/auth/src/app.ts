import express from 'express';
import 'express-async-errors';
import { json } from 'body-parser';
import cookieSession from 'cookie-session';
import { errorHandler, NotFoundError } from '@cygnetops/common';
import cors from "cors"
import chalk from 'chalk';

import { currentUserRouter } from './routes/current-user';
import { signinRouter } from './routes/signin';
import { signoutRouter } from './routes/signout';
import { signupRouter } from './routes/signup';

const app = express();
app.set('trust proxy', true);
app.use(json());
app.use(
  cookieSession({
    signed: false,
    secure: false,
  })
);

// Add a list of allowed origins.
// If you have more origins you would like to add, you can add them to the array below.
const allowedOrigins = ['http://localhost:3001','http://localhost:3000',];
const options: cors.CorsOptions = {
  origin: allowedOrigins
};
app.use(cors(options))
console.log(chalk.green("CORS configured for authentication service."))



app.get('/api/authentication', (req, res) => {
  res.send("Welcome to the authentication service.")
})
app.use(currentUserRouter);
app.use(signinRouter);
app.use(signoutRouter);
app.use(signupRouter);

app.all('*', async (req, res) => {
  throw new NotFoundError();
});

app.use(errorHandler);

export { app };
