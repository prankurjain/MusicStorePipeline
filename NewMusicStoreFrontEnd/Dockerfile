FROM node:alpine as builder
# RUN mkdir -p /app
WORKDIR '/app'

COPY . .

RUN npm install
RUN npm run build --prod

# FROM nginx:alpine
# COPY src/nginx/etc\conf.d\default.conf /etc/nginx/conf/default.conf
# COPY --from=builder app/dist/MusicStore usr/share/nginx/html

FROM nginx
EXPOSE 4200
COPY default.conf /etc/nginx/nginx.conf
COPY dist/MusicStore /usr/share/nginx/html