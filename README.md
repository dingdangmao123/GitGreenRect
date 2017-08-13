# GitGreenRect
a android view like github contribution view

# snap
![GitGreenRect](/snap/demo.PNG)

# introduction

* `size` - the size of rectangle
* `space` - the space between rectangles
* `cStart` - left color
* `cEnd` - right color




# demo
```xml
    <com.dingdangmao.gitgreenrect.GitGreenRect
        android:id="@+id/git1"
        app:size="30dp"
        app:space="5dp"
        android:background="#FFFFFF"
        android:layout_width="match_parent"
        android:layout_height="200dp" />
```


```java
        git1=(GitGreenRect)findViewById(R.id.git1);
        /*
        set the listener of the single rectangle
        */
        git1.addListener(new onListener() {
            @Override
            public void onDown(int row, int col) {
                Toast.makeText(Main.this,"onDown "+L.o(row)+" "+L.o(col),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUp(int row, int col) {
                Toast.makeText(Main.this,"onUp "+L.o(row)+" "+L.o(col),Toast.LENGTH_SHORT).show();
            }
            /*
            if two single are different
            */
            @Override
            public void onDiff(int row1, int col1, int row2, int col2) { if 
                Toast.makeText(Main.this,"onDiff",Toast.LENGTH_SHORT).show();
            }
        });
        /*
        add the color data
        */
        git1.addData(new int[][]{ 
                {1,2,0,4,2,2,3},
                {0,2,2,4,3,7,3},
                {0,4,3,4,3,0,0},
                {2,1,1,0,5,0,7}});

        git2=(GitGreenRect)findViewById(R.id.git2);
        /*
        set the color array you will use
        */
        git2.addColor(new String[]{"#ffffcc","#ffcc99","#99ccff","#99cc99","#009966","#336699"});
        
        git2.addData(new int[][]{
                {1,2,0,4,2,2,3,17,5},
                {0,2,2,4,3,7,3,1,3},
                {0,4,3,4,3,0,0,10,3},
                {2,1,1,0,5,0,7,5,3},
                {2,1,10,0,6,0,7,5,3}
        });



```
