package team15.com.transvibe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class DrawingThread extends Thread {
//public class DrawingThread implements Runnable{
    private final int AMPCHANGEVAR = 10;        //will remove final when speed slider is implemented
    private Canvas canvas;
    private WaveView waveView;
    private Context context;
    private boolean threadFlag = false;
    Bitmap bgBitmap;
    BitmapOptimizer bgOptimizer;
    int displayX,displayY;                      //size of the phone's display
    double len,freq,T,mass;
    int amp,maxAmp;                             //maximum allowed amp
    double scaleratio;
    //the ratio in which the width of the display scales with the length
    //of the string. This is required because the code iterates horizontally through
    //the entire display of the phone. Now if the x-axis isn't scaled, it draws a
    //stupidly high number of waves in massive concentrations.
    double y,t;
    int change=AMPCHANGEVAR;
    Paint paint;

    public DrawingThread(WaveView waveView,Context context) {
        super();
        this.waveView = waveView;
        this.context = context;

        initialize();
    }

    private void initialize() {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        //may have to change things here when the bitmap won't take up the entire screen
        //gets the dimensions of the display.... duh
        Point displayDimension = new Point();
        defaultDisplay.getSize(displayDimension);
        displayX = displayDimension.x;
        displayY = displayDimension.y;

        len = 250;
        T = 49;
        mass = 0.049;           //hsc-r boi theke value marsi :D
        freq = 1/(2*(len/1000))*Math.sqrt(T/mass);
        maxAmp = 100;
        amp = 0;

        bgBitmap = Bitmap.createBitmap((int)len,displayY,Bitmap.Config.ARGB_8888);
        bgBitmap.eraseColor(Color.WHITE);
        bgBitmap = Bitmap.createScaledBitmap(bgBitmap,displayX,displayY,true);
        scaleratio = displayX/len;

        bgOptimizer = new BitmapOptimizer(bgBitmap);

        paint = new Paint();
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadFlag = true;
        while(threadFlag) {
            canvas = waveView.surfaceHolder.lockCanvas();
            try {
                synchronized(waveView.surfaceHolder) {
                    updateDisplay();
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if(canvas!=null) {
                    //System.err.println(canvas);
                    waveView.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            /*try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //lol, sleep
        }
    }


    public void stopThread() { threadFlag = false; }

    public void updateDisplay() {
        drawWaveOnBitmap(Color.WHITE); //Erase previous
        amp+=change;
        if(amp==maxAmp)
            change=-AMPCHANGEVAR;       //when at peak amplitude start reducing amp
        else if(amp==-maxAmp)
            change=AMPCHANGEVAR;
        drawWaveOnBitmap(Color.BLACK); //Draw current
        if(canvas!=null)
            canvas.drawBitmap(bgOptimizer.getBitmap(),0,0,null);
    }       //yep, that's it. standing wave, folks. done

    private void drawWaveOnBitmap(int color) {
        for(t=0;t<displayX;t+=.5*scaleratio) {
        //.5 is a placeholder value, wanted to ensure a decent number of pixels were drawn into
            y = amp/scaleratio * Math.sin(freq*t/scaleratio);
            bgOptimizer.setPixelScaled((int)t,(int)y+300,color,4);
            //the last param is the length of the square the pixel should represent
            //i hear you ask why use t/scale in the formula but not when drawing
            //because when drawing i'm drawing at the actual pixel
            //when i'm finding y, i need the point of the actual string which it represents
            //needs testing for a wider range of values to see if my assumption is actually correct
        }
    }

}
