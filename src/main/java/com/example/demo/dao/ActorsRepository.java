package com.example.demo.dao;

        import com.example.demo.model.Actors;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;

        import java.util.Date;
        import java.util.List;

public interface ActorsRepository extends JpaRepository<Actors, Long> {
    Actors findByFirstNameAndLastNameAndDateOfBirth(String firstName, String lastName, Date dateOfBirth);

    @Query(
            value = "SELECT * from actors where actors_id  in(Select id_actors from movie_actors where id_movie =?1)",
            nativeQuery = true
    )
    List<Actors> findAllByActorsByMovieId(Long movie_id);
}
