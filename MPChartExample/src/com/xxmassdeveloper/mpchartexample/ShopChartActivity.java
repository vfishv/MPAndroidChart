
package com.xxmassdeveloper.mpchartexample;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.xxmassdeveloper.mpchartexample.notimportant.DemoBase;

import java.util.ArrayList;

public class ShopChartActivity extends DemoBase {

    private BarChart mChart;

    LineChart chartLine;
    PieChart chartPie;

    int color1 = Color.parseColor("#f56c6e");
    int colorTextDefault = Color.parseColor("#bfb4b4");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_shop_chart);

        chartLine = (LineChart) findViewById(R.id.chartLine);
        chartPie = (PieChart) findViewById(R.id.chartPie);

        chartPie.setUsePercentValues(true);
        chartPie.getDescription().setEnabled(false);
        chartPie.setExtraOffsets(5, 10, 5, 5);

        chartPie.setDragDecelerationFrictionCoef(0.95f);

        chartPie.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

        Legend l = chartPie.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);

        mChart = (BarChart) findViewById(R.id.chart1);

        mChart.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextColor(colorTextDefault);

        mChart.getAxisLeft().setDrawGridLines(true);
        
        mChart.getLegend().setEnabled(false);

        mChart.setScaleEnabled(false);

        mChart.getAxisRight().setEnabled(false);//右边坐标轴去掉
        //mChart.getAxisLeft().setEnabled(false);

        setData(12);
        mChart.setFitBars(true);

        setPieData(6, 100);
        chartPie.setDrawHoleEnabled(false);




        chartLine.setData(generateDataLine(6));
        YAxis axisLeft = chartLine.getAxisLeft();
        axisLeft.setDrawGridLines(true);
        axisLeft.setTextColor(colorTextDefault);
        chartLine.setPinchZoom(false);
        chartLine.setDoubleTapToZoomEnabled(false);
        //chartLine.setClickable(false);
        chartLine.setScaleEnabled(false);
        chartLine.getLegend().setEnabled(false);

        chartLine.getAxisRight().setEnabled(false);//右边坐标轴去掉
        //chartLine.getAxisLeft().setEnabled(false);

        XAxis xAxisLine = chartLine.getXAxis();
        xAxisLine.setPosition(XAxisPosition.BOTTOM);
        xAxisLine.setDrawGridLines(false);
        xAxisLine.setTextColor(colorTextDefault);

        chartLine.invalidate(); // refresh
        chartLine.animateY(800);
    }
    
    private void setData(int count) {
        
        ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * count * 100) + 600;
            yVals.add(new BarEntry(i, (int) val));
        }

        BarDataSet set = new BarDataSet(yVals, "Data Set");
//        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set.setColor(color1);
        set.setDrawValues(false);

        BarData data = new BarData(set);
        data.setValueTextColor(colorTextDefault);

        mChart.setData(data);
        mChart.invalidate();
        mChart.animateY(800);
    }



    private LineData generateDataLine(int cnt) {

        ArrayList<Entry> e1 = new ArrayList<Entry>();

        final int dataCnt = 6;
        for (int i = 0; i < dataCnt; i++) {
            e1.add(new Entry(i, (int) (Math.random() * 65) + 3500));
        }

        String label = "New DataSet " + cnt + ", (1)";
        label = null;
        LineDataSet d1 = new LineDataSet(e1, label);
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(true);
        d1.setValueTextColor(colorTextDefault);

        d1.setColor(Color.parseColor("#e66760"));

        d1.setMode(LineDataSet.Mode.CUBIC_BEZIER);//LineDataSet.Mode.LINEAR 曲线
        d1.setDrawCircles(false);//值圆圈

        /*
        ArrayList<Entry> e2 = new ArrayList<Entry>();

        for (int i = 0; i < dataCnt; i++) {
            e2.add(new Entry(i, e1.get(i).getY() - 30));
        }

        LineDataSet d2 = new LineDataSet(e2, "New DataSet " + cnt + ", (2)");
        d2.setLineWidth(2.5f);
        d2.setCircleRadius(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);
        */

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
        //sets.add(d2);

        LineData cd = new LineData(sets);
        cd.setValueTextColor(colorTextDefault);
        return cd;
    }

    private void setPieData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
                    mParties[i % mParties.length],
                    getResources().getDrawable(R.drawable.star)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        colors.clear();
        colors.add(Color.parseColor("#f7f1a9"));
        colors.add(Color.parseColor("#e66760"));
        colors.add(Color.parseColor("#43b6bd"));
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        chartPie.setData(data);

        // undo all highlights
        chartPie.highlightValues(null);

        chartPie.setCenterTextColor(colorTextDefault);

        chartPie.invalidate();
    }
}
