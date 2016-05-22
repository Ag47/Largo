package shapio.largo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;

import shapio.largo.R;
import shapio.largo.model.Mail;

public class PeekActivity extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView receiveMailRecyclerView;
    private ArrayList<Mail> arrayListMailItem;
    private LinearLayoutManager mLayoutManager;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peek);

        initToolBar();
        initBottomBar();

        initPeekLayout();
//        initReceiveItem();
//        initRecyclerView();
        handleBottomBarOnclick();
//        rotateMail();
//        trigger();
        font();

    }



    @Override
    protected void onStop() {
        super.onStop();

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
        bottomNavigation.setNotification("3", 1);
        bottomNavigation.setCurrentItem(1);

    }

    private void handleBottomBarOnclick() {
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                switch (position) {
                    case 0:
//                        Toast.makeText(MainActivity.this, "you clicked write", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), SelectPaperThemeActivity.class));
                        break;
                    case 1:
//                        Toast.makeText(MainActivity.this, "you clicked Receive", Toast.LENGTH_LONG).show();
                        break;
                    case 2:
//                        Toast.makeText(MainActivity.this, "you clicked sent", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), SentMailActivity.class));
                        break;

                }
            }
        });
    }

    private void initPeekLayout() {
        final RelativeLayout peek_layout = (RelativeLayout) findViewById(R.id.peekable_layout);
        peek_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation peek = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.peek);
                peek.setFillAfter(true);
                peek_layout.startAnimation(peek);
            }
        });

    }

    private void font() {
        TextView textView = (TextView) findViewById(R.id.tv);
        textView.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/happy.ttf"));
        TextView sender = (TextView) findViewById(R.id.sender);
        sender.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/happy.ttf"));
        TextView timer = (TextView) findViewById(R.id.timer);
        timer.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/happy.ttf"));
    }

}
