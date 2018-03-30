package coltectp.github.io.movieme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.database.Cursor;
import android.widget.TextView;
import android.widget.ImageView;

import static coltectp.github.io.movieme.provider.MovieContract.*;

/**
 * Created by Germano Barcelos on 29/03/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Cursor mCursor;

    void setMovie(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mCursor.moveToPosition(position)) {
            holder.mNameMovie.setText(mCursor.getString
                    (mCursor.getColumnIndexOrThrow(MovieEntry.COLUMN_NAME)));

            holder.mGenre.setText(String.valueOf(mCursor.getInt
                    (mCursor.getColumnIndexOrThrow(MovieEntry.COLUMN_GENRE))));

            holder.mReleaseDate.setText(String.valueOf(mCursor.getInt
                    (mCursor.getColumnIndexOrThrow(MovieEntry.COLUMN_RELEASE_DATE))));

            switch (mCursor.getInt(mCursor.getColumnIndexOrThrow(MovieEntry.COLUMN_AGE_GROUP))) {
                case MovieEntry.AGE_GROUP_L:
                    holder.mAgeGroup.setImageResource(R.drawable.icon_free);
                    break;
                case MovieEntry.AGE_GROUP_10:
                    holder.mAgeGroup.setImageResource(R.drawable.icon_ten);
                    break;
                case MovieEntry.AGE_GROUP_12:
                    holder.mAgeGroup.setImageResource(R.drawable.icon_twelve);
                    break;
                case MovieEntry.AGE_GROUP_14:
                    holder.mAgeGroup.setImageResource(R.drawable.icon_fourteen);
                    break;
                case MovieEntry.AGE_GROUP_16:
                    holder.mAgeGroup.setImageResource(R.drawable.icon_sixteen);
                    break;
                case MovieEntry.AGE_GROUP_18:
                    holder.mAgeGroup.setImageResource(R.drawable.icon_eighteen);
                    break;
                default:
                    holder.mAgeGroup.setImageResource(R.drawable.icon_free);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNameMovie;
        TextView mReleaseDate;
        TextView mGenre;
        ImageView mAgeGroup;
        ViewHolder (ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.list_movie_item, parent, false));

            mNameMovie = itemView.findViewById(R.id.lm_name_tv);
            mReleaseDate = itemView.findViewById(R.id.lm_release_date_tv);
            mGenre = itemView.findViewById(R.id.lm_genre_tv);
            mAgeGroup = itemView.findViewById(R.id.lm_icon_iv);
        }
    }
}
