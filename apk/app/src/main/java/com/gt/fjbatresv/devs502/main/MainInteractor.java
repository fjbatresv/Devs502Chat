package com.gt.fjbatresv.devs502.main;

/**
 * Created by javie on 28/03/2017.
 */

public interface MainInteractor {
    void loadMessage();
    void loadUser();
    void unSubMessages();
    void sendMessage(String msg);
    void logout();
}
