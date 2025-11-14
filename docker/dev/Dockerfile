FROM gradle:8.5-jdk21

WORKDIR /app

RUN apt-get update && apt-get install -y curl vim && rm -rf /var/lib/apt/lists/*

COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle

RUN gradle dependencies --no-daemon || true

EXPOSE 8001

CMD ["gradle", "bootRun", "--no-daemon", "--args='--spring.devtools.restart.enabled=true'", "-Dorg.gradle.jvmargs='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8001'"]