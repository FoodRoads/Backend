# 🍽️ FoodRoads

자신만의 맛집 리스트 공유 어플

## 1. 제작 기간 & 참여 인원

- 2023.02.07 ~ 진행중
- 팀 프로젝트

## 2. 사용 기술

- Java 17
- Spring Boot 3.0
- Gradle
- Spring Data JPA
- MySQL 8.0
- AWS EC2
- AWS RDS

## 3. 핵심 기능

## 4. 주요 트러블 슈팅

## 5. 그 외 트러블 슈팅

<details>
<summary>Dirty Checking 수정 안되는 현상</summary>

[문제]

![image](https://user-images.githubusercontent.com/40589394/220272762-abf4a6d0-00a0-4146-9571-95639e10c2fa.png)

수정 테스트 상황에서

![image](https://user-images.githubusercontent.com/40589394/220274164-55c7606a-367a-4a53-9c6e-c3f80752bb4f.png)

맛집 리스트의 이름을 수정했지만

![image](https://user-images.githubusercontent.com/40589394/220273818-a6b7c82b-0e6f-4a48-ab71-c3f0ea0405ad.png)

수정 사항이 반영이 되지 않은 모습

[문제 원인]

JPA는 엔티티 조회 후 영속성 컨텍스트의 1차 캐시에 저장할 때 스냅샷과 함께 저장한다. <br>
그러고나서 commit()또는 flush()가 일어날 때 엔티티와 스냅샷을 비교해서, 변경 사항이 있으면 UPDATE SQL을 알아서 만들어서 DB에 저장한다.

하지만 위 updateStoreList()에서는 commit()또는 flush()가 일어나지 않아 변경 사항이 반영되지 않았던 것이다.

[해결 방법]

`@Transactional` 애노테이션을 붙여 해당 메소드가 트랜잭션으로 묶여 메서드가 끝나는 지점에 트랜잭션 commit이 발생하게 되거 flush가 자동으로 작동됩니다.

![image](https://user-images.githubusercontent.com/40589394/220281481-62c976a4-e465-4e46-86d4-41780855d67b.png)

![image](https://user-images.githubusercontent.com/40589394/220281803-1774b14a-19a3-412d-ac6d-ba246724d73a.png)

</details>