package com.gt.fjbatresv.devs502.main;

/**
 * Created by javie on 28/03/2017.
 */

public interface MainRepo {
    void loadMessage();
    void loadUser();
    void unSubMessages();
    void logout();
    void sendMessage(String msg);
}
