package com.example.patriots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enter = (Button) findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to player list activity
                Intent intent = new Intent(view.getContext(), PlayerListActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        Button facts = (Button) findViewById(R.id.facts);
        facts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to fact activity
                Intent intent = new Intent(view.getContext(), FactActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }


}
