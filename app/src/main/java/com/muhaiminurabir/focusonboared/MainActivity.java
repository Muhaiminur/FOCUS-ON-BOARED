package com.muhaiminurabir.focusonboared;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.wooplr.spotlight.SpotlightView;

import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        OnShowcaseEventListener {

    private android.widget.Button button;
    private android.widget.Button button2;
    private android.widget.Button button3;

    private GuideView mGuideView;
    private GuideView.Builder builder;
    private android.widget.Button button4;
    private android.widget.Button button5;

    ShowcaseView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.button5 = (Button) findViewById(R.id.button5);
        this.button4 = (Button) findViewById(R.id.button4);
        this.button3 = findViewById(R.id.button3);
        this.button2 = findViewById(R.id.button2);
        this.button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ShowcaseConfig config = new ShowcaseConfig();
                config.setDelay(500); // half second between each showcase view

                MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(MainActivity.this, "");

                sequence.setConfig(config);

                sequence.addSequenceItem(button,
                        "This is button one", "GOT IT");

                sequence.addSequenceItem(button2,
                        "This is button two", "GOT IT");

                sequence.addSequenceItem(button3,
                        "This is button three", "GOT IT");

                sequence.start();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                builder = new GuideView.Builder(MainActivity.this)
                        .setTitle("Guide Title Text")
                        .setContentText("Guide Description Text\n .....Guide Description Text\n .....Guide Description Text .....")
                        .setGravity(GuideView.Gravity.center)
                        .setDismissType(GuideView.DismissType.outside)
                        .setTargetView(button)
                        .setGuideListener(new GuideView.GuideListener() {
                            @Override
                            public void onDismiss(View view) {
                                switch (view.getId()){
                                    case R.id.button:
                                        builder.setTargetView(button2).build();
                                        break;
                                    case R.id.button2:
                                        builder.setTargetView(button3).build();
                                        break;
                                    case R.id.button3:
                                        return;
                                }
                                mGuideView = builder.build();
                                mGuideView.show();
                            }
                        });

                mGuideView = builder.build();
                mGuideView.show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new SpotlightView.Builder(MainActivity.this)
                        .introAnimationDuration(400)
                        .enableRevealAnimation(true)
                        .performClick(true)
                        .fadeinTextDuration(400)
                        .headingTvColor(Color.parseColor("#eb273f"))
                        .headingTvSize(32)
                        .headingTvText("Love")
                        .subHeadingTvColor(Color.parseColor("#ffffff"))
                        .subHeadingTvSize(16)
                        .subHeadingTvText("Like the picture?\nLet others know.")
                        .maskColor(Color.parseColor("#dc000000"))
                        .target(button)
                        .lineAnimDuration(400)
                        .lineAndArcColor(Color.parseColor("#eb273f"))
                        .dismissOnTouch(true)
                        .dismissOnBackPress(true)
                        .enableDismissAfterShown(true)
                        .usageId("") //UNIQUE ID
                        .show();

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                lps.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
                lps.setMargins(margin, margin, margin, margin);
                ViewTarget target = new ViewTarget(R.id.button4, MainActivity.this);
                sv = new ShowcaseView.Builder(MainActivity.this)
                        .withMaterialShowcase()
                        .setTarget(target)
                        .setContentTitle("Abir")
                        .setContentText("ABir demo")
                        .setStyle(R.style.CustomShowcaseTheme)
                        .setShowcaseEventListener(MainActivity.this)
                        .build();
                sv.setButtonPosition(lps);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Tutorial.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        Log.d("finding","6");
        int viewId = view.getId();
        switch (viewId) {
            case R.id.button4:
                Log.d("finding","6");
                if (sv.isShown()) {
                    sv.setStyle(R.style.CustomShowcaseTheme3);
                } else {
                    sv.show();
                }
                break;
        }
    }

    @Override
    public void onShowcaseViewHide(ShowcaseView showcaseView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
        }
        Log.d("finding","4");
    }

    @Override
    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
        Log.d("finding","3");
    }

    @Override
    public void onShowcaseViewShow(ShowcaseView showcaseView) {
        Log.d("finding","2");
    }

    @Override
    public void onShowcaseViewTouchBlocked(MotionEvent motionEvent) {
        Log.d("finding","1");
    }
}
