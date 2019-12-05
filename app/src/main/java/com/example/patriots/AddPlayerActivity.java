package com.example.patriots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patriots.dummy.PatriotsPlayerContent;

public class AddPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        // the text fields for the fields of the player
        final EditText playerName = findViewById(R.id.editTextName);
        final EditText playerNumber = findViewById(R.id.editTextNumber);
        final EditText playerPosition = findViewById(R.id.editTextPosition);
        final EditText playerAge = findViewById(R.id.editTextAge);
        final EditText playerCollege = findViewById(R.id.editTextCollege);

        // back button returns to the playerlistactivity
        Button addBack = (Button) findViewById(R.id.addBack);
        addBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // return to playerlistactivity
                Intent intent = new Intent(view.getContext(), PlayerListActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        // confirm button confirms updates to player
        Button confirm = (Button) findViewById(R.id.addConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if any text fields are empty
                if (!playerAge.getText().toString().equals("")
                        && !playerCollege.getText().toString().equals("")
                        && !playerName.getText().toString().equals("")
                        && !playerNumber.getText().toString().equals("")
                        && !playerPosition.getText().toString().equals("")) {
                    // add player to database
                    PlayerListActivity.dbHandler.addPlayer(new PatriotsPlayerContent.PatriotsPlayer(playerName.getText().toString(),
                            playerNumber.getText().toString(),
                            playerPosition.getText().toString(),
                            playerAge.getText().toString(),
                            playerCollege.getText().toString()));
                } else {
                    // text field is empty let user know of error
                    Toast.makeText(getApplicationContext(), "Error left fields blank!", Toast.LENGTH_LONG).show();
                }

                // return to playerlistactivity
                Intent intent = new Intent(view.getContext(), PlayerListActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
