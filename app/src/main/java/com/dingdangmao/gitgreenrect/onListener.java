package com.dingdangmao.gitgreenrect;

/**
 * Created by suxiaohui on 2017/8/13.
 */

public interface onListener {
    void onDown(int row,int col);
    void onUp(int row,int col);
    void onDiff(int row1,int col1,int row2,int col2);
}
