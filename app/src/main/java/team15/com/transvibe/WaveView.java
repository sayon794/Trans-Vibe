package team15.com.transvibe;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class WaveView extends SurfaceView implements SurfaceHolder.Callback {

    Context context;
    SurfaceHolder surfaceHolder;
    DrawingThread drawingThread;

    public WaveView(Context context) {
        super(context);
        this.context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        drawingThread = new DrawingThread(this,context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            drawingThread.start();
        } catch(Exception e) {
            restartThread();        //in case thread creation fails
        }

    }

    private void restartThread() {
        drawingThread.stopThread();
        drawingThread = null;
        drawingThread = new DrawingThread(this,context);
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawingThread.stopThread();         //finish the thread
    }
}
