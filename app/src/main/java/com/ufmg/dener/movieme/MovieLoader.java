package com.ufmg.dener.movieme;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Dener on 17/04/2018.
 */

public class MovieLoader extends AsyncTaskLoader<ArrayList<Movie>> {

    public MovieLoader(Context context) {
        super(context);
    }

    @Override

    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Movie> loadInBackground() {
        DataBaseController crud = new DataBaseController(getContext());
        return crud.loadData();
    }
}
