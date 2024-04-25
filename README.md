# java-chess

체스 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

# ToDo
# Piece
- [x] 이동 가능한 위치 모두 반환 인터페이스 선언
  - List < Position >  getAllNextPosition()
- [ ] void move()
# 폰(pawn)
- [ ]  첫 이동일 땐 1 or 2칸 전진
- [ ]  1칸 전진
- [ ]  대각선 전면으로 상대 기물 공격
- [ ]  승급 킹을 제외한 어떤 기물로도 변경 가능.
- [ ]  필드: 이름(폰), 점수(1점), 색깔, 포지션, Boolean isFirstMove
- 전면 판가름은 색깔에 따라서

## 퀸(queen)

- [ ]  비숍,룩 이동
- 필드: 이름(퀸), 점수(9점), 색깔,포지션

## 비숍(bishop)

- [ ]  대각선 이동
    - 우리 기물위치 전까지
- 필드: 이름(비숍) 점수(3점) ,색깔,포지션

## 룩(rook)

- [ ]  4방위 이동
    - 우리 기물 위치 전까지
- 필드: 이름 (룩), 점수(5점), 색깔,포지션

## 나이트(knight)

- [ ] 4방위 이동 1 + 4방위 이동 방향 대각선 1 이동
    - 적 기물 위치로
    - 우리 기물 위치 x
- 필드: 이름(나이트), 점수(3점), 색깔,포지션

## 킹(king)

- [ ] 4방위 1칸 이동.
    - 적 기물 위치로 우리 기물 위치 x
- 필드: 이름(킹), 점수(0점), 색깔, 포지션

## Null piece(?)

- Null 객체

# ChessBoard

- [ ]  현재 체크인지 판단
    - params: 색깔
    - 내 색깔 킹 위치 반환.
    - 상대방 기물들의 모든 이동 가능 위치 반환.
    - 1,2의 교집합이 생기면 true 아니면 false
-  필드: Map<Position, Piece>
    - Piece가 Null인지

# Position
- 필드: Enum Model.position.Row(8~1, 위 → 아래)
- 필드: Enum Column(a-h, 왼 → 오)

# Team
- 흑, 백

---
- [x] Enum Row, Column 생성
    - Row(8~1), Column(a-h)
- [x] Class Position 생성
    - 필드: Enum Model.position.Row(8~1, 위 → 아래)
    - 필드: Enum Column(a-h, 왼 → 오)
- [x] Team 생성
  - WHITE(1, 흰색)
  - BLACK(1, 검정)