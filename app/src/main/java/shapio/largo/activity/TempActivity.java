package shapio.largo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import shapio.largo.R;

public class TempActivity extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initBottomBar();
        handleBottomBarOnclick();
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
        bottomNavigation.setCurrentItem(1);

    }
    private void handleBottomBarOnclick(){
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                switch (position)
                {
                    case 0:
//                        Toast.makeText(TempActivity.this,"you clicked write",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(TempActivity.this, SelectPaperThemeActivity.class));
                        break;
                    case 1:
//                        Toast.makeText(TempActivity.this,"you clicked Receive",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
//                        Toast.makeText(TempActivity.this,"you clicked sent",Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });
    }

}
