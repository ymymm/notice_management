## 사용 기술 및 스택
- spring boot
- spring security
- jwt
- jpa
- mysql
- junit

### 구현
- aws를 통하여 mysql db 서버 구현
- 공지사항 관리 api 실행 시에 로그인 된 유저만 가능
- 로그인 후 발급되는 jwt 토큰을 통하여 유저 검증
- jpa를 사용하여 db 관리

### 테스트 구현 방법
- 기능 테스트를 위해 WithMockUser을 사용하여 임의로 권한 부여 후 테스트코드 작성
- junit, mockmvc를 활용

### 실행 방법
1. 회원가입 api 실행
2. 로그인 api 실행 하여 accessToken 추출
3. 테스트 하고자 하는 Api header에 AUTHORIZATION : bearer (로그인 후 발급 받은 access 토큰) 형식으로 추가 후 테스트 진

## 참고
- 서버 보안차원에서 Db 접송정보가 들어있는 yml은 ignore 후 업로드 하였습니다.
- 혹시, 직접 프로그램을 실행시켜 테스트를 원하시면 메일로 요청하시면 접속정보 yml파일을 전달드리도록 하겠습니다.

