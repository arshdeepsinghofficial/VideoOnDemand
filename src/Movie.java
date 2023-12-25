class Movie
{

    String vid;
    String producer;
    String director;
    String storyline;
    String video_title;
    String duration;
    String videopath;
    String trailerpath;
    String releasedate;
    String cpath;
    String spath;
    String rating;
    String language;
    String cname;

    public Movie(String vid,String cname, String producer, String director, String storyline, String video_title, String duration, String videopath, String trailerpath, String releasedate, String cpath, String spath, String rating, String language)
    {
        this.vid = vid;
        this.cname=cname;
        this.producer = producer; 
        this.director = director;
        this.storyline = storyline;
        this.video_title = video_title;
        this.duration = duration;
        this.videopath = videopath;
        this.trailerpath = trailerpath;
        this.releasedate = releasedate;
        this.cpath = cpath;
        this.spath = spath;
        this.rating = rating;
        this.language = language;
    }
}
