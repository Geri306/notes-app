# notes-app

Hello World, sziasztok (Hungarian)!

This is my main ongoing showcase project as a junior full-stack developer, finishing at the bootcamp [Codecool](https://codecool.com/at/) in Austria.

## User story ##
I'm fond of taking notes. Everywhere. Notes are great to secure our recurring and often important but volatile thoughts. By doing so we can lean back and relax, because we can be sure that we aren't going forget a thing. Or are we? Well, notes often get out of hand when we mix up topics and start using different platforms, not to mention using multiple digital and tangible (physical) notebooks. There are for sure no surefire solution as all kinds of notebooks have their pros and cons. I'm still attempting to create one digital notebook for myself. Maybe I'll be (hopefully positively) surprised at the end. 

**Summary**: I as a private person need a simple, secure and customized digital note-taking app to help me navigate in today's information overloaded world.

### Stack ###
- IDE: IntelliJ
- Build: Maven
- [x] Java (data/service classes, Streams)
- Spring Framework + Spring Boot
- [x] Spring Web:
  - [x] CRUD functionality in a @RestController)
- [x] Spring Data JPA:
  - [x] at least one @Entity with a repository
  - [x] H2 database)
- [] Spring Security
  - [] basic authentication
  - [] Oauth2 Resource Server for JWT
- [/] Spring tests
  - [x] @SpringBootTest
  - [] @WebMvcTest
  - [x] @DataJpaTest
- [] Spring security tests (@WithMockUser)
- [/] JUnit:
  - [x] tests
  - [] parameterized tests
- [x] Mockito
- [x] Postgres
- [x] React.js:
  - [x] several components
  - [x] use of several hooks
  - [x] at least one custom hook
- [] React tests:
  - [] Vitest
  - [] React Testing Library
- [x] JavaScript + HTML + CSS & Bootstrap

### Core features: ###
- frontend CRUD with React.js 🛠️
- security: registration & login authentication
- tests, tests, tests
- containerized with docker
- backend CRUD with Spring Boot✅
- preloaded default notes from YAML ✅
- logging preloaded default notes ✅
- connected to postgres database ✅
- custom exceptions ✅
- custom http response messages and status codes ✅

### Extra features (if time allows): ###
- RESTful API with hyperlinks to relevant operations
- grouping of tasks
- deletable profile
- delete all note marked as "done"
- delete all notes (+ delete only groups of notes)
- completed task move automatically to the bottom
- notes sortable in frontend
- notes filterable in frontend (e.g. by label)
- downloadable notes (e.g. in csv format)
- email verification with [fake SMTP service](https://ethereal.email/)
- session timeout (e.g. 10 min)

## Schedule ##

### Week 1 ###

**Code**
- backend CRUD: ✅
    - data class that represents the model/domain ✅
    - service class that can handle CRUD operations, communicating with a repository class ✅
    - endpoint class delegate logic to service class that communicates with database through JpaRepository interface ✅
- react CRUD (single page✅, same URL✅) 🛠️
- RESTful API with hyperlinks to relevant operations
- preloaded default notes ✅
- logging preloaded default notes ✅
- custom exceptions ✅
- custom http response messages and status codes ✅
- connected to postgres database ✅

### Week 2 ###

**Code**
- cc videos
- atomic commits
- connect branches to github issues and pull requests (2 git article)
- Spring Security (with basic authentication and Oauth2 Resource  Server for JWT)
- test:
    - Spring security tests (@WithMockUser)
    - Spring tests (@SpringBootTest, @WebMvcTest, @DataJpaTest ✅)
    - endpoints with @WebMvcTest
    - service without involving Spring
    - repository without involving Spring
    - junit
    - mockito
    - React tests (Vitest + React Testing Library)

### Week 3 ###

**Code**
- frontend testing

### Week 4 ###

**Code**
- containerized with docker
- downloadable notes
- testing

---

**Created: 2023/05/02 (yyyy-mm-dd) <br />
Last updated: 2023/05/09 (yyyy-mm-dd)**

Any suggestions are welcome. 😎

_Cheers <br />
Gergő from Hungary, currently living in Germany_<br />
`gergosmith 306 at gmail dot com`
