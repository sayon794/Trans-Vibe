package team15.com.transvibe;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.content.Context;

public class input extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tv[]= new TextView [3];
        tv[0]=(TextView)findViewById(R.id.id1);
        tv[1]=(TextView)findViewById(R.id.id2);
        tv[2]=(TextView)findViewById(R.id.id3);
        Typeface face=Typeface.createFromAsset(getAssets(),"Xenotron.ttf");
        for(int i=0;i<3;i++)
            tv[i].setTypeface(face);
        Button b=(Button)findViewById(R.id.submit);
        b.setTypeface(face);

    }
    public void onClickSubmit(View view){

        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        Intent intent = new Intent(this, WaveActivity.class);
        EditText mass = (EditText) findViewById(R.id.mass);
        String temp = mass.getText().toString();
        if(TextUtils.isEmpty(temp)) {
            mass.setError("Empty field!");
            return;
        }

        EditText length = (EditText) findViewById(R.id.length);
        temp = length.getText().toString();
        if(TextUtils.isEmpty(temp)) {
            length.setError("Empty field!");
            return;
        }
        EditText tension = (EditText) findViewById(R.id.tension);
        temp = tension.getText().toString();
        if(TextUtils.isEmpty(temp)) {
            tension.setError("Empty field!");
            return;
        }
        Double m=Double.parseDouble(mass.getText().toString());
        Double l=Double.parseDouble(length.getText().toString());
        Double t=Double.parseDouble(tension.getText().toString());
        intent.putExtra("mass", m);
        intent.putExtra("length", l);
        intent.putExtra("tension", t);
        startActivity(intent);
    }

}
