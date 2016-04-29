package team15.com.transvibe;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class WaveView extends SurfaceView implements SurfaceHolder.Callback {

    Context context;
    SurfaceHolder surfaceHolder;
    DrawingThread drawingThread;
    final Handler handler = new Handler();
    public WaveView(Context context) {
        super(context);
        this.context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        //handler.post(new DrawingThread(this,context));
        drawingThread = new DrawingThread(this,context);
        drawingThread.start();

    }
    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        //handler.post(new DrawingThread(this, context));
        drawingThread = new DrawingThread(this,context);
        drawingThread.start();

    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        //handler.post(new DrawingThread(this, context));
        drawingThread = new DrawingThread(this,context);
        drawingThread.start();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            //drawingThread.start();
        } catch(Exception e) {
            //restartThread();        //in case thread creation fails
        }

    }

    private void restartThread() {
        drawingThread.stopThread();
        drawingThread = null;
        drawingThread = new DrawingThread(this,context);
        //drawingThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawingThread.stopThread();         //finish the thread
    }

}
