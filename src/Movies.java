    public class Movies
    {

        String cname;
        String genre;
        String producer;
        String director;
        String releasedate;
        String id;
        String storyline;
        String rating;
        String coverphoto;
        String cast;
        String squarephoto;
        String movietitle;
        String videopath;
        String trailerpath;
        String duration;
        String language;

        Movies(String cname, String genre, String producer, String director, String storyline, String releasedate, String id, String movietitle, String duration, String rating, String language, String coverphoto, String cast, String squarephoto, String videopath, String trailerpath)
        {
            this.cname = cname;
            this.genre = genre;
            this.director = director;
            this.producer = producer;
            this.releasedate = releasedate;
            this.id = id;
            this.storyline = storyline;
            this.rating = rating;
            this.squarephoto = squarephoto;
            this.cast = cast;
            this.coverphoto = coverphoto;
            this.movietitle = movietitle;
            this.videopath = videopath;
            this.trailerpath = trailerpath;
            this.duration = duration;
            this.language = language;
        }
    }