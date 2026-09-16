# MKX Backend Agent Instructions

## Project Overview

MKX Backend는 증권 거래 플랫폼을 위한 MSA 기반 백엔드 프로젝트다.

주요 기술 스택:

* Java
* Spring Boot
* Gradle Composite Build
* Kafka
* Redis
* MariaDB
* EKS

주요 서비스:

* apigateway
* community
* eureka
* marketdata
* matching-engine
* mkx-platform
* ordering
* trading-bot

각 서비스는 독립적인 Gradle Build로 구성되어 있으며, 루트 `settings.gradle`의 `includeBuild`를 통해 Composite Build로 연결되어 있다.

---

## General Principles

모든 작업에서 다음 원칙을 따른다.

1. 기존 프로젝트 구조와 코드 스타일을 우선적으로 유지한다.
2. 요청받지 않은 코드는 수정하지 않는다.
3. 불필요한 리팩터링을 하지 않는다.
4. 기능 구현을 위해 필요한 최소 범위만 수정한다.
5. 기존 동작을 깨뜨릴 가능성이 있는 변경은 주의한다.
6. 확실하지 않은 내용을 추측하여 대규모 변경하지 않는다.
7. 작업 범위를 벗어난 개선 사항은 직접 수정하지 않고 필요하면 피드백으로 남긴다.

---

## Agent Workflow

기본 작업 흐름은 다음과 같다.

Planner
→ Generator
→ Reviewer

### Planner

작업 요구사항을 분석하고 구현 계획을 작성한다.

Planner는 코드를 직접 수정하지 않는다.

관련 파일 탐색이나 영향 범위 분석은 다음 기준으로 수행한다.

* 계획 수립에 실질적으로 도움이 되는 경우 수행한다.
* 단순한 작업에서 불필요한 전체 프로젝트 탐색은 하지 않는다.
* 많은 토큰이나 탐색 비용이 예상되는데 계획 품질에 큰 영향을 주지 않는다면 생략한다.

계획에는 반드시 작업 우선순위를 포함한다.

---

### Generator

Planner의 계획을 기반으로 실제 코드를 수정한다.

Generator는 다음 원칙을 따른다.

* Planner가 정한 범위 안에서 작업한다.
* 기존 코드 스타일과 구조를 유지한다.
* 불필요한 리팩터링을 하지 않는다.
* 요청사항 해결에 필요한 최소 변경을 우선한다.
* 계획 외 변경이 필요하다면 변경 이유를 명확하게 설명한다.

---

### Reviewer

Generator의 결과가 요청사항을 충족하는지 검토한다.

Reviewer는 최소한 다음 내용을 확인한다.

* 요구사항이 구현되었는가
* 명확한 버그나 오류가 존재하지 않는가
* 기존 기능을 쉽게 깨뜨릴 변경이 없는가
* 구현 범위가 불필요하게 커지지 않았는가

문제가 없다면 PASS를 반환한다.

치명적인 문제는 아니지만 개선할 부분이 있다면 구현을 실패 처리하지 않고 별도의 개선 피드백으로 제공한다.

---

## Review Philosophy

완벽한 코드를 요구하는 것이 목적이 아니다.

이번 작업의 요구사항을 안정적으로 만족하는지를 우선한다.

다음과 같은 경우는 반드시 수정하지 않아도 된다.

* 현재 요구사항과 직접 관련 없는 리팩터링
* 단순한 코드 스타일 선호 차이
* 미래를 위한 과도한 추상화
* 현재 문제 해결과 무관한 구조 개선

필요한 경우 `Improvement Suggestions`로만 남긴다.

## Git Commit

커밋 메시지를 작성하거나 커밋을 생성할 때는 반드시 다음 Skill을 읽고 따른다.

- `skills/git-commit/SKILL.md`

커밋 메시지는 실제 변경된 `git diff`를 기준으로 작성한다.