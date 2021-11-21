FROM python:3.8-bullseye

WORKDIR /app

RUN pip install pipenv

COPY ./Pipfile.lock ./Pipfile.lock
COPY ./Pipfile ./Pipfile

RUN pipenv install

COPY . .

CMD ["pipenv", "run", "python3", "./src/main.py"]