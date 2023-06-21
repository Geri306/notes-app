# notes-app

Hello World, sziasztok (Hungarian)!

This is my main ongoing showcase project as a junior full-stack developer, finishing at the bootcamp [Codecool](https://codecool.com/at/) in Austria.

## User story ##
I'm fond of taking notes. Everywhere. Notes are great to secure our recurring and often important but volatile thoughts. By doing so we can lean back and relax, because we can be sure that we aren't going forget a thing. Or are we? Well, notes often get out of hand when we mix up topics and start using different platforms, not to mention using multiple digital and tangible (physical) notebooks. There are for sure no surefire solution as all kinds of notebooks have their pros and cons. I'm still attempting to create one digital notebook for myself. Maybe I'll be (hopefully positively) surprised at the end. 

**Summary**: I as a private person need a simple, secure and customized digital note-taking app to help me navigate in today's information overloaded world.

### Stack ###
- Build: Maven
- [x] Java (data/service classes, Streams)
- [x] Spring Web:
  - [x] CRUD functionality in a @RestController
- [x] Spring Data JPA:
  - [x] at least one @Entity with a repository
  - [x] H2 database)
- [x] Spring Security
  - [x] basic authentication
  - [x] Oauth2 Resource Server for JWT
- [x] Spring tests
  - [x] @SpringBootTest
  - [x] @WebMvcTest
  - [x] @DataJpaTest
- [x] Spring security tests (@WithMockUser)
- [x] JUnit:
  - [x] tests
  - [x] parameterized tests
- [x] Mockito
- [x] Postgres
- [x] React.js:
  - [x] several components
  - [x] use of several hooks
  - [x] custom hook
- [] React tests:
  - [] Vitest
  - [] React Testing Library
- [x] JavaScript + HTML + CSS & Bootstrap

### Core features: ###
- frontend with ReactJS
- security: registration & login authentication
- tests, tests, tests
- backend with Spring Boot
    - data class that represents the model/domain ✅
    - service class that can handle CRUD operations, communicating with a repository class ✅
    - endpoint class delegate logic to service class that communicates with database through JpaRepository interface ✅
- preloaded default notes and users from YAML
- logging preloaded datasets
- connected to postgres database
- custom exceptions
- custom http response messages and status codes

---

**Created: 2023/05/02 (yyyy-mm-dd) <br />
Last updated: 2023/06/21 (yyyy-mm-dd)**

Any suggestions are welcome. 😎

_Cheers <br />
Gergő from Hungary, currently living in Germany_<br />
`gergosmith 306 at gmail dot com`
