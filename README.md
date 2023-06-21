# notes-app

Hello World, sziasztok (Hungarian)!

This is my main ongoing showcase project as a junior full-stack developer, finishing at the bootcamp [Codecool](https://codecool.com/at/) in Austria.

## User story ##
I'm fond of taking notes. Everywhere. Notes are great to secure our recurring and often important but volatile thoughts. By doing so we can lean back and relax, because we can be sure that we aren't going forget a thing. Or are we? Well, notes often get out of hand when we mix up topics and start using different platforms, not to mention using multiple digital and tangible (physical) notebooks. There are for sure no surefire solution as all kinds of notebooks have their pros and cons. I'm still attempting to create one digital notebook for myself. Maybe I'll be (hopefully positively) surprised at the end. 

**Summary**: I as a private person need a simple, secure and customized digital note-taking app to help me navigate in today's information overloaded world.

### Stack ###
- Build: Maven
- Backend: Java SE 17 (data/service classes, Streams) + Spring Boot 3
- Frontend: ReactJS, JavaScript, HTML, CSS, Bootstrap
- Database: Postgres

### Tech specs: ###
- n-tier architecture
- Data class that represents the model/domain
- Service class that can handle CRUD operations, communicating with a repository class
- Endpoint class delegate logic to service class that communicates with database through JpaRepository interface
- DTO mappings to facilitate cleaner communication between backend and frontend
- Spring Web CRUD functionality in a @RestController
- Spring Data JPA with Postgres
- Spring Security: http basic login followed by JWT authentication on each request (OAuth2 Resource Server)
- Layered authorization
- Spring tests (@SpringBootTest)
- Web layer test (@WebMvcTest)
- Repository tests (@DataJpaTest)
- Spring security tests (@WithMockUser)
- Parameterized unit tests with JUnit
- Behavioral tests with Mockito
- React components & hooks, custom hook
- Preloaded default notes and users from YAML
- Lombok
- Axios
- Logging
- Custom exceptions
- Custom http response messages and status codes

---
<img width="960" alt="image" src="https://github.com/Geri306/notes-app/assets/107036298/34401674-6740-4218-b36e-734a7651e858">

---

**Created: 2023/05/02 (yyyy-mm-dd) <br />
Last updated: 2023/06/21 (yyyy-mm-dd)**

Any suggestions are welcome. 😎

_Cheers <br />
Gergő from Hungary, currently living in Germany_<br />
`gergosmith 306 at gmail dot com`
