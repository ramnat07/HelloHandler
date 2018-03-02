package ehb.be.hellohandler.activities;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import ehb.be.hellohandler.R;
import ehb.be.hellohandler.util.MyHandler;
// 2. idem zaprashivat to chto my sdelali v klasse handler
public class MainActivity extends AppCompatActivity {

    //UI
    private ProgressBar prBar; //2.a.
    private Button startButton;

    private TextView tvDone; //3.a

    //util
    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prBar = findViewById(R.id.pb_bar);//2.b.
        startButton = findViewById(R.id.btn_start);
        tvDone = findViewById(R.id.tv_done);

        myHandler = new MyHandler(prBar, tvDone);// 3.d. hier nog , tvDone-toevoegen

        startButton.setOnClickListener(new View.OnClickListener() {//2.c. aanroeppen van onClickListenaar
            @Override
            public void onClick(View v) {
                startProgress();

            }
        });


    }
    private void startProgress(){ // 2.d. een methode Runneable aanroepen om ote weten op hoeveel procent staat onze progressBar

        Thread backGroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<=100; i +=5){
                    Message message = new Message();
                            message.arg1 = i;
                            myHandler.sendMessage(message);

                            //aanpassingen aan de UI mogen niet vanuit afzonderlijke thread, geeft fout
                            // tvDone.setText("Done"); //3.b.
                    //om na te bootsen dat het lang duurt, chtoby dolshe prBar zagrugalas,chtoby my mogli uvidet chto process idet
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        backGroundThread.start();
    }
}
