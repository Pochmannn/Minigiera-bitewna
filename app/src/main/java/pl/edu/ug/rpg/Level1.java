package pl.edu.ug.rpg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Level1 extends AppCompatActivity {
    int liczbaZwyciestw;
    int hpTwoje;
    MediaPlayer kill,obrazenia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        liczbaZwyciestw = getIntent().getIntExtra("zabitychStworow",0);
        hpTwoje = getIntent().getIntExtra("hpTwoje",100);

        hpTwojeProgressBar=(ProgressBar)findViewById(R.id.hpTwojeView);
        hpTwojeProgressBar.setProgress(hpTwoje);
        kill = MediaPlayer.create(this,R.raw.kill);
        obrazenia = MediaPlayer.create(this,R.raw.obrazenia);
    }

    ProgressBar hpPrzeciwnikaProgressBar;
    TextView uderzenieTextView;

    ProgressBar hpTwojeProgressBar;



    int hpPrzeciwnika = 100+(500*liczbaZwyciestw);



    public void poddajMethod(View view) {

        Toast.makeText(this, "Poddałeś się!, " + "zabitych stworów: " + liczbaZwyciestw , Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }

    public void uderzMethod(View view) {

        Random random = new Random();
        int ciosOtrzymany = random.nextInt(10) + 1;

        Random random2 = new Random();
        int ciosZadany = random2.nextInt(20) + 1;

        hpPrzeciwnika =  hpPrzeciwnika-ciosZadany;
        hpTwoje =  hpTwoje-ciosOtrzymany;

        uderzenieTextView=(TextView)findViewById(R.id.uderzenieText);
        uderzenieTextView.setText("Twoje ostatnie uderzenie wyniosło: " + Integer.toString(ciosZadany));

        uderzenieTextView=(TextView)findViewById(R.id.uderzenieText2);
        uderzenieTextView.setText("Dostałeś za: " + Integer.toString(ciosOtrzymany));

        hpPrzeciwnikaProgressBar=(ProgressBar)findViewById(R.id.hpPrzeciwnikaView);
        hpPrzeciwnikaProgressBar.setProgress(hpPrzeciwnika);


        hpTwojeProgressBar.setProgress(hpTwoje);
        obrazenia.start();
        if (hpPrzeciwnika <=0)
        {
            zwyciestwoMethod();

        }
        if (hpTwoje <=0)
        {
            Toast.makeText(this, "Przegrałeś!, "+ "zabitych stworów: " + liczbaZwyciestw , Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }

    public void zwyciestwoMethod()
    {
        Toast.makeText(this, "Jedna bestia mniej", Toast.LENGTH_SHORT).show();
        liczbaZwyciestw+=1;
        hpTwoje+=30;

        kill.start();
        Intent intent = new Intent(getApplicationContext(),Level1.class);
        intent.putExtra("zabitychStworow",liczbaZwyciestw);
        intent.putExtra("hpTwoje",hpTwoje);
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

}