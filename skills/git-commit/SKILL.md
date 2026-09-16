# Git Commit Skill

MKX 프로젝트에서 커밋 메시지를 작성할 때 다음 형식을 따른다.

## Format

```text
<Type>: <Summary>

- <변경사항 1>
- <변경사항 2>
- <변경사항 3>
Refs: #<issue-number>
```

## Type

다음 타입을 기본으로 사용한다.

* `Feat`: 새로운 기능 추가
* `Fix`: 버그 수정
* `Refactor`: 코드 구조 개선 및 리팩터링
* `Test`: 테스트 코드 추가 또는 수정
* `Docs`: 문서 수정
* `Chore`: 설정, 빌드, 의존성 등 기타 작업

타입의 첫 글자는 대문자로 작성한다.

## Summary Rules

제목은 다음 형식을 따른다.

```text
<Type>: <한글 요약>
```

예시:

```text
Feat: 오더북 Redis 클러스터 config 설정
Fix: 주문 접수 시 잔액 검증 오류 수정
Refactor: 주문 검증 로직 분리
```

규칙:

1. 한 줄로 간결하게 작성한다.
2. 변경의 핵심 목적을 표현한다.
3. 마침표를 붙이지 않는다.
4. 구현 세부사항보다 변경 목적을 우선한다.

## Body Rules

본문에는 실제 변경사항을 bullet 형식으로 작성한다.

```text
- 변경사항
- 변경사항
- 변경사항
```

규칙:

1. 각 항목은 `-`로 시작한다.
2. 실제로 변경한 내용만 작성한다.
3. 가능한 경우 무엇을 왜 변경했는지 드러나도록 작성한다.
4. 너무 세부적인 코드 단위 설명은 피한다.
5. 보통 2~5개의 항목으로 작성한다.
6. 변경하지 않은 내용을 추측하여 작성하지 않는다.

## Issue Reference

현재 브랜치명이 `feature{number}` 형식인 경우, `feature` 뒤의 숫자를 GitHub Issue 번호 후보로 간주한다.

예:

```text
feature21
feature/21
feature-21
```

위와 같은 브랜치에서 추출된 `21`은 GitHub Issue `#21`의 후보 번호다.

커밋 메시지를 작성하기 전에 다음 절차를 따른다.

1. 현재 브랜치명에서 Issue 번호 후보를 추출한다.
2. 해당 번호의 GitHub Issue를 조회한다.
3. Issue의 제목, 설명 및 작업 목적과 현재 실제 변경사항을 비교한다.
4. 현재 변경사항이 해당 Issue와 직접적이거나 깊은 연관이 있는 경우에만 커밋 메시지 마지막에 다음과 같이 작성한다.

```text
Refs: #21
```

5. 현재 작업과 Issue의 연관성이 낮거나 전혀 관련이 없는 경우 `Refs`를 작성하지 않는다.

### Rules

* 브랜치명의 번호만 보고 자동으로 `Refs`를 추가하지 않는다.
* 반드시 해당 GitHub Issue의 내용을 확인한 후 판단한다.
* 현재 `git diff`와 실제 변경사항을 기준으로 Issue와의 연관성을 판단한다.
* 단순히 같은 브랜치에서 작업했다는 이유만으로 Issue를 참조하지 않는다.
* 연관성이 불명확한 경우에는 `Refs`를 생략한다.
* 존재하지 않는 Issue 번호를 임의로 생성하거나 추측하지 않는다.

### Example

현재 브랜치:

```text
feature21
```

GitHub Issue `#21`:

```text
Redis Cluster 기반 오더북 구성
```

현재 변경사항:

```text
- 오더북 Redis Cluster ConnectionFactory 추가
- Redis Cluster Properties 구성
- order-book Redis 설정 추가
```

이 경우 Issue와 직접적으로 연관되어 있으므로:

```text
Feat: 오더북 Redis 클러스터 config 설정

- 오더북 Redis Cluster 연결 설정 추가
- Redis Cluster Properties 객체 구성
- 기본 Redis ConnectionFactory 설정
Refs: #21
```

반대로 `feature21` 브랜치에서 README 문구 수정처럼 Issue `#21`과 관계없는 작업을 커밋하는 경우에는 `Refs: #21`을 작성하지 않는다.


## Example

```text
Feat: 오더북 Redis 클러스터 config 설정

- yml에 `common`과 `order-book`을 분리하여 공통 Redis와 오더북 Redis Cluster를 각각 연결하도록 설정
- 각각 `@ConfigurationProperties`를 이용해 Redis 설정 Properties 객체를 생성
- 기본 Redis ConnectionFactory를 명시하기 위해 `fee-policy` ConnectionFactory에 `@Primary` 적용
Refs: #21
```

## Commit Creation Rules

커밋 메시지를 작성하기 전에 실제 변경사항을 확인한다.

가능하면 다음 정보를 기준으로 메시지를 작성한다.

* 현재 git diff
* 변경된 파일
* Planner의 작업 목표
* Generator가 실제 구현한 내용

커밋 메시지는 계획이 아니라 실제 변경 결과를 기준으로 작성한다.

불필요하게 내용을 부풀리거나 구현하지 않은 내용을 포함하지 않는다.
