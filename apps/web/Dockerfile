FROM node:24.20.0-bookworm-slim AS build

WORKDIR /workspace/apps/web

COPY apps/web/package.json apps/web/package-lock.json ./
RUN npm ci

COPY packages/brand /workspace/packages/brand
COPY packages/design-tokens /workspace/packages/design-tokens
COPY apps/web/angular.json apps/web/eslint.config.js apps/web/tsconfig*.json ./
COPY apps/web/scripts scripts
COPY apps/web/src src

RUN npm run build

FROM nginx:1.30.4-alpine-slim AS runtime

COPY apps/web/docker/nginx.conf /etc/nginx/nginx.conf
COPY --from=build /workspace/apps/web/dist/nook-forge-web/browser /usr/share/nginx/html

EXPOSE 8080

CMD ["nginx", "-g", "daemon off;"]
