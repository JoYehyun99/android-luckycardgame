# android-luckycardgame
#### Card 객체 수정
- `bottom: Boolean` 추가 : 바닥 카드인지 확인을 위해 추가


#### Game Rule
- 참가자들은 차례대로 3장의 카드를 뒤집는다.
  1. 자신의 카드 리스트 혹은 바닥 카드 중 총 3장을 뽑을 수 있다.
  2. 자신의 차례에서 다른 참가자들의 카드를 뒤집을 수 없다.
- 모든 참가자들의 순서가 끝나면 하나의 round가 종료되고, 게임 종료 여부를 판단 한다.
- 게임 종료 조건 )
  1. 사용자가 동일한 숫자의 카드 3장을 뽑은 경우가 있다면, 해당 숫자들의 합 혹은 차가 7인 경우. 최소 승리자 우선 순위로 승자 판별 (7 카드를 가진 사람(1명), 2명,3명 ...)
  2. 모든 참가자들이 자신의 카드를 다 뒤집었을 경우
- 종료 시 `winnerList`에 대상자들의 정보를 담아서 결과 화면으로 넘긴다.
- 종료 조건을 만족하지 않는다면 다시 첫 유저 턴으로 넘어간다. 
  1. 동일한 숫자 카드 3장을 가지고 있는 유저가 있음에도 합/차가 7이 되지 않는 경우 : 동일한 숫자 카드 3장을 뒤집었던 참가자의 카드 중 일부가 바닥 카드라면, 해당 바닥 카드는 다음 턴에도 참가자의 소유 카드로 인정된다.
  2. 동일한 숫자 카드 3장을 가진 유저가 한 명도 없을 경우 : 바닥 카드 모두 리셋 되어 뒷면을 보이게 뒤집어진다. 참가자들은 바닥카드의 위치를 외웠다가 원하는 카드를 가질 수 있도록 해야 한다.


#### 결과 화면

<img width="334" alt="1" src="https://github.com/JoYehyun99/android-luckycardgame/assets/81362348/2f527d24-e7a4-4003-9f19-435e8c71d432">
<img width="343" alt="3" src="https://github.com/JoYehyun99/android-luckycardgame/assets/81362348/819ccce8-0958-4a84-a212-6d70e230c8bf">
<img width="342" alt="4" src="https://github.com/JoYehyun99/android-luckycardgame/assets/81362348/0230fea7-5ed1-4f70-afe9-e36b1f0791ae">
<img width="342" alt="5" src="https://github.com/JoYehyun99/android-luckycardgame/assets/81362348/8d4001b9-2607-4b24-9ef5-8bb675f27ad5">
