package drivequickstart.example.com.myapplication;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ThemePreferenceActivity extends Activity {

    private RelativeLayout layout;
    private ImageButton chooseOutdoorImageButton, choosePartyImageButton, chooseCoffeeImageButton, chooseBeachImageButton;
    private ImageButton coffeeBt, outdoorBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_preference);

        layout = (RelativeLayout) findViewById(R.id.activity_theme_preference_layout);
        coffeeBt = (ImageButton) findViewById(R.id.coffee_imageButton);
        outdoorBt = (ImageButton) findViewById(R.id.outdoor_imageButton);
        chooseOutdoorImageButton = (ImageButton) findViewById(R.id.theme_outdoor_use_imageButton);
        choosePartyImageButton = (ImageButton) findViewById(R.id.theme_party_use_imageButton);
        chooseCoffeeImageButton = (ImageButton) findViewById(R.id.theme_coffee_use_imageButton);
        chooseBeachImageButton = (ImageButton) findViewById(R.id.theme_beach_use_imageButton);


        chooseOutdoorImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPrefeBackground(R.drawable.theme_outdoor_main, R.drawable.theme_outdoor_browse, R.drawable.theme_outdoor_match);
                layout.setBackgroundResource(new getPref().getThemeBrowseResID(ThemePreferenceActivity.this));
            }
        });

        chooseCoffeeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPrefeBackground(R.drawable.theme_coffee_main, R.drawable.theme_coffee_browse, R.drawable.theme_coffee_match);
                layout.setBackgroundResource(new getPref().getThemeBrowseResID(ThemePreferenceActivity.this));
            }
        });

        choosePartyImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPrefeBackground(R.drawable.theme_party_main, R.drawable.theme_party_browse, R.drawable.theme_party_match);
                layout.setBackgroundResource(new getPref().getThemeBrowseResID(ThemePreferenceActivity.this));
            }
        });
        chooseBeachImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPrefeBackground(R.drawable.theme_beach_main, R.drawable.theme_beach_browse, R.drawable.theme_beach_match);
                layout.setBackgroundResource(new getPref().getThemeBrowseResID(ThemePreferenceActivity.this));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        layout.setBackgroundResource(new getPref().getThemeBrowseResID(this));
    }

    private void setPrefeBackground(int mainBackgroundID, int browseBackgroundID, int matchBackgroundID){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        settings.edit().putInt(Strings.MAIN_SCREEN,mainBackgroundID).commit();
        settings.edit().putInt(Strings.BROWSE_SCREEN,browseBackgroundID).commit();
        settings.edit().putInt(Strings.MATCH_SCREEN,matchBackgroundID).commit();
    }

}
