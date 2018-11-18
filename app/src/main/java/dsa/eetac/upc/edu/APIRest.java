package dsa.eetac.upc.edu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIRest {
    //We specify the url
    String BASE_URL = "http://147.83.7.155:8080/dsaApp/";

    //We add the GET method to obtain all the tracks in the list
    @GET("tracks")
    Call<List<Track>> getAllTracks();

    //We add the POST method to add a new track
    @POST("tracks")
    Call<Track> addTrack(@Body Track track);

    //We add the PUT method to modify the parameters of a track
    @PUT("tracks")
    Call<Track> updateTrack(@Body Track track);

    //We add the DELETE method to delete a track given its id
    @DELETE("tracks/{id}")
    Call<Void> deleteTrack(@Path("id") int id);


    static APIRest createAPIRest() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(APIRest.class);
    }
}
