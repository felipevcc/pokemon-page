# Pokemon Page

Page to consult pokemons with their information and manage users.

## Tech Stack

### Database

- Engine: MySQL Community 8.0
- Command line tool: MySQL Workbench 8.0 CE

### Backend

- Java - [JDK 17](https://www.oracle.com/co/java/technologies/downloads/#jdk17-windows)
- Spring Boot 3.2.3
- Gradle 8.6
- IDE: IntelliJ IDEA Community Edition 2023.2.2

### Frontend

- Node ^18.10.0
- Angular 15.2.10

<br>

<details>

<summary><b>App images</b></summary>

<br>

Home view to see Pokémons (Unauthenticated user)

![Home view - Unauth user](https://github.com/felipevcc/pokemon-page/assets/95534180/5f05caa6-5ff3-43ac-a17c-3930e7500419)

Login view

![Login view](https://github.com/felipevcc/pokemon-page/assets/95534180/4f74b4ba-0845-4cc9-970b-b66bd5620d88)

Sign up view

![Sign up view](https://github.com/felipevcc/pokemon-page/assets/95534180/c47872d4-8c75-479e-a886-59b73512c0cf)

Home view (User logged in)

![Home view - Auth user](https://github.com/felipevcc/pokemon-page/assets/95534180/289f9e2f-4255-4e58-b59e-6ddef4bf6807)

User options in navbar

![User options in navbar](https://github.com/felipevcc/pokemon-page/assets/95534180/9d6d1867-1396-414d-b022-90ab523ef4e0)

Detail view of Pokémon

![Detail view of Pokémon](https://github.com/felipevcc/pokemon-page/assets/95534180/3c95e501-43d9-4c6d-ad31-669c45680aff)

User data update view

![User data update view](https://github.com/felipevcc/pokemon-page/assets/95534180/494dec1f-e1e2-43e6-bc72-cb9df0a9b4ca)

Password change view

![Password change view](https://github.com/felipevcc/pokemon-page/assets/95534180/4e83ee97-2f67-4c40-bb19-0005a0ed87d9)

</details>

<br>


## Installation

### Database

Open MySQL Workbench and create or select an instance. Then, Open a tab to run queries from the top bar, first option (Create a new SQL tab).
And finally, paste the code found in the file [`database.sql`](https://github.com/felipevcc/pokemon-page/blob/main/database.sql) and run it.

### Backend

First, from the editor (IntelliJ IDEA) open the service [`pokemon-api/`](https://github.com/felipevcc/pokemon-page/tree/main/pokemon-api). The editor will start configuring and installing the project.
Once configured, run the service from the editor.

### Frontend

Locate yourself in the directory [`pokemon-front/`](https://github.com/felipevcc/pokemon-page/tree/main/pokemon-front)

Once inside, run the following command to install the dependencies:

```bash	
npm install
```

Once the dependencies are installed, run the following command to start the application:

```bash
ng serve --open
```

<br>

## Note

As a resource to make requests from Postman, this is the [`collection file`](https://github.com/felipevcc/pokemon-page/blob/main/PokemonApp.postman_collection.json).

<br>

## Demo

To see a summary of the app watch this [video](https://drive.google.com/file/d/1StCBcu8XfMFshN0fGGqJ-QRKF4LyvLnA/view?usp=sharing).

<br>


## Author

* Felipe Villamizar <a href="https://github.com/felipevcc" rel="nofollow"><img align="center" alt="github" src="https://www.vectorlogo.zone/logos/github/github-tile.svg" height="24" /></a>

> Pokémon API consumed: [PokéAPI](https://pokeapi.co/)
