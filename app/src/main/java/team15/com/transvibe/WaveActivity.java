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

public class WaveActivity extends AppCompatActivity {

    WaveView waveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wave);
        initThread();
        //seekbarFixSpeed();
        //RelativeLayout layout = new RelativeLayout(this);
        //layout.addView(new SurfaceView(this));
        //setContentView(layout); //needs to change, add waveview as a widget to an activity instead
        //waveView = new WaveView(this);
        //layout.addView(waveView);
    }

    private void initThread(){
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.eidalagbo);
        waveView = new WaveView(this);
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

    /*public void seekbarFixSpeed() {
        final SeekBar seekbar = (SeekBar) findViewById(R.id.speedbar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                *//*RelativeLayout layout = (RelativeLayout) findViewById(R.id.eidalagbo);
                int position = seekBar.getProgress();
                layout.removeView(waveView);
                waveView.drawingThread.stopThread();
                initThread(position);
                //seekbar.setVisibility(View.VISIBLE);*//*
                int position = seekBar.getProgress();
                waveView.drawingThread.setSpeed(position);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }*/
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
