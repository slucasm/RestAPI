package dsa.eetac.upc.edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {
    private Button deletebtn;
    private APIRest myapirest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        myapirest = APIRest.createAPIRest();
    }

    public void sendID(View view){
        deletebtn = (Button) findViewById(R.id.deletebtn);
        TextView insertid = (TextView) findViewById(R.id.editText);
        int id = Integer.parseInt(insertid.getText().toString());

        myapirest.deleteTrack(id).enqueue(callback);
    }

    Callback<Void> callback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (response.isSuccessful()) {
                finish();
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            t.printStackTrace();
        }
    };

}
