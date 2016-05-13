package team15.com.transvibe;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.content.Intent;
import android.widget.TextView;

public class WaveActivity extends AppCompatActivity {

    WaveView waveView;
    Double mass,length,tension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);
        Intent intent = getIntent();
        mass = intent.getDoubleExtra("mass", 0.049);
        length = intent.getDoubleExtra("length", 250);
        tension = intent.getDoubleExtra("tension", 49);
        initThread();

    }

    private void initThread(){
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.eidalagbo);
        waveView = new WaveView(this,mass,length,tension);
        layout.addView(waveView);
        waveView.setZOrderMediaOverlay(true);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
            waveView.drawingThread.setSpeedDown();
        }
        else if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)){
            waveView.drawingThread.setSpeedUp();
        }
        else if((keyCode == KeyEvent.KEYCODE_BACK)){
            onBackPressed();
        }
        return true;
    }

}
