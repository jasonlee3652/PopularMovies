package udacity.nanodegree.popularmovies.api.models;

import android.content.Context;
import android.databinding.Observable;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;

import com.squareup.moshi.Json;

import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import udacity.nanodegree.popularmovies.R;

import static udacity.nanodegree.popularmovies.utils.Utils.colorWithAlpha;
import static udacity.nanodegree.popularmovies.utils.Utils.tintColor;

/**
 * {
 * .."page": 1,
 * .."results": [
 * ....{
 * ......"poster_path": "/z6BP8yLwck8mN9dtdYKkZ4XGa3D.jpg",
 * ......"adult": false,
 * ......"overview": "A big screen remake of John ... thieves.",
 * ......"release_date": "2016-09-14",
 * ......"genre_ids": [
 * ........28,
 * ........12,
 * ........37
 * ......],
 * ......"id": 333484,
 * ......"original_title": "The Magnificent Seven",
 * ......"original_language": "en",
 * ......"title": "The Magnificent Seven",
 * ......"backdrop_path": "/g54J9MnNLe7WJYVIvdWTeTIygAH.jpg",
 * ......"popularity": 38.19583,
 * ......"vote_count": 298,
 * ......"video": false,
 * ......"vote_average": 4.67
 * ....}
 * ..],
 * .."total_results": 19648,
 * .."total_pages": 983
 * }
 */
public class MoviesResponse {

    @Json(name = "page") public          int         page;
    @Json(name = "total_results") public int         totalResults;
    @Json(name = "total_pages") public   int         totalPages;
    @Json(name = "results") public       List<Movie> results;


    public static class Movie {

        @Json(name = "poster_path") public       String        posterPath;
        @Json(name = "adult") public             boolean       adult;
        @Json(name = "overview") public          String        overview;
        @Json(name = "release_date") public      LocalDate     releaseDate;
        @Json(name = "id") public                int           id;
        @Json(name = "original_title") public    String        originalTitle;
        @Json(name = "original_language") public String        originalLanguage;
        @Json(name = "title") public             String        title;
        @Json(name = "backdrop_path") public     String        backdropPath;
        @Json(name = "popularity") public        double        popularity;
        @Json(name = "vote_count") public        int           voteCount;
        @Json(name = "video") public             boolean       video;
        @Json(name = "vote_average") public      float         voteAverage;
        @Json(name = "genre_ids") public         List<Integer> genreIds;

        public transient ObservableInt colorTitle;
        public transient ObservableInt colorBackground;
        public transient ObservableInt colorTint;
        public transient ObservableInt colorTransparentBackground;

        public Movie() {

            colorTitle = new ObservableInt();
            colorBackground = new ObservableInt();
            colorTint = new ObservableInt();
            colorTransparentBackground = new ObservableInt();

            colorTitle.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {

                @Override
                public void onPropertyChanged(final Observable sender, final int propertyId) {
                    colorTint.set(tintColor(colorTitle.get()));
                }
            });

            colorBackground.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {

                @Override
                public void onPropertyChanged(final Observable sender, final int propertyId) {
                    colorTransparentBackground.set(colorWithAlpha(colorBackground.get(), 64));
                }
            });
        }

        public CharSequence fullTitle(@NonNull final Context ctx) {

            final TextAppearanceSpan titleAppearance = new TextAppearanceSpan(ctx,
                                                                              R.style.AppTheme_TextAppearance_White_16sp_Bold);
            final TextAppearanceSpan originalTitleAppearance = new TextAppearanceSpan(ctx,
                                                                                      R.style.AppTheme_TextAppearance_White70_14sp);
            SpannableString spannable;

            if (title.equals(originalTitle)) {
                spannable = new SpannableString(String.format(Locale.ENGLISH,
                                                              "%s (%d)",
                                                              title,
                                                              releaseDate()));
            } else {
                spannable = new SpannableString(String.format(Locale.ENGLISH, "%s\n%s (%d)",
                                                              title,
                                                              originalTitle,
                                                              releaseDate()));
            }
            spannable.setSpan(titleAppearance, 0, title.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannable.setSpan(originalTitleAppearance,
                              title.length(),
                              spannable.length(),
                              Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            return spannable;
        }

        public int releaseDate() {
            return releaseDate.getYear();
        }

        @Nullable
        public String genres(@NonNull final Context ctx) {
//            final List<Genre> genres = ((MovieApp) ctx.getApplicationContext()).genres;
//            if (genres == null || genres.isEmpty()) {
//                return null;
//            }
            final List<String> genreNames = new ArrayList<>();
//            for (Genre g : genres) {
//                if (genreIds.contains(g.id)) {
//                    genreNames.add(g.name);
//                }
//            }
            return TextUtils.join(", ", genreNames);
        }

        public float rating() {
            return voteAverage;
        }
    }
}
