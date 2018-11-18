package dsa.eetac.upc.edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    private APIRest myapirest;

    private final Track t = new Track();

    private String token;

    private Button authenticateButton;

    private Spinner functionsSpinner;
    private RecyclerView recyclerView;

    private Button delete;
    private Recycler myrecycler;

    private int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        functionsSpinner = (Spinner) findViewById(R.id.questions_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.implementations, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        functionsSpinner.setAdapter(arrayAdapter);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        myapirest = APIRest.createAPIRest();

        functionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
                if(text.equals("Show all Tracks")){
                    myapirest.getAllTracks().enqueue(tracksCallback);
                }
                else if (text.equals("Delete a Track")){
                    deleteTrack(view);
                }
                else if(text.equals("Create a new Track")){
                    createTrack(view);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                recyclerView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
    }

    //We will obtain the id from another layout
    public void deleteTrack(View view){
        Intent intent = new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }

    //We will obtain the parameters of the track from another layout
    public void createTrack(View view){
        Intent intent = new Intent(this  , CreateActivity.class);
        startActivity(intent);
    }

    public void updateTrack(View view){
        Intent intent = new Intent(this, UpdateActivity.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (token != null) {
            authenticateButton.setEnabled(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            token = data.getStringExtra("token");
        }
    }

    Callback<List<Track>> tracksCallback = new Callback<List<Track>>() {
        @Override
        public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
            if (response.isSuccessful()) {
                List<Track> datatrack = response.body();
                datatrack.addAll(response.body());
                recyclerView.setAdapter(new Recycler(datatrack));
                //ArrayAdapter<Track> arrayAdapter = new ArrayAdapter<Track>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item);
                //questionsSpinner.setAdapter(arrayAdapter);
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<List<Track>> call, Throwable t) {
            t.printStackTrace();
        }
    };
}