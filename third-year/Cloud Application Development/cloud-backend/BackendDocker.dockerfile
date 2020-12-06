FROM hayd/ubuntu-deno:latest
WORKDIR /app
COPY . .
CMD ["run", "--allow-write", "--allow-read", "--allow-net", "--allow-env", "--unstable", "--allow-plugin", "--reload", "app.ts"]



