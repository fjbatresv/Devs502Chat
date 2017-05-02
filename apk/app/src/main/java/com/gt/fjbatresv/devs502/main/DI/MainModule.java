package com.gt.fjbatresv.devs502.main.DI;

import android.content.Context;

import com.gt.fjbatresv.devs502.entities.Message;
import com.gt.fjbatresv.devs502.firebase.FirebaseHelper;
import com.gt.fjbatresv.devs502.lib.base.EventBus;
import com.gt.fjbatresv.devs502.lib.base.ImageLoader;
import com.gt.fjbatresv.devs502.main.MainInteractor;
import com.gt.fjbatresv.devs502.main.MainInteractorImpl;
import com.gt.fjbatresv.devs502.main.MainPresenter;
import com.gt.fjbatresv.devs502.main.MainPresenterImp;
import com.gt.fjbatresv.devs502.main.MainRepo;
import com.gt.fjbatresv.devs502.main.MainRepoImpl;
import com.gt.fjbatresv.devs502.main.ui.MainActivity;
import com.gt.fjbatresv.devs502.main.ui.MainView;
import com.gt.fjbatresv.devs502.main.ui.adapters.MessagesAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by javie on 29/03/2017.
 */
@Module
public class MainModule {
    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    MainView providesMainView(){
        return this.view;
    }

    @Singleton
    @Provides
    MainPresenter providesMainPresenter(EventBus bus, MainView view, MainInteractor interactor){
        return new MainPresenterImp(bus, view, interactor);
    }

    @Singleton
    @Provides
    MainInteractor providesMainInteractor(Context context, EventBus bus, MainRepo repo){
        return new MainInteractorImpl(context, bus, repo);
    }

    @Singleton
    @Provides
    MainRepo providesMainRepo(Context context, EventBus bus, FirebaseHelper helper){
        return new MainRepoImpl(context, bus, helper);
    }

    @Singleton
    @Provides
    MessagesAdapter providesMessagesAdapter(List<Message> messages, Context context, ImageLoader loader){
        return new MessagesAdapter(messages, context, loader);
    }

    @Singleton
    @Provides
    List<Message> providesMessageList(){
        return new ArrayList<Message>();
    }
}
