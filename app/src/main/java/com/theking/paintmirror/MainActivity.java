package com.theking.paintmirror;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements PaintView.PointSender {
    Paint2Fragment f2;
    Paint1Fragment f1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        f1 = new Paint1Fragment();
        f2 = new Paint2Fragment();
        FragmentTransaction ft1=getSupportFragmentManager().beginTransaction();
        ft1.add(R.id.con1,f1);
        FragmentTransaction ft2=getSupportFragmentManager().beginTransaction();
        ft2.add(R.id.con2,f2);
        ft1.commit();
        ft2.commit();


    }
    public void clear(View view){
        f2.clearCanvas();
        f1.clearCanvas();
    }
    @Override
    public void sendStart(float x, float y, int pos) {
        if(pos==1){
            f2.sendStart(x,y);
        }
        else f1.sendStart(x,y);
    }

    @Override
    public void sendMove(float x, float y, int pos) {
        if(pos==1){
            f2.sendMove(x,y);
        }
        else f1.sendMove(x,y);
    }
}
