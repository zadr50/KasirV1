package com.talagasoft.kasir;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by compaq on 02/29/2016.
 */
public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread logoTimer = new Thread() {
            public void run(){
                try {
                    int logoTimer = 0;
                    while (logoTimer < 3000) {
                        sleep(100);
                        logoTimer = logoTimer + 100;
                    };
                    startActivity(new Intent("com.talagasoft.kasir.MenuUtama"));
                }
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                finally{
                    finish();
                }
            }
        };

        logoTimer.start();
    }

}
