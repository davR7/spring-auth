<div align="center">
  <a><img src="logo.png" width="400" alt="SpringBoot" /></a>
</div>

# SpringAuth
O projeto implementa um sistema seguro de autenticação usando o Spring Security 
com autenticação via token. A aplicação foi desenvolvida para suportar dois níveis 
distintos de permissões (roles) de usuário: ROLE_ADMIN e ROLE_BASIC. Os principais 
endpoints da aplicação são:

### Auth
```markdown
POST /auth/signup - Registrar o novo usuário.
POST /auth/signin - Logar com o usuário.
```
### Users
```markdown
GET /users - Recupera uma lista de todos os dados (administrador).
```

## Dependecias Utilizadas:
- spring-boot-starter-web
- spring-boot-starter-security
- java-jwt
- spring-boot-starter-data-jpa
- h2 (com.h2database)
- logback-classic
- spring-boot-starter-test
