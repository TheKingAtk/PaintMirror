package com.theking.paintmirror;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.crypto.spec.OAEPParameterSpec;


public class PaintView extends View {
    int p=1,w,h;
    Bitmap b;
    Paint paint=new Paint(),ePaint=new Paint(),wPaint=new Paint();
    Path path=new Path(),ePath=new Path();
    ArrayList<Path> paths = new ArrayList<Path>();
    int id=0,cl=0;
    PointSender pS;
    public PaintView(Context context) {
        super(context);
    }
    public void setPointSender(PointSender pS,int no) {
        this.pS=pS;
        id=no;
    }
    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(7);
        wPaint.setColor(Color.BLACK);
        wPaint.setStyle(Paint.Style.STROKE);
        wPaint.setStrokeWidth(7);
        ePaint.setColor(Color.WHITE);
        ePaint.setStyle(Paint.Style.STROKE);
        ePaint.setStrokeWidth(100);
        w=widthMeasureSpec;
        h=heightMeasureSpec;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        if(cl==1) {
            path.reset();
            ePath.reset();
            canvas.drawPath(path,wPaint);
            canvas.clipPath(path, Region.Op.INTERSECT);
            canvas.drawPath(ePath,ePaint);
            canvas.clipPath(ePath, Region.Op.INTERSECT);
            paths.clear();
            cl=0;
        }
        else {
            int i=0;

            if(!path.isEmpty()) {


                    canvas.drawPath(path,wPaint);
                    canvas.clipPath(path, Region.Op.INTERSECT);


            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(p==1) {
                    path.moveTo(x,y);
                }
                else ePath.moveTo(x,y);
                pS.sendStart(x,y,id);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                if(p==1) {
                    path.lineTo(x,y);
                }
                else ePath.lineTo(x,y);
                pS.sendMove(x,y,id);
                invalidate();
                break;
        }
        return true;
    }
    public void pathStart(float x,float y) {
        if(p==1) {
            path.moveTo(x,y);
        }
        else ePath.moveTo(x,y);
        invalidate();
    }
    public void pathLineTo(float x,float y) {
        if(p==1) {
            path.lineTo(x,y);
        }
        else ePath.lineTo(x,y);
        invalidate();
    }
    public void clear(){
        cl=1;
        invalidate();
    }

    interface PointSender{
        void sendStart(float x,float y,int pos);
        void sendMove(float x,float y,int pos);
    }
}
