package com.example.patriots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patriots.dummy.PatriotsPlayerContent;

public class UpdatePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_player);

        final EditText playerName = findViewById(R.id.editTextName);
        final EditText playerNumber = findViewById(R.id.editTextNumber);
        final EditText playerPosition = findViewById(R.id.editTextPosition);
        final EditText playerAge = findViewById(R.id.editTextAge);
        final EditText playerCollege = findViewById(R.id.editTextCollege);

        Button updateBack = (Button) findViewById(R.id.updateBack);
        updateBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go back to the player list activity
                Intent intent = new Intent(view.getContext(), PlayerListActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        Button updateConfirm = (Button) findViewById(R.id.updateConfirm);
        updateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validate input
                if (!playerAge.getText().toString().equals("")
                        && !playerCollege.getText().toString().equals("")
                        && !playerName.getText().toString().equals("")
                        && !playerNumber.getText().toString().equals("")
                        && !playerPosition.getText().toString().equals("")) {
                    // get the player name
                    Bundle name = getIntent().getExtras();

                    // delete the old player
                    PlayerListActivity.dbHandler.deletePlayer(name.getString("name"));

                    // update with new information
                    PlayerListActivity.dbHandler.addPlayer(new PatriotsPlayerContent.PatriotsPlayer(playerName.getText().toString(),
                            playerNumber.getText().toString(),
                            playerPosition.getText().toString(),
                            playerAge.getText().toString(),
                            playerCollege.getText().toString()));
                } else {
                    // else empty fields
                    Toast.makeText(getApplicationContext(), "Error left fields blank!", Toast.LENGTH_LONG).show();
                }

                // return to player list activity
                Intent intent = new Intent(view.getContext(), PlayerListActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
