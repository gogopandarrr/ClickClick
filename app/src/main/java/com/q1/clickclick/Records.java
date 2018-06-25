package com.q1.clickclick;

import android.graphics.drawable.Drawable;

/**
 * Created by alfo06-07 on 2018-03-09.
 */

public class Records {


    private int icon;
    private int rank;
    private long timeRecord;


    public Records(long timeRecord) {
        this.timeRecord = timeRecord;
    }

    public int getIcon() {

        icon = R.drawable.icon_basic;

        return icon;
    }

    public int getRank() {

        return rank;
    }

    public long getTimeRecord() {
        return timeRecord;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTimeRecord(long timeRecord) {
        this.timeRecord = timeRecord;

    }
}
