package shapio.largo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;

import shapio.largo.R;
import shapio.largo.adapter.ReceiveMailAdapter;
import shapio.largo.adapter.SentMailAdapter;
import shapio.largo.helper.SimpleDividerItemDecoration;
import shapio.largo.model.Mail;

public class SentMailActivity extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView receiveMailRecyclerView;
    private ArrayList<Mail> arrayListMailItem;
    private LinearLayoutManager mLayoutManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_mail);

        initToolBar();
        initBottomBar();
        initReceiveItem();
        initRecyclerView();
        handleBottomBarOnclick();
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this.getApplication());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        receiveMailRecyclerView = (RecyclerView) findViewById(R.id.recycler_sent_mail);
        mAdapter = new SentMailAdapter(this, arrayListMailItem);
        receiveMailRecyclerView.setLayoutManager(mLayoutManager);
        receiveMailRecyclerView.setAdapter(mAdapter);
        receiveMailRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));
        receiveMailRecyclerView.setHasFixedSize(true);
    }

    private void initReceiveItem() {
        String[] senderName = {
                "Mum"
        };
        Integer[] senderIcon = {
                R.drawable.temp_user,
        };
        arrayListMailItem = new ArrayList<Mail>();

        for (int i = 0; i < senderName.length; i++) {
            Mail itemMail = new Mail();
            itemMail.setUserName(senderName[i]);
            itemMail.setProfileImage(senderIcon[i]);
            arrayListMailItem.add(i, itemMail);
        }
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initBottomBar() {

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_create_black_24dp, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.receive, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.sent, R.color.color_tab_3);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        bottomNavigation.setNotification("4", 1);
        bottomNavigation.setCurrentItem(2);

    }
    private void handleBottomBarOnclick(){
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                switch (position)
                {
                    case 0:
//                        Toast.makeText(SentMailActivity.this,"you clicked write",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SentMailActivity.this, SelectPaperThemeActivity.class));
                        break;
                    case 1:
//                        Toast.makeText(SentMailActivity.this,"you clicked Receive",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        break;
                    case 2:
//                        Toast.makeText(SentMailActivity.this,"you clicked sent",Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });
    }

}
