package drivequickstart.example.com.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by arthome on 2016/10/18.
 */

public final class getPref {

    public int getThemeMainResID(Context context){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getInt(Strings.MAIN_SCREEN,R.drawable.theme_outdoor_main);
    }

    public int getThemeBrowseResID(Context context){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getInt(Strings.BROWSE_SCREEN,R.drawable.theme_outdoor_browse);
    }
    public int getThemeMatchResID(Context context){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getInt(Strings.MATCH_SCREEN,R.drawable.theme_outdoor_match);
    }

    public int getThemeAlbumResID(Context context){
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        return settings.getInt(Strings.ALBUM_SCREEN,R.drawable.theme_outdoor_ablum);
    }
}
