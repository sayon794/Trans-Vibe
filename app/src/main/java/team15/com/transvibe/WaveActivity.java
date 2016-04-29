package team15.com.transvibe;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class WaveActivity extends AppCompatActivity {

    WaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wave);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.eidalagbo);
        waveView = new WaveView(this);
        layout.addView(waveView);
        //RelativeLayout layout = new RelativeLayout(this);
        //layout.addView(new SurfaceView(this));
        //setContentView(layout); //needs to change, add waveview as a widget to an activity instead
        //waveView = new WaveView(this);
        //layout.addView(waveView);
    }

    @Override
    protected void onDestroy() {
        waveView.drawingThread.stopThread();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        try {
            super.onBackPressed();
            waveView.drawingThread.stopThread();
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
