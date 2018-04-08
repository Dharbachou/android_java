package by.bstu.pnv.education.laba_4_animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private ObjectAnimator animation1, animation2, animation3, animation4;
    private EditText editText;
    private ImageView imageView1, imageView2;
    private Random randon;
    private int width, height;
    private AnimatorSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);

        width = getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();
        randon = new Random();
    }

    public void onClick(View view) {
        int duration =  chek();
        String string = ((Button)view).getText().toString().toUpperCase();
        switch (string){
            case "ONE" : {Rotate(imageView1, duration);} break;
            case "ASYNC" : {Async(imageView1, imageView2, duration, 0);} break;
            case "SYNC" : {Async(imageView1, imageView2, duration, 1);} break;
            default: {} break;
        }
    }

    public void Rotate(final ImageView imageView, final  int duration){
        final float button1_x = imageView.getX();
        final float button1_y = imageView.getY();
        set = createAnimationRotate(imageView, duration);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                imageView.setX(button1_x);
                imageView.setY(button1_y);
            }
        });
    }

    public void Async(final ImageView imageView1, final ImageView imageView2,  final  int duration, int flag){
        final float button1_x = imageView1.getX();
        final float button1_y = imageView1.getY();
        final float button2_x = imageView2.getX();
        final float button2_y = imageView2.getY();
        if(flag == 0)
            set = createAnimation(imageView1, imageView2, duration);
        else if(flag == 1)
            set = createAnimationOne(imageView1, imageView2, duration);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                imageView1.setX(button1_x);
                imageView1.setY(button1_y);
                imageView2.setX(button2_x);
                imageView2.setY(button2_y);
            }
        });
    }

    private int chek(){
        Integer strDur = 3000;
        String strTR = editText.getText().toString();
        if(!strTR.isEmpty() && Pattern.compile("[0-9]").matcher(strTR).find()){
            strDur = Integer.valueOf(strTR);
            if(strDur < 0 && strDur > 7000){
                strDur = 3000;
            }
        }
        return strDur;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("NewApi")
    private AnimatorSet createAnimationRotate(ImageView btn, int duration) {
        int nextX = randon.nextInt(width);
        int nextY = randon.nextInt(height);
        animation1 = ObjectAnimator.ofFloat(btn, "x", nextX);
        animation1.setDuration(duration);
        animation2 = ObjectAnimator.ofFloat(btn, "y", nextY);
        animation2.setDuration(duration);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animation1, animation2);
        return set;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("NewApi")
    private AnimatorSet createAnimation(ImageView imgView1, ImageView imgView2, int duration) {
        int nextX = randon.nextInt(width);
        int nextY = randon.nextInt(height);
        animation1 = ObjectAnimator.ofFloat(imgView1, "x", nextX);
        animation1.setDuration(duration);
        animation2 = ObjectAnimator.ofFloat(imgView1, "y", nextY);
        animation2.setDuration(duration);
        animation3 = ObjectAnimator.ofFloat(imgView2, "x", nextX);
        animation3.setDuration(duration);
        animation4 = ObjectAnimator.ofFloat(imgView2, "y", nextY);
        animation4.setDuration(duration);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animation1, animation3);
        set.playSequentially(animation2, animation4);
        return set;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressLint("NewApi")
    private AnimatorSet createAnimationOne(ImageView imgView1, ImageView imgView2, int duration) {
        int nextX = randon.nextInt(width);
        int nextY = randon.nextInt(height);
        animation1 = ObjectAnimator.ofFloat(imgView1, "x", nextX);
        animation1.setDuration(duration);
        animation2 = ObjectAnimator.ofFloat(imgView1, "y", nextY);
        animation2.setDuration(duration);
        animation3 = ObjectAnimator.ofFloat(imgView2, "x", nextX);
        animation3.setDuration(duration);
        animation4 = ObjectAnimator.ofFloat(imgView2, "y", nextY);
        animation4.setDuration(duration);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animation1, animation3, animation2, animation4);
        return set;
    }
}
