package shapio.largo.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

import shapio.largo.R;

public class ReadMessageContentActivity extends AppCompatActivity {
    RelativeLayout mLayout;
    private Toolbar toolbar;
//    private final String[] array = {"Add gift"};

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_message);

        initToolBar();
//        initBottomBar();
        initPaperBackground();

//        handleBottomBarOnclick();

//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.view_row, R.id.header_text, array);
//        final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) findViewById(R.id.listview);

//        expandableLayoutListView.setAdapter(arrayAdapter);
//        send();
        setFont();
        read();

    }

    private void initPaperBackground() {
        mLayout = (RelativeLayout) findViewById(R.id.paper_background);
        mLayout.setBackground(this.getResources().getDrawable(R.drawable.general_theme));
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

    private void setFont() {
        tv = (TextView) findViewById(R.id.editText);
        tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/happy.ttf"));
    }

    int mIndex = 0;
    CharSequence mText = "asdf";
    long mDelay = 1000;
    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            tv.setText(mText.subSequence(0, mIndex++));
            if (mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    public void animateText(CharSequence text) {
        mText = text;
        mIndex = 0;

        tv.setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    public void setCharacterDelay(long millis) {
        mDelay = millis;
    }

    private void read() {
        final ImageView send = (ImageView) findViewById(R.id.send);
        final RelativeLayout paper_bg = (RelativeLayout) findViewById(R.id.paper_background);
        final ImageView letter = (ImageView) findViewById(R.id.letter);
        final ImageView letter_top = (ImageView) findViewById(R.id.letter_top);

        YoYo.with(Techniques.RotateInUpLeft).duration(2000).playOn(letter);
        letter.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.RotateInUpLeft).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                YoYo.with(Techniques.SlideInUp).duration(2000).playOn(paper_bg);
                paper_bg.setVisibility(View.VISIBLE);
                setCharacterDelay(100);
                animateText("Good morning, Angel" +
                        "\n I am planning to build this app through the night" +
                        "\n I know it cant be flawless but the show must go on" +
                        "\n Code4Impact." +
                        "\n By Shapio | Largo");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).duration(2000).playOn(letter_top);
        letter_top.setVisibility(View.VISIBLE);
    }
}