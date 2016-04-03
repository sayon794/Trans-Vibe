package team15.com.transvibe;

import android.graphics.Bitmap;

public class BitmapOptimizer {  //doesn't actually optimize. well, not that i can see
    private Bitmap image;
    private int width,height;
    private int pixels[];

    public BitmapOptimizer(Bitmap source) {
        image = source;
        width = image.getWidth();
        height = image.getHeight();
        pixels = new int[width*height];
        image.getPixels(pixels,0,width,0,0,width,height);
    }

    public int getPixel(int x,int y) {
        return pixels[(x+y*width)];
    }

    public void setPixel(int x,int y,int color) {
        pixels[x+y*width]=color;
    }

    public void setPixelScaled(int x,int y,int color,int scale) {   //this is nice though
        int x1,y1,i,j;
        for(i=0;i<scale;i++) {
            for(j=0;j<scale;j++) {
                x1 = x+i;
                y1 = y+j;
                if(x1<width&&y1<height)
                    pixels[x1+y1*width] = color;
            }
        }
    }

    public Bitmap getBitmap() {
        image.setPixels(pixels,0,width,0,0,width,height);
        return image;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }
}
