package com.gt.fjbatresv.devs502.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.gt.fjbatresv.devs502.App;
import com.gt.fjbatresv.devs502.R;
import com.gt.fjbatresv.devs502.entities.Message;
import com.gt.fjbatresv.devs502.lib.base.ImageLoader;
import com.gt.fjbatresv.devs502.login.ui.LoginActivity;
import com.gt.fjbatresv.devs502.main.MainPresenter;
import com.gt.fjbatresv.devs502.main.ui.adapters.MessagesAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @Bind(R.id.container)
    LinearLayout container;

    @Bind(R.id.messages)
    RecyclerView messages;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Bind(R.id.message)
    EditText message;
    @Bind(R.id.sendBtn)
    ImageButton sendBtn;
    @Bind(R.id.messageProgessBar)
    ProgressBar messageProgessBar;
    /*@Bind(R.id.wrapperMessage)
    TextInputLayout wrapperMessage;*/
    @Bind(R.id.offline)
    LinearLayout offline;

    @Inject
    MainPresenter presenter;
    @Inject
    ImageLoader loader;
    @Inject
    MessagesAdapter adapter;

    private App app;

    private int visible = View.VISIBLE;
    private int gone = View.GONE;

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
        app.main(this, this).inject(this);
        messages.setLayoutManager(new LinearLayoutManager(this));
        messages.setAdapter(adapter);
    }

    @OnClick(R.id.sendBtn)
    public void sendBtn(){
        presenter.sendMessage(message.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadMessage();
    }

    @Override
    protected void onPause() {
        presenter.unSubMessages();
        adapter.clear();
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
        int id = item.getItemId();
        if (id == R.id.sign_out) {
            presenter.logout();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void loadMessages(Message message) {
        adapter.add(message);
        messages.scrollToPosition(adapter.getItemCount() - 1);
    }


    @Override
    public void loading(boolean load) {
        if (load){
            messages.setVisibility(gone);
            progressBar.setVisibility(visible);
        }else{
            messages.setVisibility(visible);
            progressBar.setVisibility(gone);
        }
    }

    @Override
    public void loadinSending(boolean load) {
        if (load){
            sendBtn.setEnabled(false);
            message.setEnabled(false);
            sendBtn.setVisibility(gone);
            messageProgessBar.setVisibility(visible);
        }else{
            sendBtn.setEnabled(true);
            message.setEnabled(true);
            sendBtn.setVisibility(visible);
            messageProgessBar.setVisibility(gone);
        }
    }

    @Override
    public void logout() {
        startActivity(new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|
        Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void loadUser(FirebaseUser user) {
        View header = navigationView.getHeaderView(0);
        TextView userName = (TextView) header.findViewById(R.id.userName);
        CircleImageView userAvatar = (CircleImageView) header.findViewById(R.id.userAvatar);
        if(user.getPhotoUrl() != null){
            loader.load(userAvatar, user.getPhotoUrl().toString());
        }
        userName.setText(user.getDisplayName());
        adapter.setUserUid(user.getUid());
    }

    @Override
    public void sendMessage() {
        message.setText(null);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSnack(String msg) {
        Snackbar.make(container, msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void connection(boolean conected) {
        if (conected){
            offline.setVisibility(gone);
            message.setVisibility(visible);
            sendBtn.setEnabled(true);
        }else{
            offline.setVisibility(visible);
            message.setVisibility(gone);
            sendBtn.setEnabled(false);
        }
    }
}
