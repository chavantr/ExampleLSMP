package lovesms.mywings.com.marathilovesms;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        final LinearLayout LoginBox =  findViewById(R.id.LoginBox);

        LoginBox.setVisibility(View.GONE);

        Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.translate);

        animTranslate.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation arg0) {
            }

            public void onAnimationRepeat(Animation arg0) {

            }

            public void onAnimationEnd(Animation arg0) {
                LoginBox.setVisibility(View.VISIBLE);
                LoginBox.startAnimation(AnimationUtils.loadAnimation(Splash.this, R.anim.fade));
            }
        });

        (findViewById(R.id.imageView1)).startAnimation(animTranslate);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Splash.this.startActivity(new Intent(Splash.this.getApplicationContext(), FirstListPage.class));
                Splash.this.finish();
            }
        }, 3000);




    }
}
