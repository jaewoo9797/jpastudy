공통되는 필드가 있다.

`id`, `createAt`, `updateAt`
이를 공통적으로 관리하는 `baseEntity`를 만들어서 관리해보고 싶다.

## `@MappedSuperclass`란
객체 상속을 통해 사용하지만, 상속관계 매핑의 목적보다 공통 매핑 정보가 필요할 때 사용한다.
중복되는 속성을 생성하여 Entity생성 시 상속받아 중복되는 속성을 처리하는 데에 의미를 둔다.

```java
import javax.annotation.processing.Generated;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    
    @CreationTimestamp
    @Column(name="created_at", nullable=false, updateable = false)
    private LocalDateTime createAt;
    
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    private LocalDateTime updateAt;
}
```

`@Column`의 속성 `nullable`은 기본적으로 true의 값을 가진다. 이는 기본적으로 null이 들어올 수 있다
해당 필드는 DDL 생성 시 null 이 들어갈 수 있다. 만약 nullable 한 값을 넣지 않으려면 즉, not null로 설정하고 싶으면
속성 값을 `false`로 주어주자.

## Auditing 
감사, 심사의 의미를 가진다. 이는 엔티티가 수정 생성 되는 시점을 감지하여 그 시간과 생성, 수정 한 사람을 
기록하여 이력을 남길 수 있습니다.

`@EnableJpaAuditing` 어노테이션을 사용하여 기능 활성화
두 가지 방법이 존재
- Application 클래스 단계에서 어노테이션을 추가
```java
@EnableJpaAuditing
@SpringBootApplication
public class XXXApplication {
    
    public static void main...
}
```

- Configuration 분리
```java
@EnableJpaAuditing
@Configuration
public class AuditingConfig {
    
}
```
test code
```java
@DataJpaTest
@Import(AuditingConfig.class)
class TodoRepositoryTest {
    
}
```

### BaseEntity에 추가
조금 다르지만, 차이점은 다음과 같다. Jpa에서 제공하는 기능인가? Hibernate에서 제공하는 기능인가

지금 보는 baseEntity에 추가하는 기능은 Jpa에서 제공하는 기능으로 하이버네이트와 다르게 추가로 더 많은 기능 (생성, 수정 사용자 기록 등)을 제공하고
Spring 생태계와 잘 통합되어 많이 활용된다.

```java
@Getter
@MappedSuperClass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    
    // ...
    
    // 기존 어노테이션 @CreateionTimestamp
    @CreatedDate // 변경된 어노테이션
    @Column(name =...)
    
    // 기존 어노테이션 @UpdateTimestamp
    @LastModifedDate // 변경된 어노테이션
}
```

Jpa에서 제공하는 수정한 사람, 생성한 사람을 등록하고 싶을때

```java
@CreateBy
@Column(updateable = false)
private String createdBy;

@LastModifedBy
private String lastModifiedBy;
```