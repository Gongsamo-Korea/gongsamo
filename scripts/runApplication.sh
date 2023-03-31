#!/bin/bash

echo "🏃🏃🏃 GONGSAMO PLATFORM 어플리케이션을 실행합니다. 🏃🏃🏃"

echo ">>> 현재 실행중인 애플리케이션 pid를 확인합니다."
CURRENT_PID=$(pgrep java)
echo "> CURRENT_PID : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]
 then
   echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> 현재 구동중인 어플리케이션을 종료합니다."
    echo "> kill -15 $CURRENT_PID and sleep 5 sec"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo ">>> 새로운 버전의 어플리케이션을 실행합니다."
echo "> 실행 환경을 선택해주세요. (dev / prod)"
read env

if [[ "$env" = "prod" ]]; then
  echo "> 운영 환경에서 어플리케이션을 시작합니다."
  java -jar -Dspring.profiles.active=prod gongsamo-0.0.1.jar &
elif [[ "$env" = "dev" ]]; then
  echo -e "> 개발 환경에서 어플리케이션을 시작합니다."
  java -jar -Dspring.profiles.active=dev gongsamo-0.0.1.jar &
else
  echo -e "> 존재하지 않는 환경입니다. 프로세스를 종료합니다. "
  exit 0
fi

echo "🏃🏃🏃 GONGSAMO PLATFORM 어플리케이션이 성공적으로 시작되었습니다. 🏃🏃🏃"

