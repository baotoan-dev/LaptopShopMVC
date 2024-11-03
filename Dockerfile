# Sử dụng image OpenJDK làm base
FROM openjdk:17-jdk-slim

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép tệp pom.xml vào thư mục làm việc
COPY pom.xml ./

# Sao chép mã nguồn
COPY src ./src

# Cài đặt Maven và biên dịch ứng dụng
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests

# Sao chép tệp JAR đã biên dịch vào thư mục gốc
COPY target/*.jar app.jar

# Thiết lập biến môi trường
ENV JAVA_OPTS=""

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
