package shapio.largo.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

import shapio.largo.R;

public class WriteMessageContentActivity extends AppCompatActivity {
    RelativeLayout mLayout;
    private Toolbar toolbar;
//    private final String[] array = {"Add gift"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);

        initToolBar();
//        initBottomBar();
        initPaperBackground();

//        handleBottomBarOnclick();

//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.view_row, R.id.header_text, array);
//        final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) findViewById(R.id.listview);

//        expandableLayoutListView.setAdapter(arrayAdapter);
        send();
        setFont();
    }

    private void initPaperBackground() {
        mLayout = (RelativeLayout) findViewById(R.id.paper_background);
        Bundle paperBundle = getIntent().getExtras();
        String materialType = paperBundle.getString(this.getResources().getString(R.string.papers_material_type));
        Log.v("oska", materialType);

        if (materialType.equals(this.getResources().getString(R.string.paper_general))) {
            Log.v("oska", "in general");

            mLayout.setBackground(this.getResources().getDrawable(R.drawable.general_theme));
        } else if (materialType.equals(this.getResources().getString(R.string.paper_love))) {
            Log.v("oska", "in love");
            mLayout.setBackground(this.getResources().getDrawable(R.drawable.love_letter));
        }
//        if(materialType.equals(this.getResources().getString(R.string.paper_apologize)));
//        {
//            mLayout.setBackground(this.getResources().getDrawable(R.drawable.leisure));
//        }
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

//    private void initBottomBar() {
//
//        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
//
//        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_create_black_24dp, R.color.color_tab_1);
//        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.receive, R.color.color_tab_2);
//        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.sent, R.color.color_tab_3);
//        bottomNavigation.addItem(item1);
//        bottomNavigation.addItem(item2);
//        bottomNavigation.addItem(item3);
//        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
//        bottomNavigation.setNotification("4", 1);
//        bottomNavigation.setCurrentItem(0);
//    }
//    private void handleBottomBarOnclick(){
//        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(int position, boolean wasSelected) {
//                switch (position)
//                {
//                    case 0:
//                        Toast.makeText(WriteMessageContentActivity.this,"you clicked write",Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(WriteMessageContentActivity.this, SelectPaperThemeActivity.class));
//                        break;
//                    case 1:
//                        Toast.makeText(WriteMessageContentActivity.this,"you clicked Receive",Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        break;
//                    case 2:
//                        Toast.makeText(WriteMessageContentActivity.this,"you clicked sent",Toast.LENGTH_LONG).show();
//                        break;
//
//                }
//            }
//        });

    private void send() {
        final ImageView send = (ImageView) findViewById(R.id.send);
        final RelativeLayout paper_bg = (RelativeLayout) findViewById(R.id.paper_background);
        final ImageView letter = (ImageView) findViewById(R.id.letter);
        final ImageView letter_top = (ImageView) findViewById(R.id.letter_top);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.FadeIn).duration(1000).playOn(letter);
                YoYo.with(Techniques.FadeIn).duration(1000).playOn(letter_top);
                letter.setVisibility(View.VISIBLE);
                letter_top.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideOutDown)
                        .duration(3000)
                        .playOn(paper_bg);
                YoYo.with(Techniques.FadeOut).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        YoYo.with(Techniques.SlideOutRight)
                                .duration(2000)
                                .playOn(letter);
                        YoYo.with(Techniques.SlideOutRight).withListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {

                            }
                        })
                                .duration(2000)
                                .playOn(letter_top);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                        .duration(3000)
                        .playOn(send);

            }
        });
    }

    private void setFont()
    {
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/happy.ttf"));
    }
}
