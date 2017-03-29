package com.gt.fjbatresv.devs502.main.ui;

import com.google.firebase.auth.FirebaseUser;
import com.gt.fjbatresv.devs502.entities.Message;

import java.util.List;

/**
 * Created by javie on 28/03/2017.
 */

public interface MainView {
    void loadMessages(Message message);
    void loading(boolean load);
    void loadinSending(boolean load);
    void logout();
    void loadUser(FirebaseUser user);
    void sendMessage();
    void showToast(String msg);
    void showSnack(String msg);
}
