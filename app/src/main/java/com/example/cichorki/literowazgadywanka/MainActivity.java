package com.example.cichorki.literowazgadywanka;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import static android.widget.Toast.makeText;
import static com.example.cichorki.literowazgadywanka.R.id.wylosowane;


public class MainActivity extends AppCompatActivity {


    private String temp;
    private final String fraza = "Liczba punktów: ";
    private int liczba;
    private int id_obrazka;
    private static int total=0;

    private String []wyrazy={"urodą", "legenda", "rycerz", "liść","pociąg", "będę", "kompozytor", "komponować", "wziąłem", "chleb", "lubię", "Tadeusz", "jabłko", "węzeł", "zawiózł", "dwór", "samochód", "auto", "sąsiadka", "kot", "przedstawia", "dyrygent", "kość", "gość", "gumka", "dość", "wiedźma", "północna"};

    final Handler handler = new Handler();
    Runnable timer=new Runnable() {
        @Override
        public void run() {
            wylosowane_slowo.setText("Tu pojawi się wylosowane słowo");
        }
    };
    private TextView wylosowane_slowo;
    private EditText edytor;
    public TextView punkty;
    private ImageView obrazek;

    public void losuj_wyraz(View v) {
        Random losowanie=new Random();
        liczba=losowanie.nextInt(27);
        wylosowane_slowo.setText(wyrazy[liczba]);
        temp = wylosowane_slowo.toString();
        handler.postDelayed(timer,5000);
    }


    public void gotowe(View v) {
        boolean isEmpty=false;
        String slowo = edytor.getText().toString();
        if (TextUtils.isEmpty(slowo)){
            edytor.setError(getString(R.string.pusty_wpis));
            isEmpty=true;
        }
if(!isEmpty) {
    if (slowo.equals(wyrazy[liczba])) {
        id_obrazka = R.drawable.buzia;
        obrazek.setImageResource(id_obrazka);
        Toast toast = Toast.makeText(this, "Bardzo dobrze! Odpowiedziałeś prawidłowo, otrzymujesz 10 punktów", Toast.LENGTH_LONG);
        toast.show();
        Log.d("INFO", "prawidłowa odpowiedź: " + slowo);
        total += 10;
        punkty.setText((fraza + total));
        edytor.setText("");
    } else {
        id_obrazka = R.drawable.buzia2;
        obrazek.setImageResource(id_obrazka);
        Toast toast = Toast.makeText(this, "Żle! Musisz się bardziej postarać, odejmuję 5 punktów", Toast.LENGTH_LONG);
        toast.show();
        Log.d("INFO", "prawidłowa odpowiedź: " + slowo);
        total -= 5;
        punkty.setText((fraza + total));
        edytor.setText("");
    }
}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            total=savedInstanceState.getInt("total");
            id_obrazka=savedInstanceState.getInt("id_obrazka");
        }
        wylosowane_slowo =(TextView)findViewById(R.id.wylosowane);
        edytor = (EditText) findViewById(R.id.edytor);
        punkty = (TextView) findViewById(R.id.punkty);
        obrazek =(ImageView) findViewById(R.id.obrazek);
        punkty.setText(fraza+total);
        obrazek.setImageResource(id_obrazka);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int click_id=item.getItemId();
        if(click_id == R.id.rezygnuje){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("total",total);
        savedInstanceState.putInt("id_obrazka",id_obrazka);
    }
}
