package com.tuff.benoitlistener;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final float MIN_DISTANCE = 50;
    private float x1, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<BenoitModel> benoits = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BenoitModel basicBenoit = new BenoitModel();
            basicBenoit.name = "Benoit le " + i;
            benoits.add(basicBenoit);
        }
        final RecyclerView mRecycler = (RecyclerView) findViewById(R.id.mRecycler);
        RecyclerAdapter adapter = new RecyclerAdapter(benoits);
        mRecycler.setAdapter(adapter);

        mRecycler.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = e.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        x2 = e.getX();
                        float deltaX = x2 - x1;
                        if (Math.abs(deltaX) > MIN_DISTANCE) {
                            Toast.makeText(getApplicationContext(), "left2right swipe", Toast.LENGTH_SHORT).show();
                            View popo = rv.findChildViewUnder(e.getX(), e.getY());
                            int itemPosition = rv.getChildAdapterPosition(popo);
                            ((RecyclerAdapter) rv.getAdapter()).benoits.get(itemPosition).isMoved = true;
                            rv.getAdapter().notifyDataSetChanged();

                        } else {
                            // consider as something else - a screen tap for example
                        }
                        break;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {


            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}
