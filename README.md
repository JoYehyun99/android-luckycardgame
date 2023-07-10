# android-luckycardgame
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


