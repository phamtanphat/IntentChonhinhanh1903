package khoapham.ptp.phamtanphat.intentchonhinhanh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

public class DanhsachthucungActivity extends AppCompatActivity {

    TableLayout tableLayout;
    String [] mangtenthucung;
    int vitri = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachthucung);

        tableLayout = findViewById(R.id.tablelayout);
        mangtenthucung = getResources().getStringArray(R.array.arrayanimal);
        Collections.shuffle(Arrays.asList(mangtenthucung));
        // số dòng 6 (Tablerow)
        // Số cột 3(Imageview)
        int sodong = 6;
        int socot = 3;
        if (mangtenthucung.length % 3 == 2 || mangtenthucung.length % 3 == 1 || mangtenthucung.length % 3 == 0){
            for (int i = 1 ; i <= Math.ceil((float)mangtenthucung.length /3) ; i++){
                TableRow tableRow = new TableRow(this);
                for (int y = 1 ; y <= socot ;y++){
                    ImageView imageView = new ImageView(this);
                    int vitri = socot * (i - 1) + y -1;
                    if (mangtenthucung.length == vitri){
                        imageView.setImageResource(android.R.color.transparent);
                        imageView.setVisibility(View.GONE);
                    }else{
                        final int idHinh = getResources().getIdentifier(mangtenthucung[vitri],"drawable",getPackageName());
                        imageView.setImageResource(idHinh);
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(DanhsachthucungActivity.this,MainActivity.class);
                                intent.putExtra("idhinhchon",idHinh);
                                setResult(RESULT_OK,intent);
                                finish();
                            }
                        });

                    }
                    tableRow.addView(imageView);

                }
                tableLayout.addView(tableRow);
            }
        }

    }
}
