package algonquin.cst2355.finalproject013group2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import algonquin.cst2355.finalproject013group2.SunriseSunset.sunriseMain;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        // Assuming you have menu items that correspond to the buttons
        if (id == R.id.action_sunrise) {
            Intent intent = new Intent(MainActivity.this, sunriseMain.class);
            startActivity(intent);
            return true;
        }
        // ... (Handle other menu item clicks)

        return super.onOptionsItemSelected(item);
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
