package team15.com.transvibe;

/**
 * Created by maliha on 5/12/16.
 */
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class credits extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        TextView tv[]=new TextView[5];
        tv[0]= (TextView)findViewById(R.id.sayon);
        tv[1]= (TextView)findViewById(R.id.maliha);
        tv[2]= (TextView)findViewById(R.id.tahsin);
        tv[3]= (TextView)findViewById(R.id.pritom);
        tv[4]= (TextView)findViewById(R.id.team15);

        Animation translatebu= AnimationUtils.loadAnimation(this, R.anim.bounce);
        for(int i=0;i<4;i++)
            tv[i].startAnimation(translatebu);





    }

}
