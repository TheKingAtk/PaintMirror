package com.theking.paintmirror;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Paint1Fragment extends Fragment {
    PaintView p1;
    public Paint1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_paint1, container, false);
        p1=layout.findViewById(R.id.paint1);
        p1.setPointSender((PaintView.PointSender) layout.getContext(),1);
        return layout;
    }
    public void sendStart(float x,float y){
        p1.pathStart(x,y);
    }
    public void sendMove(float x,float y){
        p1.pathLineTo(x,y);
    }
    public void clearCanvas() {
        p1.clear();
    }

}
