package khoapham.ptp.phamtanphat.intentchonhinhanh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imgHinhgoc,imgHinhchon;
    TextView txtDiem;
    String [] mangtenhinh;
    int idHinhgoc;
    int Request_Code_Hinhanh = 123;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgHinhchon = findViewById(R.id.imageviewHinhchon);
        imgHinhgoc = findViewById(R.id.imageviewHinhgoc);
        txtDiem = findViewById(R.id.textviewdiem);

        sharedPreferences = getSharedPreferences("quanlydiem",MODE_PRIVATE);
        try {
            String chuoi = sharedPreferences.getString("chuoi",null);
            if (chuoi == null) throw new Exception("loi");
        }catch (Exception e){
            Log.d("CCC",e.getMessage());
        }

//        editor = sharedPreferences.edit();
//        editor.remove("chuoi");
//        editor.commit();

        mangtenhinh = getResources().getStringArray(R.array.arrayanimal);
//        Random random = new Random();
//        int index = random.nextInt(mangtenhinh.length);
        getRandomImage();
        imgHinhchon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DanhsachthucungActivity.class);
                startActivityForResult(intent , Request_Code_Hinhanh);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == Request_Code_Hinhanh && resultCode == RESULT_OK && data != null){
            int idhinhchon = data.getIntExtra("idhinhchon",Integer.MIN_VALUE);
            if (idhinhchon != Integer.MIN_VALUE){
                imgHinhchon.setImageResource(idhinhchon);
                if (idhinhchon == idHinhgoc){
                    Toast.makeText(this, "Chinh xac!!", Toast.LENGTH_SHORT).show();
                    imgHinhchon.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getRandomImage();
                            imgHinhchon.setEnabled(true);
                        }
                    },2000);

                }else{
                    Toast.makeText(this, "Sai roi!!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void getRandomImage(){
        Collections.shuffle(Arrays.asList(mangtenhinh));
        idHinhgoc = getResources().getIdentifier(mangtenhinh[0],"drawable",getPackageName());
        imgHinhgoc.setImageResource(idHinhgoc);
    }
}
