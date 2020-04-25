package com.example.rockstarmoviereview;

import dagger.Component;

@Component(modules = ListModule.class)
public interface ListAdapterComponent {
    void inject(ListViewActivity listViewActivity);
}
