# 첫 번째 단계를 위한 OpenJDK 베이스 이미지
FROM eclipse-temurin:17 AS builder
WORKDIR workspace
# 프로젝트 내에서 애플리케이션 JAR 파일의 위치를 지정하는 빌드 인수
ARG JAR_FILE=build/libs/*.jar
# 애플리케이션 JAR 파일을 로컬 머신에서 이미지의 'workspace' 폴더로 복사
COPY ${JAR_FILE} catalog-service.jar
# 계층 JAR 모드를 적용해 아카이브에서 계층을 추출한다.
RUN java -Djarmode=layertools -jar catalog-service.jar extract

FROM eclipse-temurin:17
# 'spring'이라는 이름의 유저를 만든다.
RUN useradd spring
# 'spring'을 현재 유저로 설정한다.
USER spring
WORKDIR workspace
# 첫 번째 단계에서 추출한 JAR 계층을 두 번째 단계로 복사한다.
COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/spring-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./
# 스프링 부트 런처를 사용해 우버 JAR 대신 계층으로 애플리케이션을 시작한다.
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]



