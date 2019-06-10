package com.example.demo;

import com.example.demo.dao.ActorsRepository;
import com.example.demo.dao.MovieActorsRepository;
import com.example.demo.dao.MovieRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.model.*;
import com.example.demo.model.en.AuthProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;

@Component
@Slf4j

public class AutoCreate implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MovieRepository movieRepository;
    private final MovieActorsRepository movieActorsRepository;
    private final ActorsRepository actorsRepository;


    @Autowired
    public AutoCreate(UserRepository userRepository, PasswordEncoder passwordEncoder, MovieRepository movieRepository, MovieActorsRepository movieActorsRepository, ActorsRepository actorsRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.movieRepository = movieRepository;
        this.movieActorsRepository = movieActorsRepository;
        this.actorsRepository = actorsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.findByEmail("admin@filmweb.com").isPresent()) {
            this.userRepository.save(User.builder()
                    .email("admin@filmweb.com")
                    .password(this.passwordEncoder.encode("admin"))
                    .provider(AuthProvider.local)
                    .emailVerified(true)
                    .name("admin")
                    .roles(Roles.builder().role("ROLE_ADMIN").build())
                    .build()
            );
        }
        if (!userRepository.findByEmail("wegrzyn.adr@gmail.com").isPresent()) {
            this.userRepository.save(User.builder()
                    .email("wegrzyn.adr@gmail.com")
                    .password(this.passwordEncoder.encode("zaq1"))
                    .provider(AuthProvider.local)
                    .emailVerified(true)
                    .name("wegrzyn")
                    .roles(Roles.builder().role("ROLE_ADMIN").build())
                    .build()
            );
        }
        if(movieRepository.findByNameMovieAndDirectorAndYearOfProduction("X-Men: Mroczna Phoenix","Simon Kinberg",2019)==null){
            Movie movie = Movie.builder()
                    .director("Simon Kinberg")
                    .species("Akcja")
                    .price(12.60)
                    .image("https://cnet3.cbsistatic.com/img/my_YmFaRAIUNIEJIqdUAd27OZ30=/940x0/2019/06/04/165d230f-e8ae-484b-86c5-f684b22235eb/x-men-dark-phoenix-new-poster-1200-1777-81-s.jpg")
                    .nameMovie("X-Men: Mroczna Phoenix")
                    .yearOfProduction(1996)
                    .acceptableAge(18)
                    .description("FJean Grey, jedna z pierwszych członkiń X-Men, nigdy w pełni nie rozumiała głębi i złożoności.")
                    .build();

            Actors actors1 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Marek Marczuk")
                    .lastName("Aleksandra Wegrzyn")
                    .build();
            Actors actors2 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Władek Marczuk")
                    .lastName("Piotr Wegrzyn")
                    .build();

            MovieActors movieActors2=  MovieActors.builder()
                    .actors(actors1)
                    .movie(movie)
                    .build();

//            MovieActors movieActors1 = MovieActors.builder()
//                    .actors(actors2)
//                    .movie(movie)
//                    .build();
            this.movieActorsRepository.saveAll(Collections.singletonList(movieActors2));


        }
        if(movieRepository.findByNameMovieAndDirectorAndYearOfProduction("Venom","Ruben Fleischer",2018)==null){
            Movie movie = Movie.builder()
                    .director("Krzysztof Wowczuk")
                    .species("Akcja")
                    .price(12.60)
                    .image("https://ssl-gfx.filmweb.pl/po/99/06/519906/7857303.3.jpg")
                    .nameMovie("Venom")
                    .yearOfProduction(2018)
                    .acceptableAge(12)
                    .description("Kiedy Eddie Brock zdobywa moce symbionta, zmuszony jest uwolnić swoje alter-ego \"Venoma\", by ratować własne życie.  ")
                    .build();

            Actors actors1 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Ela Marczuk")
                    .lastName("Pawał Wegrzyn")
                    .build();
            Actors actors2 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Krysia Marczuk")
                    .lastName("Adrian Wegrzyn")
                    .build();


            this.movieActorsRepository.save(MovieActors.builder()
                    .actors(actors1)
                    .movie(movie)
                    .build()
            );
//            this.movieActorsRepository.save(MovieActors.builder()
//                    .actors(actors2)
//                    .movie(movie)
//                    .build()
//            );


        }
        if(movieRepository.findByNameMovieAndDirectorAndYearOfProduction("Rocketman","Dexter Fletcher",2019)==null){
            Movie movie = Movie.builder()
                    .director("Dexter Fletcher")
                    .species("Biograficzny")
                    .price(12.60)
                    .image("https://upload.wikimedia.org/wikipedia/en/thumb/0/0f/Rocketman_%28film%29.png/220px-Rocketman_%28film%29.png")
                    .nameMovie("Rocketman")
                    .yearOfProduction(2019)
                    .acceptableAge(12)
                    .description("Historia życia Eltona Johna ukazująca artystę od jego najmłodszych lat w Królewskiej Akademii Muzycznej po długoletnią współpracę z autorem tekstów, Berniem Taupinem.")
                    .build();

            Actors actors1 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Rysiek Marczuk")
                    .lastName("Sławek Wegrzyn")
                    .build();
            Actors actors2 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Kasia Marczuk")
                    .lastName("Adrian Wgrzyn")
                    .build();


            this.movieActorsRepository.save(MovieActors.builder()
                    .actors(actors1)
                    .movie(movie)
                    .build()
            );
//            this.movieActorsRepository.save(MovieActors.builder()
//                    .actors(actors2)
//                    .movie(movie)
//                    .build()
//            );


        }

        if(movieRepository.findByNameMovieAndDirectorAndYearOfProduction("Aladyn","Guy Ritchie",2019)==null){
            Movie movie = Movie.builder()
                    .director("Guy Ritchie")
                    .species("Przygodowy")
                    .price(12.60)
                    .image("https://media.multikino.pl/thumbnails/50/rc/QjE1QzdF/eyJ0aHVtYm5haWwiOnsic2l6ZSI6WyIxMDAwMCIsIjEwMDAwIl0sIm1vZGUiOiJpbnNldCJ9fQ==/uploads/images/films_and_events/aladyn-pl_19d4c36c0d.jpg")
                    .nameMovie("Aladyn")
                    .yearOfProduction(2019)
                    .acceptableAge(6)
                    .description("Chłopak o czystym sercu dostaje propozycję od potężnego wezyra, by znalazł dla niego tajemniczą lampę.")
                    .build();

            Actors actors1 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Pele Marczuk")
                    .lastName("Jan Wegrzyn")
                    .build();
            Actors actors2 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Krzysiek Marczuk")
                    .lastName("Zbyszek Wgrzyn")
                    .build();


            this.movieActorsRepository.save(MovieActors.builder()
                    .actors(actors1)
                    .movie(movie)
                    .build()
            );


        }
        if(movieRepository.findByNameMovieAndDirectorAndYearOfProduction("Pokémon: Detektyw Pikachu","Rob Letterman",2019)==null){
            Movie movie = Movie.builder()
                    .director("Rob Letterman")
                    .species("Animacja")
                    .price(12.60)
                    .image("https://ssl-gfx.filmweb.pl/po/40/86/234086/7038490.3.jpg")
                    .nameMovie("Pokémon: Detektyw Pikachu")
                    .yearOfProduction(2019)
                    .acceptableAge(6)
                    .description("Prywatny detektyw Harry Goodman znika w niewyjaśnionych okolicznościach, a jego 21-letni syn Tim próbuje ustalić, co się wydarzyło.")
                    .build();

            Actors actors1 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Rysiek Wrona")
                    .lastName("Sławek Wowczuk")
                    .build();
            Actors actors2 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Kasia Wowczuk")
                    .lastName("Adrian Wrona")
                    .build();


            this.movieActorsRepository.save(MovieActors.builder()
                    .actors(actors1)
                    .movie(movie)
                    .build()
            );
//            this.movieActorsRepository.save(MovieActors.builder()
//                    .actors(actors2)
//                    .movie(movie)
//                    .build()
//            );


        }
        if(movieRepository.findByNameMovieAndDirectorAndYearOfProduction("Tylko nie mów nikomu","Tomasz Sekielski",2019)==null){
            Movie movie = Movie.builder()
                    .director("Tomasz Sekielski")
                    .species("Dokumentalny")
                    .price(12.60)
                    .image("https://ssl-gfx.filmweb.pl/po/06/31/830631/7885650.3.jpg")
                    .nameMovie("Tylko nie mów nikomu")
                    .yearOfProduction(2019)
                    .acceptableAge(18)
                    .description("Dziennikarz śledczy rozmawia z dziewięcioma księżmi katolickimi, którzy dopuścili się zbrodni pedofilii i molestowania nieletnich, a także ich ofiarami.")
                    .build();

            Actors actors1 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Rysiek Koziol")
                    .lastName("Sławek Koziol")
                    .build();
            Actors actors2 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Kasia Koziol")
                    .lastName("Adrian Koziol")
                    .build();


            this.movieActorsRepository.save(MovieActors.builder()
                    .actors(actors1)
                    .movie(movie)
                    .build()
            );
//            this.movieActorsRepository.save(MovieActors.builder()
//                    .actors(actors2)
//                    .movie(movie)
//                    .build()
//            );


        }
        if(movieRepository.findByNameMovieAndDirectorAndYearOfProduction("Jestem matką","Grant Sputore",2019)==null){
            Movie movie = Movie.builder()
                    .director("Grant Sputore")
                    .species("Thriller")
                    .price(12.60)
                    .image("https://ssl-gfx.filmweb.pl/po/96/69/829669/7885644.6.jpg")
                    .nameMovie("Jestem matką")
                    .yearOfProduction(2019)
                    .acceptableAge(12)
                    .description("Po masowej zagładzie ludzkości wychowana przez robota nastolatka zaczyna zadawać trudne pytania, gdy na progu jej bunkra zjawia się ranna kobieta.")
                    .build();

            Actors actors1 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Rysiek Piek")
                    .lastName("Sławek Piek")
                    .build();
            Actors actors2 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Kasia Piek")
                    .lastName("Adrian Piek")
                    .build();


            this.movieActorsRepository.save(MovieActors.builder()
                    .actors(actors1)
                    .movie(movie)
                    .build()
            );
//            this.movieActorsRepository.save(MovieActors.builder()
//                    .actors(actors2)
//                    .movie(movie)
//                    .build()
//            );


        }
        if(movieRepository.findByNameMovieAndDirectorAndYearOfProduction("Królowa Kier","May el-Toukhy",2019)==null){
            Movie movie = Movie.builder()
                    .director("May el-Toukhy")
                    .species("Dramat")
                    .price(12.60)
                    .image("https://ssl-gfx.filmweb.pl/po/83/68/818368/7884197.6.jpg")
                    .nameMovie("Królowa Kier")
                    .yearOfProduction(2019)
                    .acceptableAge(12)
                    .description("Anne (Trine Dyrholm) jest szanowaną prawniczką, która pomaga nieletnim ofiarom molestowania seksualnego. Jej mąż, Peter (Magnus Krepper) to ...")
                    .build();

            Actors actors1 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Rysiek Smyk")
                    .lastName("Sławek Smyk")
                    .build();
            Actors actors2 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Kasia Smyk")
                    .lastName("Adrian Smyk")
                    .build();


            this.movieActorsRepository.save(MovieActors.builder()
                    .actors(actors1)
                    .movie(movie)
                    .build()
            );
//            this.movieActorsRepository.save(MovieActors.builder()
//                    .actors(actors2)
//                    .movie(movie)
//                    .build()
//            );


        }
        if(movieRepository.findByNameMovieAndDirectorAndYearOfProduction("Trzy kroki od siebie","Justin Baldoni",2019)==null){
            Movie movie = Movie.builder()
                    .director("Justin Baldoni")
                    .species("Melodramat")
                    .price(12.60)
                    .image("https://ssl-gfx.filmweb.pl/po/45/66/814566/7882313.3.jpg")
                    .nameMovie("Trzy kroki od siebie")
                    .yearOfProduction(2019)
                    .acceptableAge(12)
                    .description("Życie chorej na mukowiscydozę Stelli Grant ulega zmianie, gdy podczas pobytu w szpitalu poznaje pacjenta Willa Newmana.")
                    .build();

            Actors actors1 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Rysiek Qwerty")
                    .lastName("Sławek Qwerty")
                    .build();
            Actors actors2 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Kasia Qwerty")
                    .lastName("Adrian Qwerty")
                    .build();


            this.movieActorsRepository.save(MovieActors.builder()
                    .actors(actors1)
                    .movie(movie)
                    .build()
            );
//            this.movieActorsRepository.save(MovieActors.builder()
//                    .actors(actors2)
//                    .movie(movie)
//                    .build()
//            );


        }
        if(movieRepository.findByNameMovieAndDirectorAndYearOfProduction("Glass","M. Night Shyamalan",2019)==null){
            Movie movie = Movie.builder()
                    .director("M. Night Shyamalan")
                    .species("Thriller")
                    .price(12.60)
                    .image("https://ssl-gfx.filmweb.pl/po/87/82/798782/7872512.6.jpg")
                    .nameMovie("Glass")
                    .yearOfProduction(2019)
                    .acceptableAge(18)
                    .description("Strażnik David Dunn, wykorzystując swoje nadprzyrodzone zdolności, tropi człowieka o wielu osobowościach. ")
                    .build();

            Actors actors1 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Rysiek Agent")
                    .lastName("Sławek Agent")
                    .build();
            Actors actors2 = Actors.builder()
                    .dateOfBirth(Date.valueOf("1996-12-12"))
                    .firstName("Kasia Agent")
                    .lastName("Adrian Agent")
                    .build();


            this.movieActorsRepository.save(MovieActors.builder()
                    .actors(actors1)
                    .movie(movie)
                    .build()
            );
//            this.movieActorsRepository.save(MovieActors.builder()
//                    .actors(actors2)
//                    .movie(movie)
//                    .build()
//            );


        }

    }
}
