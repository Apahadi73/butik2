First stage
FROM node:current-alpine as base
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . . 


Second Stage - build stage
FROM base AS build
ENV NODE_ENV=production
WORKDIR /build
COPY --from=base /app ./
RUN npm run build


# Final Stage 
FROM node:current-alpine as production
ENV NODE_ENV=production
WORKDIR /app
COPY --from=build /build/package*.json ./
COPY --from=build /build/.next  ./.next
COPY --from=build /build/public  ./public
RUN npm install dev

EXPOSE 5000

CMD [ "npm","run","start" ]





