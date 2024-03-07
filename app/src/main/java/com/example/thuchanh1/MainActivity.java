package com.example.thuchanh1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thuchanh1.model.SpinnerAdapter;
import com.example.thuchanh1.model.Tour;
import com.example.thuchanh1.model.TourAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TourAdapter.TourItemListener,
        SearchView.OnQueryTextListener {
    private int[] imgs = {R.drawable.icon_bike, R.drawable.icon_car, R.drawable.icon_airport};
    private Spinner sp;
    private EditText edit1, edit2;
    private Button btnAdd, btnUpdate;
    private RecyclerView rcView;
    private TourAdapter tourAdapter;
    private SearchView searchView;
    private int positionCurr;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = findViewById(R.id.sp);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        btnAdd = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        rcView = findViewById(R.id.rcv);
        btnUpdate.setEnabled(false);
        searchView = findViewById(R.id.search);

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this);
        sp.setAdapter(spinnerAdapter);


        tourAdapter = new TourAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcView.setLayoutManager(linearLayoutManager);
        rcView.setAdapter(tourAdapter);

        tourAdapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour = new Tour();
                String i = sp.getSelectedItem().toString();
                String schedule = edit1.getText().toString();
                String time = edit2.getText().toString();
                int img = R.drawable.icon_car;
                img = imgs[Integer.parseInt(i)];
                if(!schedule.isEmpty() && !time.isEmpty()) {
                    tour.setImg(img);
                    tour.setSchedule(schedule);
                    tour.setTime(time);
                    tourAdapter.add(tour);
                } else {
                    Toast.makeText(getApplicationContext(),"Nhap lai", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour = new Tour();
                String i = sp.getSelectedItem().toString();
                String schedule = edit1.getText().toString();
                String time = edit2.getText().toString();
                int img = R.drawable.icon_car;
                img = imgs[Integer.parseInt(i)];
                if(!schedule.isEmpty() && !time.isEmpty()) {
                    tour.setImg(img);
                    tour.setSchedule(schedule);
                    tour.setTime(time);
                    tourAdapter.update(positionCurr, tour);
                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
        positionCurr = position;
        Tour tour = tourAdapter.getItem(position);
        int img = tour.getImg();
        int p = 0;
        for(int i=0;i<imgs.length;i++) {
            if (img == imgs[i]) {
                p = i;
                break;
            }
        }
        sp.setSelection(p);
        edit1.setText(tour.getSchedule());
        edit2.setText(tour.getTime());
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        List<Tour> filterlist = new ArrayList<>();
        for(Tour i:tourAdapter.getBackup()) {
            if(i.getSchedule().toLowerCase().contains(s.toLowerCase())) {
                filterlist.add(i);
            }
        }
        if(filterlist.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            tourAdapter.filterList(filterlist);
        }
        return false;
    }
}