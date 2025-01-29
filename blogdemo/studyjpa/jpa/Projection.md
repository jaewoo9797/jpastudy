## Projection


- Open Projection
- CLosed Projection

스프링 데이터 JPA는 Projection이라는 기능을 제공한다. Projection의 경우 엔티티 대신에 DTO를 편리하게 조회할 때 사용이 된다.

즉, 엔티티의 일부 데이터만 가지고 오고 싶은 경우 사용이 된다.

## Closed 프로젝션
- 컬럼 일부만 가져오기
- 쿼리를 최적화 할 수 있다. 가져오려는 attribute
- Java 8 디폴트 메서드를 사용해서 연산을 할 수 있다.

**인터페이스 기반의 Closed Projection 적용**

예 - Member 엔티티에서 특정 값만 가져오기 위해 인터페이스 생성
```java
public interface UserNameOnly {

  String getUserName();
}

public interface MemberRepository extends JpaRepository<Member, Long> {

  List<UserNameOnly> findProjectionsByUserName(@Param("username") String username)
}
```
인터페이스 안에 가져오고자 하는 필드명에 맞는 메서드를 생성한다.

- Projections 기능 사용을 위해 메서드 선언
- 반환 타입이 엔티티, DTO 가 아닌 인터페이스

인터페이스를 정의하면 스프링 데이터JPA에 의해 프록시 객체를 생성하여 결과를 반환해준다. 인터페이스에 @JsonNaming 어노테이션을 사용하여 반환 시 스네이크 케이스 적용도 가능하다.

클래스 기반의 Projection 적용

```java
public class UserNameOnlyDto {

  private final String userName;

  public UserNameOnlyDto(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }
}
```

클래스 기반의 Projection 적용을 위해 dto 클래스를 생성해주었다. 인터페이스 기반의 Projection과 다른 부분은 실제 DTO 클래스를 생성하여 사용하기에 프록시 객체가 아닌 실제 객체를 반환해준다는 차이점이 존재한다.

```java
public interface MemberRepository extends JpaRepository<Member, Long> {

  List<UserNameOnlyDto> findProjectionsByUserName(@Param("userName") String userName);
}
```
Projection이 최적화되어 결과를 잘 뱉어낸다.

또한 제네릭 타입으로 선언하여 사용할 수 있다.

```java
<T> List<T> findProjectionsByUserName(@Param("userName") String userName, Class<T> type);
```

클래스 기반의 Projection 중첩 구조

연관관계를 가지고 있는 객체도 함께 가져온다.
```java
public interface NestedClosedProjection {

  String getUserName();
  TeamInfo getTeam();

  interface TeamInfo {
    String getName();
  }
}
```

```sql
    select
        member0_.user_name as col_0_0_,
        team1_.team_id as col_1_0_,
        team1_.team_id as team_id1_2_,
        team1_.created_date as created_2_2_,
        team1_.updated_date as updated_3_2_,
        team1_.name as name4_2_
    from
        member member0_
    left outer join
        team team1_
            on member0_.team_id=team1_.team_id
    where
        member0_.user_name=?
```
Member의 경우 정확하게 user_name 필드만 조회되었지만 Team의 경우 모든 필드가 조회가 된 것을 쿼리를 통해 알 수 있다.

중첩 Projection 구조에서는 root에 있는 필드 -> String getUserName() 의 경우 최적화가 된다. 하지만 두 번째부터는 최적화가 되지 않으며 Join은 Left Join을 사용하여 데이터를 조회한다.

1. 프로젝션 대상이 root 엔티티면 유용하다
2. 프로젝트 대상이 root 엔티티를 넘어가면 JPQL SELECT 최적화가 불가능하다.
3. 실무에서는 단순할 때만 사용하고, 복잡할 경우 QueryDSL을 사용하자.
