package com.example.rockstarmoviereview;

import android.content.Context;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class ListModule {
    private List<ListItem.Results> results;
    private Context context;
    private int view_holder_category;

    public ListModule(List<ListItem.Results> results, Context context,int view_holder_category) {
        this.results = results;
        this.context = context;
        this.view_holder_category=view_holder_category;
    }

    @Provides
    ListViewAdapter provideAdapter(){
    return new ListViewAdapter(results, context,view_holder_category);
}


}
