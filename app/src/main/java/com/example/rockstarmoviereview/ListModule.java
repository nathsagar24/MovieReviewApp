package com.example.rockstarmoviereview;

import android.content.Context;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class ListModule {
    private List<ListItem.Results> results;
    private Context context;

    public ListModule(List<ListItem.Results> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @Provides
    ListViewAdapter provideAdapter(){
    return new ListViewAdapter(results, context);
}

    @Provides
    NowPlayingListViewAdapter provideNowPlayingAdapter(){
        return new NowPlayingListViewAdapter(results, context);
    }

}
