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
    Double mass,length,tension;

    //don't call drawingThread.start() here. then it won't work if user home buttons out and comes back in.
<<<<<<< HEAD
    public WaveView(Context context, double mass) {
=======
    public WaveView(Context context,double m,double l, double t) {
>>>>>>> ddc9101362ba43d05b5af9ff3dde1bd2a90083fc
        super(context);
        this.context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        mass=m;
        length=l;
        tension=t;
        //handler.post(new DrawingThread(this,context));
        drawingThread = new DrawingThread(this,context,mass,length,tension);

    }

<<<<<<< HEAD
=======
    /*public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        //handler.post(new DrawingThread(this, context));
        drawingThread = new DrawingThread(this,context);

    }*/

    /*public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        //handler.post(new DrawingThread(this, context));
        drawingThread = new DrawingThread(this,context);

    }*/

>>>>>>> ddc9101362ba43d05b5af9ff3dde1bd2a90083fc

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
        drawingThread = new DrawingThread(this,context,mass,length,tension);
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
