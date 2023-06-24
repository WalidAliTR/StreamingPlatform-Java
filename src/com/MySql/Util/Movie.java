package com.MySql.Util;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Movie {
        private final SimpleStringProperty movieName;
        private final SimpleStringProperty showType;
        private final DoubleProperty imdbRating;
        private final SimpleStringProperty genres;
        private final SimpleStringProperty ageRating;
        private final SimpleStringProperty length;
        private final SimpleStringProperty movieLink;

        public Movie(String movieName, String showType, double imdbRating, String genres,
                     String ageRating, String length, String movieLink) {
            this.movieName = new SimpleStringProperty(movieName);
            this.showType = new SimpleStringProperty(showType);
            this.imdbRating = new SimpleDoubleProperty(imdbRating);
            this.genres = new SimpleStringProperty(genres);
            this.ageRating = new SimpleStringProperty(ageRating);
            this.length = new SimpleStringProperty(length);
            this.movieLink = new SimpleStringProperty(movieLink);
        }

        public String getMovieName() {
            return movieName.get();
        }

        public SimpleStringProperty movieNameProperty() {
            return movieName;
        }

        public String getShowType() {
            return showType.get();
        }

        public SimpleStringProperty showTypeProperty() {
            return showType;
        }

        public double getImdbRating() {
            return imdbRating.get();
        }

        public DoubleProperty imdbRatingProperty() {
            return imdbRating;
        }

        public String getGenres() {
            return genres.get();
        }

        public SimpleStringProperty genresProperty() {
            return genres;
        }

        public String getAgeRating() {
            return ageRating.get();
        }

        public SimpleStringProperty ageRatingProperty() {
            return ageRating;
        }

        public String getLength() {
            return length.get();
        }

        public SimpleStringProperty lengthProperty() {
            return length;
        }

        public String getMovieLink() {
            return movieLink.get();
        }

        public SimpleStringProperty movieLinkProperty() {
            return movieLink;
        }
    }