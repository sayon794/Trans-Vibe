package team15.com.transvibe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickedStarter(View view) {
        Intent intent = new Intent(MainActivity.this, WaveActivity.class);
        startActivity(intent);
    }
}
