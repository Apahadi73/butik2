# FROM python:3.8-bullseye

# WORKDIR /app

# RUN pip install pipenv

# COPY ./Pipfile.lock ./Pipfile.lock
# COPY ./Pipfile ./Pipfile

# RUN pipenv install

# COPY . .

# CMD ["pipenv", "run", "python3", "./src/main.py"]

# FROM tiangolo/uvicorn-gunicorn-fastapi:python3.7
# COPY requirements.txt /tmp
# RUN pip install -r /tmp/requirements.txt
# WORKDIR /app
# COPY ./app .
# EXPOSE 5000
# CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "5000"]


# Pull base image
FROM python:3.9
# Set environment varibles
ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1
WORKDIR /code/
# Install dependencies
RUN pip install pipenv
COPY Pipfile Pipfile.lock /code/
RUN pipenv install --system --dev
COPY . /code/
EXPOSE 5000
CMD ["python", "./src/main.py"]