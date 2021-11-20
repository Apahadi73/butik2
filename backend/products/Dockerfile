
FROM python:3.9


WORKDIR /app


COPY . . 

RUN python3 -m pip install --upgrade pip 
RUN pip install pipenv
RUN pipenv install Pipfile


COPY . /app


CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "80"]
