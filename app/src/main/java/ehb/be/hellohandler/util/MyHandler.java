package ehb.be.hellohandler.util;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by mobapp07 on 2/03/2018.
 */
// 1.Nadat wij onze scherm ontwerp hadden maken we een java classe Handler en dit is dan super klassa van Handler
public class MyHandler extends Handler {//gaat berichten krijgen van tread op de achtergrond

    private ProgressBar pbBar;

    private TextView tvDone; //3.c.


    public MyHandler(ProgressBar pbBar, TextView tvDone) { //3.d. oude constructor deleten en niuwe aanroepen met de nieuwe variabele
        this.pbBar = pbBar;
        this.tvDone = tvDone;
    }

    @Override// indien een nieuwwe bericht, wat moet er uitgevoerd?
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

      int progress = msg.arg1;//zo gaan we onze int doorsturen om gegevens van onze pb te halen

        tvDone.setText(progress+"%"); // chtoby procenty pokazyvala
        if(progress >= 100) { //3.c. // of we kunnen schrijven in plaats 100 schrijven pbBar.getMax()
            tvDone.setText("Done");
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){//animatie op progress kan pas sinds android Nougat, je kan nagelang de versie van Android andere code laten uitvoeren
            pbBar.setProgress(progress, true);
        }
        else {
            pbBar.setProgress(progress);// vse chto v 1. my postroili -eto nash Handler
        }

    }
}
