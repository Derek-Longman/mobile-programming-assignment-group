package algonquin.cst2355.finalproject013group2.SunriseSunset;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import algonquin.cst2355.finalproject013group2.R;
/**
 * The main activity class for the sunrise and sunset application.
 * It handles user input for latitude and longitude, makes API requests to fetch sunrise and sunset data,
 * displays favorite locations in a RecyclerView, and navigates to detail views.
 */
public class sunriseMain extends AppCompatActivity implements OnFavoriteClickListener {

    private EditText latitudeInput;
    private EditText longitudeInput;
    private RecyclerView favoritesRecyclerView;
    /**
     * Initializes the activity, including setting up the user interface and event listeners.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down then this Bundle contains the data it most recently
     *                           supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sunrisesunset_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        latitudeInput = findViewById(R.id.latitudeInput);
        longitudeInput = findViewById(R.id.longitudeInput);
        Button lookupButton = findViewById(R.id.lookupButton);
        Button viewFavoritesButton = findViewById(R.id.viewFavoritesButton);
        favoritesRecyclerView = findViewById(R.id.favoritesRecyclerView);

        RecyclerView recyclerView = findViewById(R.id.favoritesRecyclerView);

        // Initialize with an empty list and a dbHelper instance
        List<FavouriteLocation> favoritesList = new ArrayList<>();
        sunriseDBHelper dbHelper = new sunriseDBHelper(this);
        sunriseFavorites adapter = new sunriseFavorites(this, favoritesList, dbHelper, this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Restore previous search term
        String prevLatitude = getPreferences(MODE_PRIVATE).getString("prevLatitude", "");
        String prevLongitude = getPreferences(MODE_PRIVATE).getString("prevLongitude", "");
        latitudeInput.setText(prevLatitude);
        longitudeInput.setText(prevLongitude);


        lookupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitude = latitudeInput.getText().toString();
                String longitude = longitudeInput.getText().toString();
                fetchSunriseSunsetInfo(latitude, longitude);
                SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                editor.putString("prevLatitude", latitudeInput.getText().toString());
                editor.putString("prevLongitude", longitudeInput.getText().toString());
                editor.apply();
            }
        });

        viewFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFavorites(); // This method will show the dialog with the favorites list
            }
        });
    }
    /**
     * Handles the event when a favorite location is clicked. It fetches the sunrise and sunset
     * data for the selected favorite location.
     *
     * @param favoriteLocation The favorite location that was clicked.
     */
    @Override
    public void onFavoriteClick(FavouriteLocation favoriteLocation) {
        fetchSunriseSunsetInfo(favoriteLocation.getLatitude(), favoriteLocation.getLongitude());
    }
    /**
     * Fetches sunrise and sunset information from an external API for the given latitude and longitude.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     */
    private void fetchSunriseSunsetInfo(String latitude, String longitude) {
        String url = "https://api.sunrisesunset.io/json?lat=" + latitude + "&lng=" + longitude + "&date=today";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        handleApiResponse(response, latitude, longitude);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error, possibly show a message to the user
            }

            private String getCurrentDate() {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                return dateFormat.format(new Date());
            }
        });

        queue.add(stringRequest);
    }
    /**
     * Handles the API response, parsing the JSON and starting the sunriseDetail activity with the
     * fetched data.
     *
     * @param response  The JSON response from the API.
     * @param latitude  The latitude for which the data is fetched.
     * @param longitude The longitude for which the data is fetched.
     */
    private void handleApiResponse(String response, String latitude, String longitude) {
        // Now use the passed latitude and longitude instead of fetching from EditTexts
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONObject results = jsonResponse.getJSONObject("results");

            // Extract data from JSON response
            String date = results.optString("date", "Not available");
            String sunriseTime = results.optString("sunrise", "Not available");
            String sunsetTime = results.optString("sunset", "Not available");
            String firstLight = results.optString("first_light", "Not available");
            String lastLight = results.optString("last_light", "Not available");
            String dawn = results.optString("dawn", "Not available");
            String dusk = results.optString("dusk", "Not available");
            String solarNoon = results.optString("solar_noon", "Not available");
            String goldenHour = results.optString("golden_hour", "Not available");
            String dayLength = results.optString("day_length", "Not available");
            String timezone = results.optString("timezone", "Not available");

            // Pass the data to sunriseDetail activity via Intent
            Intent intent = new Intent(sunriseMain.this, sunriseDetail.class);
            intent.putExtra("Latitude", latitude);
            intent.putExtra("Longitude", longitude);
            intent.putExtra("SunriseTime", sunriseTime);
            intent.putExtra("SunsetTime", sunsetTime);
            intent.putExtra("FirstLight", firstLight);
            intent.putExtra("LastLight", lastLight);
            intent.putExtra("Dawn", dawn);
            intent.putExtra("Dusk", dusk);
            intent.putExtra("SolarNoon", solarNoon);
            intent.putExtra("DayLength", dayLength);
            intent.putExtra("Date", date);
            intent.putExtra("GoldenHour", goldenHour);
            intent.putExtra("Timezone", timezone);

            startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle the exception, possibly show an error message to the user
        }
    }


    /**
     * Displays a dialog with a list of favorite locations.
     */
    private void viewFavorites() {
        Dialog favoritesDialog = new Dialog(this);
        favoritesDialog.setContentView(R.layout.sunrise_favourites_list);

        RecyclerView recyclerView = favoritesDialog.findViewById(R.id.favoritesDialogRecyclerView);
        sunriseDBHelper dbHelper = new sunriseDBHelper(this);
        List<FavouriteLocation> favoritesList = dbHelper.getAllFavoriteLocations();

        sunriseFavorites adapter = new sunriseFavorites(this, favoritesList, dbHelper, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        favoritesDialog.show();

    }
    /**
     * Inflates the menu; this adds items to the action bar if it is present.
     *
     * @param menu The menu to be inflated.
     * @return true to display the menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    /**
     * Handles action bar item clicks. The action bar will automatically handle clicks on the Home/Up button,
     * as long as a parent activity is specified in AndroidManifest.xml.
     *
     * @param item The menu item that was selected.
     * @return true if the event was handled, false otherwise.
     */
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if (item.getItemId() == R.id.action_help) {
                showHelpDialog();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

    /**
     * Displays a help dialog with information about how to use the application.
     */
    private void showHelpDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.help_title)
                .setMessage(R.string.help_message)
                .setPositiveButton(R.string.ok, null)
                .show();
    }
}