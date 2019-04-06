package android.by.itacademy.countdown;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.ads.MobileAds;

public class Main extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        MobileAds.initialize(this, "ca-app-pub-1501215034144631/6744755337");
    }
}
 