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

    //don't call drawingThread.start() here. then it won't work if user home buttons out and comes back in.
    public WaveView(Context context, double mass) {
        super(context);
        this.context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        //handler.post(new DrawingThread(this,context));
        drawingThread = new DrawingThread(this,context);

    }


    //Change size of surfaceView here
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        surfaceHolder.setFixedSize(this.getWidth(),this.getHeight()/2);
    }

    //start thread here
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
