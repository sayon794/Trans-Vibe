package team15.com.transvibe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class WaveActivity extends AppCompatActivity {

    WaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        waveView = new WaveView(this);
        setContentView(waveView); //needs to change, add waveview as a widget to an activity instead
    }
}
