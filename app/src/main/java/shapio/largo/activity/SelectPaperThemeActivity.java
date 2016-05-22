package shapio.largo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import java.util.ArrayList;

import shapio.largo.R;
import shapio.largo.adapter.SelectPaperAdapter;
import shapio.largo.helper.SimpleDividerItemDecoration;
import shapio.largo.model.Paper;

public class SelectPaperThemeActivity extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView paperRecyclerView;
    private ArrayList<Paper> arrayListPaperItem;
    private LinearLayoutManager mLayoutManager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_select_paper_theme);

        initToolBar();
        initBottomBar();

        initPaperItem();
        initPaperRecyclerView();

        handleBottomBarOnclick();
    }

    private void initPaperItem() {
        String[] paperName = {
                getResources().getString(R.string.paper_general),
                getResources().getString(R.string.paper_love),
                getResources().getString(R.string.paper_apologize)
        };
        Integer[] paperThumbIcon = {
                R.drawable.general_theme_thumb,
                R.drawable.love_letter_thumb,
                R.drawable.leisure_thumb
        };
        arrayListPaperItem = new ArrayList<Paper>();

        for (int i = 0; i < paperName.length; i++) {
            Paper itemPaper = new Paper();
            itemPaper.setPaperName(paperName[i]);
            itemPaper.setPaperThumbImage(paperThumbIcon[i]);
            arrayListPaperItem.add(i, itemPaper);
        }
    }

    private void initPaperRecyclerView() {

        mLayoutManager = new LinearLayoutManager(this.getApplication());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        paperRecyclerView = (RecyclerView) findViewById(R.id.recycler_paper);
        mAdapter = new SelectPaperAdapter(this, arrayListPaperItem);
        paperRecyclerView.setLayoutManager(mLayoutManager);
        paperRecyclerView.setAdapter(mAdapter);
        paperRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));
        paperRecyclerView.setHasFixedSize(true);


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
        bottomNavigation.setNotification("1", 1);
        bottomNavigation.setCurrentItem(0);
    }
    private void handleBottomBarOnclick(){
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {
                switch (position)
                {
                    case 0:
//                        Toast.makeText(SelectPaperThemeActivity.this,"you clicked write",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
//                        Toast.makeText(SelectPaperThemeActivity.this,"you clicked Receive",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        break;
                    case 2:
//                        Toast.makeText(SelectPaperThemeActivity.this,"you clicked sent",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), SentMailActivity.class));
                        break;

                }
            }
        });
    }

}
