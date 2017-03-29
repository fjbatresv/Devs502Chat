package com.gt.fjbatresv.devs502.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.gt.fjbatresv.devs502.App;
import com.gt.fjbatresv.devs502.R;
import com.gt.fjbatresv.devs502.entities.Message;
import com.gt.fjbatresv.devs502.lib.base.ImageLoader;
import com.gt.fjbatresv.devs502.login.ui.LoginActivity;
import com.gt.fjbatresv.devs502.main.MainPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.container)
    RelativeLayout container;

    @Inject
    MainPresenter presenter;
    @Inject
    ImageLoader loader;

    private App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDrawer();
        app = (App) getApplication();
        injection();
        presenter.onCreate();
        presenter.loadUser();
    }

    private void injection() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadMessage();
    }

    @Override
    protected void onPause() {
        presenter.unSubMessages();
        //Clear messages
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void initDrawer() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void loadMessages(Message message) {

    }

    @Override
    public void loading(boolean load) {

    }

    @Override
    public void loadinSending(boolean load) {

    }

    @Override
    public void logout() {
        startActivity(new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|
        Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void loadUser(FirebaseUser user) {

    }

    @Override
    public void sendMessage() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSnack(String msg) {
        Snackbar.make(container, msg, Snackbar.LENGTH_LONG).show();
    }
}
