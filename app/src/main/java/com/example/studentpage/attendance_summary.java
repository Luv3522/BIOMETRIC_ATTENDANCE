package com.example.studentpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

public class attendance_summary extends AppCompatActivity {

    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_summary);
        pieChart = findViewById(R.id.pie_chart);
        getSupportActionBar().hide();

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
//        for (int i=1; i<3; i++) {
//            //Convert to float
//            float value = (float) (i * 10.0);
//            //Initialize pie chart entry
//            PieEntry pieEntry = new PieEntry(i, value);
//            //Add values in array list
//            pieEntries.add(pieEntry);
//        }
        float value = (float) (36 * 10.0);
        PieEntry pieEntry1 = new PieEntry(1 , value);
        pieEntries.add(pieEntry1);

        float value1 = (float) (9 * 10.0);
        PieEntry pieEntry2 = new PieEntry(2 , value1);
        pieEntries.add(pieEntry2);

        float value2 = (float) (12 * 10.0);
        PieEntry pieEntry3 = new PieEntry(3 , value2);
        pieEntries.add(pieEntry3);



        //Initialize pie data set
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Student");
        //Set colors
        pieDataSet.setColors (ColorTemplate.COLORFUL_COLORS);
//Set pie data
        pieChart. setData (new PieData (pieDataSet));
        //Set animation
        pieChart.animateXY( 4000,  4000);
        //Hide description
        pieChart.getDescription().setEnabled(false);


    }
}