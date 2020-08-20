package com.theking.paintmirror;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Paint2Fragment extends Fragment {

    public Paint2Fragment() {
        // Required empty public constructor
    }
    private PaintView p2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_paint2, container, false);
        p2=layout.findViewById(R.id.paint2);
        p2.setPointSender((PaintView.PointSender)layout.getContext(),2);
        return layout;
    }
    public void sendStart(float x,float y){
        p2.pathStart(x,y);
    }
    public void sendMove(float x,float y){
        p2.pathLineTo(x,y);
    }
    public void clearCanvas() {
        p2.clear();
    }

}
