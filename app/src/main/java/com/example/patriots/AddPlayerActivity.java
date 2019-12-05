package com.example.patriots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.patriots.dummy.PatriotsPlayerContent;

public class AddPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        final EditText playerName = findViewById(R.id.editTextName);
        final EditText playerNumber = findViewById(R.id.editTextNumber);
        final EditText playerPosition = findViewById(R.id.editTextPosition);
        final EditText playerAge = findViewById(R.id.editTextAge);
        final EditText playerCollege = findViewById(R.id.editTextCollege);


        Button addBack = (Button) findViewById(R.id.addBack);
        addBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PlayerListActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        Button confirm = (Button) findViewById(R.id.addConfirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playerAge.getText() != null
                        && playerCollege.getText() != null
                        && playerName.getText() != null
                        && playerNumber.getText() != null
                        && playerPosition.getText() != null) {
                    PlayerListActivity.dbHandler.addPlayer(new PatriotsPlayerContent.PatriotsPlayer(playerName.getText().toString(),
                            playerNumber.getText().toString(),
                            playerPosition.getText().toString(),
                            playerAge.getText().toString(),
                            playerCollege.getText().toString()));
                }

                Intent intent = new Intent(view.getContext(), PlayerListActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }
}
