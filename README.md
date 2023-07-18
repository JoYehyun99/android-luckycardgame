# android-luckycardgame

## Task 1 - 1

#### 게임 보드 화면 구성
- ConstraintLayout, CardView 활용

## Task 1 - 2

#### Card Class 구현 ( LuckyCard.kt )
- enum class 사용하여 유니코드 저장
- sealed class 인 Card를 통해 3개의 자식 클래스 생성 ( Card )
- 결과 콘솔창

  <img width="597" alt="screenshot2" src="https://github.com/JoYehyun99/android-luckycardgame/assets/81362348/97292c5f-bf55-49da-807c-7f0651d5651a">


##### 수정 사항
- enum class를 없애고, Card class 내의 각각 동물들 data class 안에 unicode 정보를 String 형태로 저장 => _조금 더 심플한 출력 코드를 위함_


## Task 1 - 3


#### 참가자 클래스 ( Participant )
- 참가자 별 소유하는 카드 리스트 저장

#### RecyclerView를 사용하여 카드 리스트 UI 구현

- **카드 앞면 / 뒷면 구분**

Adapter에 뒷면을 표시할 것인가에 대한 Boolean 값(isBackSide)을 전달하여 자신의 카드 외에는 카드 뒷면이 보일 수 있도록 설정

- **카드 간격 설정**

RecyclerView의 ItemDecoration 활용하여 카드 간의 간격 설정.

참가자 카드와 바닥 카드 리스트 UI가 다르기 때문에 두 개의 Decoration class 생성하여 사용. (MarginSetDecoration / MarginSetBottomDecoration)

Decoration class 내에서 카드 리스트 개수에 따라 간격을 다르게 하도록 설정


#### 구현 화면
<img width="322" alt="1" src="https://github.com/JoYehyun99/android-luckycardgame/assets/81362348/c0a87ff9-ec58-4cb0-be54-baa36cf03152">
<img width="308" alt="2" src="https://github.com/JoYehyun99/android-luckycardgame/assets/81362348/645fd117-bd45-4f2b-8b77-45bb6e6c308d">
<img width="312" alt="3" src="https://github.com/JoYehyun99/android-luckycardgame/assets/81362348/71e4999c-3e77-4257-8ecd-f819c2d67e44">


## Task 1 - 4


#### LuckyGame 객체 구현
- 총 카드 리스트와 참여자들의 정보가 담긴 LuckyGame 객체 구현


#### LuckyCardTest - JUnit 단위 테스트
- TEST 1 ) 동물 별로 12장씩 잘 생성되었는가 (초기 세팅 테스트)
- TEST 2 ) 참여 인원이 3명일 경우, 전체 카드 리스트에 12가 포함되어 있지 않는가
- TEST 3 ) 참여 인원을 설정시, 인원별 (3,4,5명) 올바른 카드 개수가 user들에게 할당되었는가
- TEST 4 ) 참여 인원 설정에 맞춰서 바닥 카드 리스트에도 올바른 카드 개수가 할당되었는가
- TEST 5 ) 특정 참가자가 카드를 오름차순 정렬로 설정했을 때, 해당 참가자의 카드 리스트가 올바르게 정렬되었는가
- TEST 6 ) 바닥 카드 리스트가 오름차순으로 잘 정렬되는가
- TEST 7 ) 참가자들 중에서 같은 숫자 카드 3장을 가진 사람이 존재하는가
- TEST 8 ) 참가자 두 명의 카드 (최소, 최대 숫자)와 바닥 카드에서 랜덤으로 선택된 카드가 모두 같은 숫자 카드가 존재하는가


## Task 1 - 5


#### Card 객체 수정
- `flipped: Boolean` 추가 : 카드의 뒤집힘 상태 체크


#### Game Rule
- 참여자들은 차례대로 3장의 카드를 뒤집는다.
- 모든 참여자들의 순서가 끝나면 하나의 round가 종료되고, 게임 종료 여부를 판단 한다.
- 게임 종료 조건 )
  1. 사용자가 동일한 숫자의 카드 3장을 뽑은 경우가 있다면, 해당 숫자들의 합 혹은 차가 7인 경우. 최소 승리자 우선 순위로 승자 판별 (7 카드를 가진 사람(1명), 2명,3명 ...)
  2. 모든 참여자들이 자신의 카드를 다 뒤집었을 경우
- 종료 시 `winnerList`에 대상자들의 정보를 담아서 결과 화면으로 넘긴다.



#### LuckyCardTest - JUnit 단위 테스트 추가 구현
- TEST 9 ) 사용자가 처음에 양끝 카드 중 하나를 뒤집으려고 했을 때 flip이 잘 작동되는가
- TEST 10 ) 사용자가 처음부터 가운데에 있는 카드들 중 하나를 뒤집으려고 했을 때 flip이 실패하는가
- TEST 11 ) 사용자가 카드를 양끝 카드에서부터 연속적으로 카드를 뒤집었을 때 flip이 잘 작동되는가
- TEST 12 ) 게임에 참여한 모든 참가자들의 카드가 다 뒤집어져서 더 이상 뒤집을 카드가 없을 경우를 잘 파악하는가
- TEST 13 ) 작성한 combination 함수를 통해 숫자 조합들이 잘 반환되는가
- TEST 14 ) 조합들 중에 합 또는 차가 7이 되는 경우가 있다면 잘 찾아낼 수 있는가
- TEST 15 ) 게임이 종료되는 조건을 잘 파악할 수 있는가


## Task 1 - 6


#### Card 객체 수정
- `bottom: Boolean` 추가 : 바닥 카드인지 확인을 위해 추가


#### Game Rule 수정
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
