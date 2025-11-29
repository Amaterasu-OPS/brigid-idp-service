<samp><h6 align="center">#backend, #project, #idp</h6></samp>
# <samp align="center"><h2 align="center">Briged IdP</h2></samp>

<p align="center">
  <img src="https://img.shields.io/badge/kotlin-22272E?&style=for-the-badge&logo=kotlin&logoColor=7F52FF">
  <img src="https://img.shields.io/badge/docker-22272E?style=for-the-badge&logo=docker&logoColor=2496ED">
  <img src="https://img.shields.io/badge/gradle-22272E?style=for-the-badge&logo=gradle&logoColor=78FF96">
  <img src="https://img.shields.io/badge/spring-22272E?style=for-the-badge&logo=spring&logoColor=6DB33F">
</p>
<br/>

Overview
Briged IdP is an identity provider for user authentication. It offers username/password login, JWT token issuance, and token revocation using Redis.

## Run

```bash
git clone https://github.com/Amaterasu-OPS/brigid-idp-service.git

# OR clone with SSH

git clone git@github.com:Amaterasu-OPS/brigid-idp-service.git

cd brigid-idp-service

docker compose up
```

Open [localhost:8001/api/v1/health](http://localhost:8001/api/v1/health) to access the health route.

## Features

- User management
- User groups and roles
- Username/password authentication

## Contribute

Want to be part of this project?

Whether it’s improving documentation, fixing bugs, or adding new features — your help is always welcome.

Just fork the repo, make your changes, and open a pull request. Let’s build something great together!

## License
MIT License. See `LICENSE` file for details.