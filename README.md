# 🃏 블랙잭 미션

### 🎰 사용자와 블랙잭 프로그램의 요청, 응답

1. 게임에 참여할 사람 이름 입력 → 이름을 쉼표로 분리 / `Player`로 저장.
2. `Dealer`와 `Player`모두 2장의 카드를 지급받는다 → 딜러는 처음 뽑은 카드를, 플레이어는 모든 카드를 공개한다.
3. `Player`는 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
    1. 사용자는 y 또는 n을 입력한다
        1. y일 경우 → 사용자가 카드를 뽑아간다.
        2. n일 경우 → 해당 사용자의 턴이 끝난다.
    2. 사용자의 카드를 출력한다.
4. `Dealer` 의 카드를 뽑는다.
    1. 2장의 합계가 16이하 → 카드 1장을 추가로 받는다.
    2. 17점 이상→ 딜러의 턴이 끝난다.
5. 각 플레이어의 카드 합을 출력한다
6. 최종 승패를 출력한다

### ✨입출력 예외 사항

- 사람 이름을 입력할 때, 빈값을 입력하면 안된다. (한명 이상은 무조건 참여)
- 카드 한장 더 받는 것에 대한 입력을 할 때, `y/n` 이외에는 입력할 수 없다.

### 🃏 블랙잭 구현

- [x]  사람 이름을 입력한다.
    - [x]  [예외처리] 이름이 공백인 경우
    - [x]  [예외처리] 빈 값을 입력하는 경우
- [x]  사람 이름을 쉼표로 분리한다.
- [x]  사용자 객체를 생성한다.
- [x] 사용자는 배팅 금액을 입력한다.
  - [x] [예외처리] 베팅 금액은 음수를 입력할 수 없다
- [x]  각 사용자 객체에 카드 2장을 추가한다.
- [x]  사용자의 카드와 딜러의 첫번째 카드를 출력한다.
- [x] 사용자의 2개의 카드가 블랙잭인 경우 딜러에게 배팅금액의 1.5배의 금액을 받는다
- [x] 사용자랑 딜러가 동시에 블랙잭인 경우 딜러는 배팅한 금액을 돌려받는다.
- [x]  각 사용자별로 카드를 더 받을지 여부를 입력한다.
    - [x]  [예외처리] `y/n` 이외의 값을 입력한 경우
    - [x]  y를 입력한 경우 - 카드 1장을 더 뽑는다.
    - [x]  n을 입력한 경우 - 턴을 끝낸다.
    - [x]  새로운 카드를 뽑을 때마다 자신의 카드를 출력한다.
- [x]  딜러의 카드가 16이하일 경우 카드를 한장 더 뽑는다. (아닐경우, 넘어간다)
- [x]  전체 카드의 결과를 출력한다.
   - [x]  카드의 끗수가 A인 경우, 1 또는 11로 계산하여 가장 유리한 값으로 선정한다.
- [x]  최종 승패를 출력한다.

### 도메인별 역할
**Card**
- [x] 카드가 Ace 카드인지 여부를 나타내기
- [x] 프로그램이 시작될 때, 캐시에 카드 저장하기
- [x] 캐시에 저장된 카드 List 반환하기
- [x] 전달받은 symbol과 denomination와 같은 카드 캐시에서 반환해주기

**Cards**
- [x] 리스트에 있는 카드들을 점수 계산하기
- [x] 리스트에 Ace가 있는 경우에, 가장 이득이 되는 점수로 계산하기
- [x] 카드 리스트에서 인덱스로 카드 반환하기
- [x] 카드 리스트가 bust인지 여부 나타내기
- [x] 카드 리스트가 blackjack인지 여부 나타내기

**Deck**
- [x] 카드 List 받아 섞기
- [x] 덱에서 카드 한장을 꺼내기 
- [x] 덱에서 카드 여러장을 꺼내기 

**Participant**
- [x] 첫번째 턴에 카드 2개 전달받기
- [x] 자신이 가지고 있는 카드의 점수를 계산하기
- [x] 참가자의 카드 리스트가 bust인지 여부 나타내기
- [x] 참가자의 카드 리스트가 blackjack인지 여부 나타내기

**Player**
- [x] 딜러와 점수로 비교하기, 결과 반환하기 
- [x] 카드를 hit할 수 있는지 여부 나타내기
- [x] 카드 hit 하기
- [x] 결과에 따라 수익을 계산하기

**Result**
- [x] 플레이어 점수와 딜러 점수를 받아 결과 반환하기 (버스트, 블랙잭인 경우 제외)

**Money**
- [x] 돈이 음수 또는 0인 경우 예외를 발생시킨다.
- [x] 블랙잭 배당률을 받아 총 수익을 계산한다

**dealer**
- [x] 첫번째 카드 반환하기
- [x] 카드를 hit할 수 있는지 여부 나타내기
- [x] 카드 hit 하기
- [x] 플레이어 수익을 종합하여 딜러의 수익을 계산하기

**Players**
- [x] 모든 플레이어들 첫 턴 까드 뽑기
- [x] 모든 플레이어에 대해 딜러와 점수로 비교하기

### 블랙잭 게임 용어
- 블랙잭 : 처음 두장의 카드 합이 21인 경우
- Bust : 카드 합이 21을 초과하는 경우
- Stay : 플레이어가 추가 카드를 원하지 않는경우, 딜러도 17이상이 넘은 경우
- hit : 처음 두장의 카드외에 딜러에게 추가 카드를 요청하는 경우

### ✔ 추가된 요구 사항

- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.
