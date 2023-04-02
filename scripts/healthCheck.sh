#!/bin/bash

echo -e "\n\n"
echo "🏃🏃🏃 GONGSAMO PLATFORM HEALTH CHECK 를 시작합니다. 🏃🏃🏃"
echo ">>> ps -ef | grep java"

for RETRY_COUNT in {1..15}
do
  RESPONSE=$(ps -ef | grep java)
  UP_COUNT=$(echo $RESPONSE | grep 'gongsamo-0.0.1.jar' | wc -l)

  if [ $UP_COUNT -ge 1 ]
  then # $up_count >= 1 ("UP" 문자열이 있는지 검증)
      echo "> Health Check 에 성공했습니다. 😆"
      break
  else
      echo "> Health Check 의 응답을 알 수 없거나 혹은 status가 UP이 아닙니다."
      echo "> Health Check: ${RESPONSE}"
  fi

  if [ $RETRY_COUNT -eq 10 ]
  then
    echo "> Health Check 에 실패했습니다. 😭 "
    exit 1
  fi

  echo "> Health Check 연결에 실패했습니다. 다시 시도합니다...🤔"
  sleep 10
done
exit 0
