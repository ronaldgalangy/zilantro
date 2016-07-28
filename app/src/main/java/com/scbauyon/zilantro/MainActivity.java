package com.scbauyon.zilantro;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.scbauyon.zilantro.models.CustomValueFormatter;
import com.scbauyon.zilantro.models.Point;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.scatterChart)
    ScatterChart scatterChart;
    @Bind(R.id.seekBar1)
    SeekBar seekBar1;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.tvValue)
    TextView tvValue;

    private int NUMBER;
    private MenuItem item;

    private ArrayList<Point> pointObjects = new ArrayList<>();
    private ArrayList<Point> trivialVertices = new ArrayList<>();
    private ArrayList<Point> twoVertices = new ArrayList<>();
    private ArrayList<Point> multipleVertices = new ArrayList<>();
    private ArrayList<String> xVals = new ArrayList<String>();

    private final String TAG = MainActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initChart();
        initSeekBar();
    }

    private void initSeekBar() {
        seekBar1.setProgress(10);
        seekBar1.setOnSeekBarChangeListener(this);
    }

    private void initChart() {

        scatterChart.setDescription("Zilantro");

        Legend l = scatterChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);

        scatterChart.getAxisLeft().setDrawGridLines(true);
        scatterChart.getAxisRight().setDrawGridLines(true);
        scatterChart.getXAxis().setDrawGridLines(true);

        scatterChart.getXAxis().setDrawLabels(false);
        scatterChart.getAxisLeft().setDrawLabels(false);
        scatterChart.getAxisRight().setDrawLabels(false);

        scatterChart.setDrawGridBackground(true);
        scatterChart.setTouchEnabled(true);
        scatterChart.setPinchZoom(true);

        scatterChart.setOnChartValueSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.item = menu.getItem(0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {


            case R.id.action_settings:
            case R.id.action_default:
                this.item = item;
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                    refreshChart();
                }
                break;
            case R.id.action_save_image:
                scatterChart.saveToGallery("image-" + System.currentTimeMillis(), "scatterchart/", "", Bitmap.CompressFormat.PNG, 50);
//                if (scatterChart.saveToGallery("image-" + System.currentTimeMillis(), 50)) {
//                    Log.d(TAG, "image saved!");
//                } else {
//                    Log.d(TAG, "image not saved!");
//                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    //TODO OnSeekBarChangeListener

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        refreshChart();
    }

    private ScatterDataSet initTrivialVertices() {
        ArrayList<Entry> trivialEntries = new ArrayList<>();
        for (int i = 0; i < this.trivialVertices.size(); i++) {
            float val = (float) (Math.random() * NUMBER) + 3;
            Entry entry;
            if (this.item.getItemId() == R.id.action_default) {
                entry = new Entry(val, this.trivialVertices.get(i).getPoint(), this.trivialVertices.get(i));
            } else {
                entry = new Entry(val, i, this.trivialVertices.get(i));
            }
            trivialEntries.add(entry);
        }
        ScatterDataSet trivialDataSet = new ScatterDataSet(trivialEntries, "trivial vertices");
        trivialDataSet.setScatterShape(ScatterChart.ScatterShape.TRIANGLE);
        trivialDataSet.setColor(ColorTemplate.COLORFUL_COLORS[3]);
        trivialDataSet.setScatterShapeSize(10f);
        trivialDataSet.setValueFormatter(new CustomValueFormatter());
        return trivialDataSet;
    }

    private ScatterDataSet initTwoVertices() {
        ArrayList<Entry> twoVerticesEntries = new ArrayList<>();
        for (int i = 0; i < this.twoVertices.size(); i++) {
            float val = (float) (Math.random() * NUMBER) + 3;
            Entry entry;
            if (this.item.getItemId() == R.id.action_default) {
                entry = new Entry(val, this.twoVertices.get(i).getPoint(), this.twoVertices.get(i));
            } else {
                entry = new Entry(val, i, this.twoVertices.get(i));
            }

            twoVerticesEntries.add(entry);
        }
        ScatterDataSet twoVerticesDataSet = new ScatterDataSet(twoVerticesEntries, "two vertices");
        twoVerticesDataSet.setScatterShape(ScatterChart.ScatterShape.SQUARE);
        twoVerticesDataSet.setColor(ColorTemplate.COLORFUL_COLORS[2]);
        twoVerticesDataSet.setScatterShapeSize(10f);
        twoVerticesDataSet.setValueFormatter(new CustomValueFormatter());
        return twoVerticesDataSet;
    }

    private ScatterDataSet initMultipleVertices() {
        ArrayList<Entry> multipleVerticesEntries = new ArrayList<>();
        for (int i = 0; i < this.multipleVertices.size(); i++) {
            float val = (float) (Math.random() * NUMBER) + 3;
            Entry entry;
            if (this.item.getItemId() == R.id.action_default) {
                entry = new Entry(val, this.multipleVertices.get(i).getPoint(), this.multipleVertices.get(i));
            } else {
                entry = new Entry(val, i, this.multipleVertices.get(i));
            }
            multipleVerticesEntries.add(entry);
        }
        ScatterDataSet multipleVerticesDataSet = new ScatterDataSet(multipleVerticesEntries, "multiple vertices");
        multipleVerticesDataSet.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        multipleVerticesDataSet.setColor(ColorTemplate.COLORFUL_COLORS[4]);
        multipleVerticesDataSet.setScatterShapeSize(10f);
        multipleVerticesDataSet.setValueFormatter(new CustomValueFormatter());
        return multipleVerticesDataSet;
    }

    private void filterVertices() {
        this.trivialVertices.clear();
        this.twoVertices.clear();
        this.multipleVertices.clear();
        for (Point point : this.pointObjects) {
            int size = point.getConnectedPoints().size();
            if (size == 1) {
                this.twoVertices.add(point);
            } else if (size > 1) {
                this.multipleVertices.add(point);
            } else {
                this.trivialVertices.add(point);
            }
        }
    }

    private int isTrivial(int point, int y, final int SIZE) {
        return ((point * y) % SIZE);
    }

    private void zilantro() {
        this.trivialVertices.clear();
        this.twoVertices.clear();
        this.multipleVertices.clear();

        for (Point point : this.pointObjects) {
            for (int i = 1; i < NUMBER; i++) {
                if (isTrivial(point.getPoint(), i, NUMBER) == 0) {
                    if (!point.getConnectedPoints().contains(i)) {
                        point.getConnectedPoints().add(new Point(i));
                    }
                }
            }
        }
    }

    private void createPointObjects() {
        this.pointObjects.clear();
        //TODO create object for points
        for (int x = 1; x < NUMBER; x++) {
            this.pointObjects.add(new Point(x, new ArrayList<Point>()));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    //TODO

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Point point = (Point) e.getData();
        if (point.getConnectedPoints().size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Point : ");
            stringBuilder.append(point.getPoint());
            stringBuilder.append("\n\nConnected to : ");
            for (Point point1 : point.getConnectedPoints()) {
                stringBuilder.append(point1.getPoint());
                stringBuilder.append(",");
            }
            showToast(stringBuilder.toString());
        }
    }

    @Override
    public void onNothingSelected() {

    }

    private void showToast(String txt) {
        Toast.makeText(getBaseContext(), txt, Toast.LENGTH_LONG).show();
    }

    private void refreshChart() {
        new AsyncTask<Void,Void, ArrayList<IScatterDataSet>>() {

            @Override
            protected void onPreExecute() {
                NUMBER = seekBar1.getProgress();
                tvValue.setText(String.valueOf(NUMBER));
                super.onPreExecute();
            }

            @Override
            protected ArrayList<IScatterDataSet> doInBackground(Void... params) {

                createPointObjects();

                zilantro();

                filterVertices();

                xVals.clear();
                for (int i = 0; i < NUMBER + 1; i++) {
                    xVals.add((i) + "");
                }

                ArrayList<IScatterDataSet> dataSets = new ArrayList<IScatterDataSet>();
                dataSets.add(initTrivialVertices()); // add the datasets
                dataSets.add(initTwoVertices());
                dataSets.add(initMultipleVertices());

                return dataSets;
            }

            @Override
            protected void onPostExecute(ArrayList<IScatterDataSet> dataSets) {
                super.onPostExecute(dataSets);
                ScatterData data = new ScatterData(xVals, dataSets);

                scatterChart.setData(data);
                scatterChart.setMaxVisibleValueCount(NUMBER);
                scatterChart.notifyDataSetChanged();
                scatterChart.invalidate();
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }

}
