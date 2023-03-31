#!/bin/bash

echo -e "\n\n"
echo "🏃🏃🏃 GONGSAMO PLATFORM 배포를 시작합니다. 🏃🏃🏃"

echo ">>> build 파일명을 확인합니다."
BUILD_PATH=/home/ubuntu/gongsamo/build/gongsamo-0.0.1.jar
JAR_NAME=$(basename $BUILD_PATH)
echo "> build 파일명: $JAR_NAME"

echo ">>> 현재 실행 중인 애플리케이션의 pid 를 확인합니다."
CURRENT_PID=$(pgrep -f $JAR_NAME)
echo "> pid : $CURRENT_PID"

if [ -z $CURRENT_PID ]
then
  echo "> 현재 실행 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> 현재 실행 중인 애플리케이션이 있습니다. 어플리케이션을 종료합니다."
  echo "> kill -15 $CURRENT_PID and sleep 5 sec"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo ">>> 기존 버전의 $JAR_NAME 을/를 삭제합니다."
DEPLOY_PATH=/home/ubuntu/gongsamo/
APPLICATION_JAR=$DEPLOY_PATH$JAR_NAME
rm $APPLICATION_JAR
echo "> 기존 버전의 $JAR_NAME 이/가 삭제되었습니다."

echo ">>> 신규 build 파일을 복사합니다."
cp $BUILD_PATH $DEPLOY_PATH
echo "> build 파일 복사 완료"


echo ">>> $APPLICATION_JAR 를 배포합니다."
CURRENT_SERVER_ADDRESS=$(hostname -I)
echo "> CURRENT_SERVER_ADDRESS : $CURRENT_SERVER_ADDRESS"

if [ ${CURRENT_SERVER_ADDRESS} = "192.168.1.55" ]
then
  echo "> 개발서버이므로 개발 환경으로 배포합니다."
  java -jar -Dspring.profiles.active=dev $APPLICATION_JAR > /dev/null 2> /dev/null < /dev/null &
else
  echo "> 운영서버이므로 운영 환경으로 배포합니다."
  java -jar -Dspring.profiles.active=prod $APPLICATION_JAR > /dev/null 2> /dev/null < /dev/null &
fi

echo "🏃🏃🏃 GONGSAMO PLATFORM 배포 완료 🏃🏃🏃"
