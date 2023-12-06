package algonquin.cst2355.finalproject013group2.SunriseSunset;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import algonquin.cst2355.finalproject013group2.R;
/**
 * Adapter class for a RecyclerView, which displays a list of favorite locations.
 * This class is responsible for providing views that represent each favorite location in the data set.
 */
public class sunriseFavorites extends RecyclerView.Adapter<sunriseFavorites.FavoriteViewHolder> {
    private Context context;
    private List<FavouriteLocation> favoritesList;
    private sunriseDBHelper dbHelper;
    private OnFavoriteClickListener clickListener;
    /**
     * Constructor for the sunriseFavorites adapter.
     *
     * @param context         The context where the adapter is used.
     * @param favoritesList   The list of favorite locations.
     * @param dbHelper        An instance of sunriseDBHelper to interact with the database.
     * @param clickListener   A listener to handle clicks on favorite items.
     */
    public sunriseFavorites(Context context, List<FavouriteLocation> favoritesList, sunriseDBHelper dbHelper, OnFavoriteClickListener clickListener) {
        this.context = context;
        this.favoritesList = favoritesList;
        this.dbHelper = dbHelper;
        this.clickListener = clickListener;
    }
    /**
     * Called when RecyclerView needs a new {@link FavoriteViewHolder} of the given type to represent
     * an item. This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. The new ViewHolder will be used to display items of the adapter using onBindViewHolder.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
        // Create new views (invoked by the layout manager)
        @Override
        public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent, false);
            return new FavoriteViewHolder(itemView);
        }
    /**
     * Called by RecyclerView to display the data at the specified position. This method updates the contents
     * of the {@link FavoriteViewHolder#itemView} to reflect the item at the given position.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the item at
     *                 the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(FavoriteViewHolder holder, int position) {
            // Get the item from the favorites list
            FavouriteLocation location = favoritesList.get(position);

            // Set the TextViews to the properties of the location
            holder.latitudeTextView.setText(String.format("Latitude: %s", location.getLatitude()));
            holder.longitudeTextView.setText(String.format("Longitude: %s", location.getLongitude()));
            holder.sunriseTextView.setText(String.format("Timezone: %s", location.getTimezone()));

            // Set the click listener for the entire item view
            holder.itemView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onFavoriteClick(favoritesList.get(holder.getAdapterPosition()));
                }
            });

            // Set the click listener for the delete button
            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an AlertDialog.Builder instance
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete this saved location?")
                            .setTitle("Confirm Deletion");

                    // Set up the buttons
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // User clicked Yes button. Perform the deletion.
                            int id = location.getId();
                            dbHelper.deleteFavoriteLocation(id);
                            favoritesList.remove(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, favoritesList.size());

                            Toast.makeText(context, "Location deleted", Toast.LENGTH_SHORT).show();

                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // User cancelled the dialog. No action needed.
                            dialog.dismiss();
                        }
                    });

                    // Create and show the AlertDialog
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            });
        }
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return favoritesList.size();
        }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     * sunriseFavorites.ViewHolder holds the views for each data item. Complex data items may need
     * more than one view per item, and these views are all contained in the ViewHolder object.
     */
        public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
            public TextView latitudeTextView;
            public TextView longitudeTextView;
            public TextView sunriseTextView;

            public Button deleteButton;
        /**
         * Constructor for the FavoriteViewHolder.
         *
         * @param view The view that the ViewHolder will hold.
         */
            public FavoriteViewHolder(View view) {
                super(view);
                latitudeTextView = view.findViewById(R.id.latitude_text_view);
                longitudeTextView = view.findViewById(R.id.longitude_text_view);
                sunriseTextView = view.findViewById(R.id.sunriseTZTextView);
                deleteButton = view.findViewById(R.id.deleteButton);
            }
        }
    }
