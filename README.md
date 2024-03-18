# ♠️ 블랙잭 미션 저장소 ♠️

### 카드

- 카드는 모양을 갖는다.
    - 모양은 하트, 스페이드, 다이아몬드, 클로버가 있다.
- 카드는 문자를 갖는다.
    - 문자는 숫자 또는 영어 글자이다.
    - 숫자는 2~10까지이다.
    - 영어 글자는 Ace(A), King(K), Queen(Q), Jack(J) 가 있다.
- 카드는 숫자로 계산된다.
    - 숫자가 적힌 카드는 그 숫자로 계산한다.
    - A는 1 또는 11로 계산할 수 있다.
    - K, Q, J은 각각 10으로 계산한다.

### 플레이어

- 플레이어는 카드를 갖는다.
    - 게임이 시작하면 2장의 카드를 지급받는다.
    - 게임 시작 시 카드 2장을 오픈한다.
    - 게임 시작 이후 카드를 받을지 말지 선택할 수 있다.
    - 카드가 21이 넘지 않을 때까지 카드를 받을 수 있다.
    - 21이 넘어가면 더 이상 카드를 받지 못 한다.
    - Ace카드는 버스트 되지 않는 선에서만 11로 계산한다.

### 딜러

- 딜러는 카드를 갖는다.
    - 게임이 시작하면 2장의 카드를 지급받는다.
    - 게임 시작 시 카드 1장만 오픈한다.
    - Ace카드는 버스트 되지 않는 선에서만 11로 계산한다.
- 카드를 받을지 말지 선택할 수 없다.
    - 카드의 합계가 16 이하이면 반드시 1장의 카드를 받아야 한다.
    - 카드의 합계가 17 이상이면 추가로 받을 수 없다.

### 게임

- 카드 분배는 랜덤으로 진행한다.
- 모든 플레이어에게 카드가 분배되면, 딜러가 카드를 분배받을 수 있다.
- 카드의 합계가 21을 넘지 않으면서 21에 가장 가까운 사람이 승리한다.
- 딜러가 버스트 상태라면, 플레이어의 패에 상관없이 플레이어가 승리한다.
- 딜러와 플레이어가 둘 다 버스트 되지 않고, 점수가 동일하면 push(무승부)로 간주한다.
- 승리할 경우 베팅 금액만큼 수익을 얻고, 패배할 경우 베팅 금액을 잃는다.
- 만약 블랙잭으로 승리할 경우 배팅금액의 1.5배의 수익을 얻는다.

### 입력

- 참가자의 이름 입력 받는다.
    - 이름은 ,로 구분한다.
    - 이름은 1~5글자 이다.
    - 이름은 "딜러"가 될 수 없다.
    - 참가자의 수는 최대 10명까지 허용한다.

- 배팅 금액을 입력 받는다.
    - 배팅 금액은 정수만 허용한다.
    - 베팅 최소 금액은 1_000원이다.
    - 베팅 최대 금액은 1_000_000원이다.


- hit, stand 여부를 입력 받는다.
    - y,n 입력만 허용하도록 한다.

### 출력

- 첫 손 패의 출력은 딜러는 한 장만 보여준다.
- 참가자가 hit하여, 손 패가 변경되면 손 패를 출력한다.
- 참가자가 첫 손 패를 유지할 경우, 최초 n 입력에 대해서 손 패를 출력한다.
- 딜러는 hit을 할 때 마다, 한 장을 받았는 메세지를 출력한다.
- 최종 수익을 출력한다.