package dsa.eetac.upc.edu;

public class Track {
    //Parameters of the track
    public int id;
    public String title;
    public String singer;

    @Override
    public String toString(){
        return("Id: " + id + "  Title: " + title + "  Author: " + singer);
    }
}
