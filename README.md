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


## Note

As a resource to make requests from Postman, this is the [`collection file`](https://github.com/felipevcc/pokemon-page/blob/main/PokemonApp.postman_collection.json).


## Author

* Felipe Villamizar <a href="https://github.com/felipevcc" rel="nofollow"><img align="center" alt="github" src="https://www.vectorlogo.zone/logos/github/github-tile.svg" height="24" /></a>

> Pokémon API consumed: [PokéAPI](https://pokeapi.co/)
