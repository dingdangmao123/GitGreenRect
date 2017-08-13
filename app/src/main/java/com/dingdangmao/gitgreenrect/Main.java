package com.dingdangmao.gitgreenrect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    GitGreenRect git1;
    GitGreenRect git2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        git1=(GitGreenRect)findViewById(R.id.git1);
        git1.addListener(new onListener() {
            @Override
            public void onDown(int row, int col) {
                Toast.makeText(Main.this,"onDown "+L.o(row)+" "+L.o(col),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUp(int row, int col) {
                Toast.makeText(Main.this,"onUp "+L.o(row)+" "+L.o(col),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDiff(int row1, int col1, int row2, int col2) {
                Toast.makeText(Main.this,"onDiff",Toast.LENGTH_SHORT).show();
            }
        });
        git1.addData(new int[][]{
                {1,2,0,4,2,2,3},
                {0,2,2,4,3,7,3},
                {0,4,3,4,3,0,0},
                {2,1,1,0,5,0,7}});

        git2=(GitGreenRect)findViewById(R.id.git2);
        git2.addColor(new String[]{"#ffffcc","#ffcc99","#99ccff","#99cc99","#009966","#336699"});
        git2.addData(new int[][]{
                {1,2,0,4,2,2,3,17,5,6,4},
                {0,2,2,4,3,7,3,1,3,5,6,8},
                {0,4,3,4,3,0,0,10,3},
                {2,1,1,0,5,0,7,5,3},
                {2,1,10,0,6,0,7,5,3}
        });

    }
}
