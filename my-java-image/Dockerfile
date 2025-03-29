FROM ubuntu:22.04
# 새 이미지에 대한 베이스 이미지로 우분투 22.04를 지정

RUN apt-get update && apt-get install -y default-jre
# 익숙한 bash 명령을 사용해 JRE를 설치

ENTRYPOINT ["java", "--version"]
# 실행 컨테이너의 엔트리 포인트를 정의한다.