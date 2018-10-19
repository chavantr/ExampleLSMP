package lovesms.mywings.com.marathilovesms;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private ViewPagerAdapter adapter;
    int cat;
    Integer intCat;
    Integer intPosi;
    Integer item_cat;
    InterstitialAd mInterstitialAd;
    private ViewPager myPager;
    String name;
    int noofsize;
    private TestAdapter t_adapter;
    String[] title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.t_adapter = new TestAdapter(this);
        this.t_adapter.createDatabase();
        this.t_adapter.open();
        Intent intent = getIntent();
        String catTitle = intent.getStringExtra("cat_name");
        this.intCat = Integer.valueOf(intent.getIntExtra("cat", 0));
        setTitle(catTitle);
        this.noofsize = this.t_adapter.getAllCount(this.intCat.intValue());
        this.noofsize = this.t_adapter.GetCatCount(this.intCat.intValue());
        if (this.intCat.intValue() == 0) {
            this.noofsize = this.t_adapter.getAllCountFirst();
        }
        int intPosition = getLastValue();
        if (intPosition < 0 || intPosition > this.noofsize - 1) {
            intPosition = 0;
        }

        this.adapter = new ViewPagerAdapter(this, this.noofsize, this.intCat.intValue());
        this.myPager = (ViewPager) findViewById(R.id.reviewpager);
        this.myPager.setAdapter(this.adapter);
        this.myPager.setCurrentItem(intPosition);

        MobileAds.initialize(MainActivity.this, getString(R.string.app_id));

        AdView mAdView = (AdView) findViewById(R.id.adView);

        mAdView.loadAd(new AdRequest.Builder().build());

        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                MainActivity.this.requestInterstitial();
            }
        });
        requestInterstitial();
        this.adapter = new ViewPagerAdapter(this, this.noofsize, this.intCat.intValue());
        this.myPager = (ViewPager) findViewById(R.id.reviewpager);
        this.myPager.setAdapter(this.adapter);
        this.myPager.setCurrentItem(intPosition);
        this.myPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int arg0) {
                if (arg0 % 18 == 0) {
                    showInterstitial();
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }


    private void requestInterstitial() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }


    private void showInterstitial() {
        if (this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
        }
    }


    public int getLastValue() {
        try {
            return getSharedPreferences("com.mywings.lovesms" + this.intCat + BuildConfig.FLAVOR, 0).getInt(getString(R.string.last_viewed), 0);
        } catch (Exception e) {
            return 0;
        }
    }
}
