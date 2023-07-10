# android-luckycardgame
Card Class 구현 ( LuckyCard.kt )
- enum class 사용하여 유니코드 저장
- sealed class 인 Card를 통해 3개의 자식 클래스 생성 ( Card )
- 결과 콘솔창
  
  <img width="597" alt="screenshot2" src="https://github.com/JoYehyun99/android-luckycardgame/assets/81362348/97292c5f-bf55-49da-807c-7f0651d5651a">


---

##### 수정 사항
- enum class를 없애고, Card class 내의 각각 동물들 data class 안에 unicode 정보를 String 형태로 저장 => _조금 더 심플한 출력 코드를 위함_