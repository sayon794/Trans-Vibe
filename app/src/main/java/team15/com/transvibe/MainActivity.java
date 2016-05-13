package team15.com.transvibe;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button n[]=new Button [4];
        n[0]=(Button) findViewById(R.id.button1);
        n[1]=(Button) findViewById(R.id.button2);
        n[2]=(Button) findViewById(R.id.button3);
        n[3]=(Button) findViewById(R.id.button4);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "Xenotron.ttf");
        for(int i=0;i<4;i++)
            n[i].setTypeface(typeface);

    }

    public void clickedStarter(View view) {
        Intent intent = new Intent(MainActivity.this, WaveActivity.class);
        startActivity(intent);
    }
    public void onClickInput(View view){
        Intent intent = new Intent(this, input.class);
        startActivity(intent);
    }
    public void onClickCredits(View view){
        Intent intent = new Intent(this, credits.class);
        startActivity(intent);
    }
}
