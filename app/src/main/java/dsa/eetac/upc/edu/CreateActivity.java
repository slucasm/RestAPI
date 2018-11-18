package dsa.eetac.upc.edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateActivity extends AppCompatActivity {
    private Button createbtn;
    private TextView id;
    private TextView title;
    private TextView singer;

    private APIRest myapirest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        myapirest = APIRest.createAPIRest();
    }

    public void sendTrack(View view){
        createbtn = (Button) findViewById(R.id.createbtn);
        id = (TextView) findViewById(R.id.insertId);
        title = (TextView) findViewById(R.id.insertTitle);
        singer = (TextView) findViewById(R.id.insertSinger);

        int newid = Integer.parseInt(id.getText().toString());
        String newtitle = title.getText().toString();
        String newsinger = singer.getText().toString();

        Track t = new Track();
        t.id = newid;
        t.singer = newtitle;
        t.title = newsinger;

        myapirest.addTrack(t).enqueue(callback);

    }

    Callback<Track> callback = new Callback<Track>() {
        @Override
        public void onResponse(Call<Track> call, Response<Track> response) {
            if (response.isSuccessful()) {
                finish();
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<Track> call, Throwable t) {
            t.printStackTrace();
        }
    };
}
