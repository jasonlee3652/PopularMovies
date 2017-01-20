package udacity.nanodegree.popularmovies.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import udacity.nanodegree.popularmovies.model.BackdropSize;
import udacity.nanodegree.popularmovies.model.ImageConfig;
import udacity.nanodegree.popularmovies.model.PosterSize;


@Dao
public interface ImageConfigurationDao {

    @Query("SELECT * FROM ImageConfig")
    List<ImageConfig> getAll();

    @Insert
    long insert(ImageConfig config);

    @Delete
    void delete(ImageConfig config);

    @Delete
    void delete(List<ImageConfig> configs);

    @Insert
    void insert(PosterSize size);

    @Insert
    void insert(BackdropSize size);
}
