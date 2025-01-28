백엔드와 프론트엔드 간의 소통에서 네이밍 규칙이 차이를 해결해보고자 한다.

1. 백엔드 - 캐멀케이스
   백엔드는 변수 네이밍 규칙이 캐멀케이스로 다음과 같이 변수 내 단어 간 규칙이다. `createAt`
   이와 달리 프론트엔드의 변수 네이밍 규칙은 스테이크 케이스로 백엔드와 달리 `create_at` 이처럼 변수를 처리한다.

이런 두 종단 간의 차이를 해결하기 위해서 `@JosnNaming` 이라는 어노테이션을 사용하여 스프링부트에서 파싱을 자동으로 해주도록 설정할 수 있다.

예를 들어서 프론트엔드로 데이터를 전송하는 dto 객체에 `@JsonNaming` 어노테이션으로 어떤 규칙으로 파싱할 것인지 설정을 해주면된다.

이때 더이상 관리되지 않는 속성과 사용해야하는 속성에 대해서 짚고 넘어가자

### deprecated

'com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy' is deprecated

```java
@Builder
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public record CategoryResponse(
        @NotNull Long id,
        @NotBlank String name
) {
}

```

아래와 같이 사용해주면 된다.

```java
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CategoryResponse(
        @NotNull Long id,
        @NotBlank String name
) {
}

```

이걸 모든 응답 객체에 설정해주는 것은 반복적인 코드를 계속해서 적어야하는 단점이 있다. 이를 관리하는 방법을 찾아보자

## application.yml

```yaml
spring:
  jackson:
    property-naming-strategy: SNAKE_CASE
```

etc

만약 Json 응답을 해줄때, 날짜 등 복잡한 설정을 해주어야 한다면, application 보다는 configuration 파일을 만들어서 오브젝트 매퍼 설정을 해주어야 한다.

이때 설정 파일과 클래스 중 스프링 부트는 configuration 클래스 파일을 우선한다.