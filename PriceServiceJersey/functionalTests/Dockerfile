FROM node:9-alpine

WORKDIR /test

ADD package.json ./package.json

RUN npm install -g mocha
RUN npm install

ENTRYPOINT ["mocha"]