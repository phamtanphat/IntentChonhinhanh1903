package khoapham.ptp.phamtanphat.intentchonhinhanh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imgHinhgoc,imgHinhchon;
    String [] mangtenhinh;
    int idHinhgoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgHinhchon = findViewById(R.id.imageviewHinhchon);
        imgHinhgoc = findViewById(R.id.imageviewHinhgoc);

        mangtenhinh = getResources().getStringArray(R.array.arrayanimal);
//        Random random = new Random();
//        int index = random.nextInt(mangtenhinh.length);
        Collections.shuffle(Arrays.asList(mangtenhinh));
        idHinhgoc = getResources().getIdentifier(mangtenhinh[0],"drawable",getPackageName());
        imgHinhgoc.setImageResource(idHinhgoc);
        imgHinhchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
