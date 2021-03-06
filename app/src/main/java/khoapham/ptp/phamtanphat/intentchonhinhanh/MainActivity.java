package khoapham.ptp.phamtanphat.intentchonhinhanh;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

    int diemdangchoi = 0;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgHinhchon = findViewById(R.id.imageviewHinhchon);
        imgHinhgoc = findViewById(R.id.imageviewHinhgoc);
        txtDiem = findViewById(R.id.textviewdiem);

        sharedPreferences = getSharedPreferences("quanlydiem",MODE_PRIVATE);
//        try {
//            String chuoi = sharedPreferences.getString("chuoi",null);
//            if (chuoi == null) throw new Exception("loi");
//        }catch (Exception e){
//            Log.d("CCC",e.getMessage());
//        }

//        editor = sharedPreferences.edit();
//        editor.remove("chuoi");
//        editor.commit();
        editor = sharedPreferences.edit();
        diemdangchoi = sharedPreferences.getInt("diemso",0);
        txtDiem.setText(diemdangchoi + "");

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
                            diemdangchoi += 10;
                            editor.putInt("diemso",diemdangchoi);
                            getRandomImage();
                            imgHinhchon.setEnabled(true);
                            txtDiem.setText(diemdangchoi + "");
                            editor.commit();
                        }
                    },2000);

                }else{
                    diemdangchoi -= 10;
                    Log.d("BBB",diemdangchoi + "");
                    if (diemdangchoi >= 0){
                        editor.putInt("diemso",diemdangchoi);
                        txtDiem.setText(diemdangchoi + "");
                        editor.commit();
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Ban muon choi lai hay khong?");
                        builder.setCancelable(false);
                        builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                diemdangchoi = 0;
                                editor.putInt("diemso",diemdangchoi);
                                txtDiem.setText(diemdangchoi + "");
                                editor.commit();
                            }
                        });
                        builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editor.clear();
                                finish();
                            }
                        });
                        builder.show();
                    }
                    Toast.makeText(this, "Sai roi!!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_refresh_image,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_setting){
            diemdangchoi -= 10;
            if (diemdangchoi >= 0){
                getRandomImage();
                editor.putInt("diemso",diemdangchoi);
                txtDiem.setText(diemdangchoi + "");
                editor.commit();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Ban muon choi lai hay khong?");
                builder.setCancelable(false);
                builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        diemdangchoi = 0;
                        editor.putInt("diemso",diemdangchoi);
                        txtDiem.setText(diemdangchoi + "");
                        editor.commit();
                    }
                });
                builder.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.clear();
                        finish();
                    }
                });
                builder.show();
            }
            Toast.makeText(this, "Sai roi!!", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    private void getRandomImage(){
        Collections.shuffle(Arrays.asList(mangtenhinh));
        idHinhgoc = getResources().getIdentifier(mangtenhinh[0],"drawable",getPackageName());
        imgHinhgoc.setImageResource(idHinhgoc);
    }
}
