1. mappedBy
    - 양방향 연관관계에서 연관관계의 주인을 설정한다.
    - 주인은 외래 키를 관리하며, mappedBy 로 지정된 필드는 주인이 아님을 의미합니다.
    - comment 의 article 필드가 주인이므로, article 의 comments 필드에 mappedBy = "article"을 설정
2. cascade
    - 연관된 엔티티의 상태를 함께 관리하기 위해 사용하는 속성이다.
      - All : 모든 상태 변화를 전파
      - persist : 부모를 저장할 때 자식도 함께 저장.
      - merge : 부모를 병합할 때 자식도 함께 병함
      - remove : 부모를 삭제할 대 자식도 함께 삭제
      - refresh : 부모를 새로고침할 때 자식도 새로고침
      - detach : 부모를 분리할 때 자식도 분리
3. orphanRemoval
   - 고아 객체란, 부모와의 연관관계가 끊어진 자식 객체를 의미한다.
   - true로 설정 시 부모와의 연관관계가 끊어진 자식을 자동으로 삭제한다.
   - 예 : Article에서 특정 Comment를 제거하면 comment를 자동으로 삭제한다.
4. fetch
   - 연관 데이터를 로드할 때 사용하는 전략이다.
   - @OneToMany 의 기본 fetch 전략은 LAZY 입니다.
   - 