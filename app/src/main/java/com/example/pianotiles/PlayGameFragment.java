package com.example.pianotiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

public class PlayGameFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {
    protected ImageView iv_00;
    protected Bitmap mBitmap;
    //  protected GestureDetector mDetector;
// test
    protected Button start;
    protected TextView score;
    protected TextView highScore;
    protected TextView value_score;
    protected TextView value_highScore;
    protected Canvas mCanvas;
    protected boolean status;
    protected Paint strokePaint;

    protected int nilai;
    protected int currentNilai;
    public FragmentListener listener;
    public PlayGameFragment(){

    }

    public static PlayGameFragment newInstance(String title){
        PlayGameFragment fragment = new PlayGameFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_play_game,container,false);
        this.nilai=100;
        this.status=false;
        this.value_score=view.findViewById(R.id.value_score);
        this.value_highScore=view.findViewById(R.id.value_highScore);
        this.iv_00 = view.findViewById(R.id.iv_00);
        this.start = view.findViewById(R.id.start);
        this.start.setOnClickListener(this);
        this.score =(TextView) view.findViewById(R.id.tv_score);
        this.highScore =(TextView) view.findViewById(R.id.tv_high_score);
        //this.iv_00.setClickable(true);
        this.iv_00.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof FragmentListener){
            this.listener = (FragmentListener)context;
        }else{
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }

    @Override
    public void onClick(View v) {
        if(v==this.start)
        {
            this.start.setVisibility(v.GONE);
            status=true;
        }
        if(this.iv_00==v && this.status==true)
        {
            Log.d("Coba", "WOy");

            this.currentNilai+=this.nilai;
            this.value_score.setText(""+this.currentNilai);
            this.value_highScore.setText(""+this.currentNilai);
            //this.score.setText("Score: 100"+this.score);

        }
    }

    public void initiateCanvas()
    {
        this.mBitmap = Bitmap.createBitmap(mCanvas.getWidth(),mCanvas.getHeight(), Bitmap.Config.ARGB_8888);

        this.iv_00.setImageBitmap(this.mBitmap);

        this.mCanvas = new Canvas(this.mBitmap);

        this.resetCanvas();

        // this.iv_00.setOnTouchListener(this);
    }

    public void resetCanvas(){
        // 4. Draw canvas background
        int mColorBackground = ResourcesCompat.getColor(getResources(),R.color.white,null);
        int mColorBlack = ResourcesCompat.getColor(getResources(),R.color.black,null);
        this.mCanvas.drawColor(mColorBackground);
        // 5. force draw
        this.iv_00.invalidate();
        // 6. reset stroke width + color
        this.strokePaint.setStyle(Paint.Style.STROKE);
        this.changeStrokeColor(mColorBlack);
        this.strokePaint.setStrokeWidth(10);
    }

    private void changeStrokeColor(int color) {
        //change stroke color using parameter (color resource id)
        this.strokePaint.setColor(color);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
