package algonquin.cst2355.finalproject013group2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import algonquin.cst2355.finalproject013group2.SunriseSunset.sunriseMain;

public class MainActivity extends AppCompatActivity {
    //ToDo: Onclick-listeners for each app.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize your button and set the onclick listener
        Button sunriseButton = findViewById(R.id.sunriseButton);
        sunriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the corresponding Activity
                Intent intent = new Intent(MainActivity.this, sunriseMain.class);
                startActivity(intent);
            }
        });
    }
}



//Button sunriseButton = findViewById(R.id.sunriseButton);
//sunriseButton.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        // Create an Intent to start the corresponding Activity
//        Intent intent = new Intent(CurrentActivity.this, SunriseActivity.class);
//        startActivity(intent);
//    }
//});

//Button sunriseButton = findViewById(R.id.sunriseButton);
//sunriseButton.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        // Create an Intent to start the corresponding Activity
//        Intent intent = new Intent(CurrentActivity.this, SunriseActivity.class);
//        startActivity(intent);
//    }
//});

//Button sunriseButton = findViewById(R.id.sunriseButton);
//sunriseButton.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        // Create an Intent to start the corresponding Activity
//        Intent intent = new Intent(CurrentActivity.this, SunriseActivity.class);
//        startActivity(intent);
//    }
//});
