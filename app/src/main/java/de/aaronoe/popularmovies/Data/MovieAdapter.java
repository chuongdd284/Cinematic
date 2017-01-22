package de.aaronoe.popularmovies.Data;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.aaronoe.popularmovies.MovieItem;
import de.aaronoe.popularmovies.R;

/**
 *
 * Created by aaron on 22.01.17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    // member variable for the list of movie data
    private List<MovieItem> mMovieList;

    private MovieAdapterOnClickHandler mClickHandler;

    /**
     * Creates a MovieAdapter
     *
     *
     */
    public MovieAdapter(MovieAdapterOnClickHandler onClickHandler) {
        mClickHandler = onClickHandler;
    }

    public interface MovieAdapterOnClickHandler {
        void onClick(MovieItem movieItem);
    }


    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        public final ImageView mImageView;


        public MovieAdapterViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.iv_movie_thumbnail);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            MovieItem thisMovie = mMovieList.get(adapterPosition);
            mClickHandler.onClick(thisMovie);
        }
    }


    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new ForecastAdapterViewHolder that holds the View for each list item
     */
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieAdapterViewHolder(view);
    }


    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder                    The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        // get the data of the current movie
        MovieItem thisMovieItem = mMovieList.get(position);
        // retrieve the poster picture path
        String picturePath = thisMovieItem.getmPosterPath();
        // put the picture URL together
        String pictureUrl = "http://image.tmdb.org/t/p/w342/" + picturePath;
        // get a reference to this item's ImageView
        ImageView currentImageView = holder.mImageView;
        // use picasso to load the image into the view
        Picasso.with(holder.itemView.getContext()).load(pictureUrl).into(currentImageView);
    }


    @Override
    public int getItemCount() {
        if (mMovieList == null) return 0;
        return mMovieList.size();
    }

    /**
     * This method is used to set the movie data on a MovieAdapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new MovieAdapter to display it.
     *
     * @param movieData The new movie data to be displayed.
     */
    public void setMovieData(List<MovieItem> movieData) {
        mMovieList = movieData;
        notifyDataSetChanged();
    }

}
