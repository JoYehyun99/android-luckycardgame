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


