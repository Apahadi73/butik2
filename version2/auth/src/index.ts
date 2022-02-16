import mongoose from 'mongoose';
import dotenv from "dotenv"
import { app } from './app';
import chalk from 'chalk';

const start = async () => {
  dotenv.config()
  console.log(chalk.green('Checking workflow for merge...'));

  

  if (!process.env["JWT_KEY"]) {
    throw new Error('JWT_KEY must be defined');
  }
  if (!process.env["MONGO_URI"]) {
    throw new Error('MONGO_URI must be defined');
  }

  try {
    await mongoose.connect(process.env["MONGO_URI"], {
      useNewUrlParser: true,
      useUnifiedTopology: true,
      useCreateIndex: true,
    });
    console.log(chalk.green('Connected to MongoDb'));
  } catch (err) {
    console.error(err);
  }

  app.listen(3000, () => {
    console.log(chalk.green('Listening on port 3000!!!!!!!!'));
  });
};

start();
