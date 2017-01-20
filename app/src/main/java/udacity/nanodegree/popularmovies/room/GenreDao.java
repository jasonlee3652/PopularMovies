package udacity.nanodegree.popularmovies.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import udacity.nanodegree.popularmovies.model.Genre;


@Dao
public interface GenreDao {

    @Query("SELECT * FROM Genre")
    List<Genre> getAll();

    @Insert
    void insertAll(List<Genre> genres);

    @Delete
    void delete(Genre genre);

    @Delete
    void delete(List<Genre> genres);
}
