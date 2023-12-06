package algonquin.cst2355.finalproject013group2.SunriseSunset;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * SQLiteOpenHelper class for managing the sunrise and sunset times database.
 * It handles the creation and management of a local database that stores favorite locations with their respective sunrise and sunset details.
 */
public class sunriseDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sunriseDB";
    private static final String TABLE_FAVORITES = "favorites";

    // Column names
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "date";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_SUNRISE = "sunrise";
    private static final String KEY_SUNSET = "sunset";
    private static final String KEY_FIRST_LIGHT = "first_light";
    private static final String KEY_LAST_LIGHT = "last_light";
    private static final String KEY_DAWN = "dawn";
    private static final String KEY_DUSK = "dusk";
    private static final String KEY_SOLAR_NOON = "solar_noon";
    private static final String KEY_GOLDEN_HOUR = "golden_hour";
    private static final String KEY_DAY_LENGTH = "day_length";
    private static final String KEY_TIMEZONE = "timezone";

    /**
     * Constructor for the sunriseDBHelper class.
     *
     * @param context The context where the database helper is used.
     */
    public sunriseDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_DATE + " TEXT,"
                + KEY_LATITUDE + " TEXT,"
                + KEY_LONGITUDE + " TEXT,"
                + KEY_SUNRISE + " TEXT,"
                + KEY_SUNSET + " TEXT,"
                + KEY_FIRST_LIGHT + " TEXT,"
                + KEY_LAST_LIGHT + " TEXT,"
                + KEY_DAWN + " TEXT,"
                + KEY_DUSK + " TEXT,"
                + KEY_SOLAR_NOON + " TEXT,"
                + KEY_GOLDEN_HOUR + " TEXT,"
                + KEY_DAY_LENGTH + " TEXT,"
                + KEY_TIMEZONE + " TEXT"
                + ")";
        db.execSQL(CREATE_FAVORITES_TABLE);
    }

    /**
     * Called when the database needs to be upgraded. The implementation should use this method to drop tables, add tables, or do anything else it needs to upgrade to the new schema version.
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    /**
     * Adds a new favorite location to the database.
     *
     * @param favouriteLocation The favorite location object to be added to the database.
     * @return The row ID of the newly inserted row, or -1 if an error occurred.
     */
    public long addFavouriteLocation(FavouriteLocation favouriteLocation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_DATE, favouriteLocation.getDate());
        values.put(KEY_LATITUDE, favouriteLocation.getLatitude());
        values.put(KEY_LONGITUDE, favouriteLocation.getLongitude());
        values.put(KEY_SUNRISE, favouriteLocation.getSunrise());
        values.put(KEY_SUNSET, favouriteLocation.getSunset());
        values.put(KEY_FIRST_LIGHT, favouriteLocation.getFirstLight());
        values.put(KEY_LAST_LIGHT, favouriteLocation.getLastLight());
        values.put(KEY_DAWN, favouriteLocation.getDawn());
        values.put(KEY_DUSK, favouriteLocation.getDusk());
        values.put(KEY_SOLAR_NOON, favouriteLocation.getSolarNoon());
        values.put(KEY_GOLDEN_HOUR, favouriteLocation.getGoldenHour());
        values.put(KEY_DAY_LENGTH, favouriteLocation.getDayLength());
        values.put(KEY_TIMEZONE, favouriteLocation.getTimezone());

        long newRowId = db.insert(TABLE_FAVORITES, null, values);
        db.close();
        return newRowId; // Return the new row ID
    }

    /**
     * Retrieves all favorite locations from the database.
     *
     * @return A list of FavouriteLocation objects.
     */
    @SuppressLint("Range")
    public List<FavouriteLocation> getAllFavoriteLocations() {
        List<FavouriteLocation> favoriteList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_FAVORITES;
        Cursor cursor = db.rawQuery(selectQuery, null);

        int idIndex = cursor.getColumnIndex(KEY_ID);
        int latitudeIndex = cursor.getColumnIndex(KEY_LATITUDE);
        int longitudeIndex = cursor.getColumnIndex(KEY_LONGITUDE);
        int sunriseIndex = cursor.getColumnIndex(KEY_SUNRISE);
        int sunsetIndex = cursor.getColumnIndex(KEY_SUNSET);

        if (idIndex != -1 && latitudeIndex != -1 && longitudeIndex != -1 &&
                sunriseIndex != -1 && sunsetIndex != -1) {

            if (cursor.moveToFirst()) {
                do {
                    FavouriteLocation favourite = new FavouriteLocation();
                    favourite.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                    favourite.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                    favourite.setLatitude(cursor.getString(cursor.getColumnIndex(KEY_LATITUDE)));
                    favourite.setLongitude(cursor.getString(cursor.getColumnIndex(KEY_LONGITUDE)));
                    favourite.setSunrise(cursor.getString(cursor.getColumnIndex(KEY_SUNRISE)));
                    favourite.setSunset(cursor.getString(cursor.getColumnIndex(KEY_SUNSET)));
                    favourite.setFirstLight(cursor.getString(cursor.getColumnIndex(KEY_FIRST_LIGHT)));
                    favourite.setLastLight(cursor.getString(cursor.getColumnIndex(KEY_LAST_LIGHT)));
                    favourite.setDawn(cursor.getString(cursor.getColumnIndex(KEY_DAWN)));
                    favourite.setDusk(cursor.getString(cursor.getColumnIndex(KEY_DUSK)));
                    favourite.setSolarNoon(cursor.getString(cursor.getColumnIndex(KEY_SOLAR_NOON)));
                    favourite.setGoldenHour(cursor.getString(cursor.getColumnIndex(KEY_GOLDEN_HOUR)));
                    favourite.setDayLength(cursor.getString(cursor.getColumnIndex(KEY_DAY_LENGTH)));
                    favourite.setTimezone(cursor.getString(cursor.getColumnIndex(KEY_TIMEZONE)));

                    favoriteList.add(favourite);
                } while (cursor.moveToNext());
            }
        } else {
            Log.e("Database", "One or more columns are missing");
        }

        cursor.close();
        db.close();
        return favoriteList;
    }

    /**
     * Deletes a favorite location from the database.
     *
     * @param id The ID of the favorite location to be deleted.
     */
    public void deleteFavoriteLocation(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITES, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}

