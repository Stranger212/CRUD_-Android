package com.example.thuchanh1.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuchanh1.R;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder>{
    private Context context;
    private List<Tour> listBackup;
    private List<Tour> listTour;
    private TourItemListener tourItemListener;

    public TourAdapter(Context context) {
        this.context = context;
        listTour = new ArrayList<>();
        listBackup = new ArrayList<>();
    }
    public List<Tour> getBackup() {
        return listBackup;
    }

    public void setListTour(List<Tour> listTour) {
        this.listTour = listTour;
    }

    public void filterList(List<Tour> filterlist) {
        listTour = filterlist;
        notifyDataSetChanged();
    }
    public void setClickListener(TourItemListener tourItemListener) {
        this.tourItemListener = tourItemListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    public Tour getItem(int position) {
        return listTour.get(position);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tour tour = listTour.get(position);
        if(tour == null) {
            return;
        }
        holder.img.setImageResource(tour.getImg());
        holder.schedule.setText(tour.getSchedule());
        holder.time.setText(tour.getTime());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xoa");
                builder.setMessage("Ban co chac muon xoa " + tour.getSchedule() + " khong?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listBackup.remove(position);
                        listTour.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void add(Tour tour) {
        listBackup.add(tour);
        listTour.add(tour);
        notifyDataSetChanged();
    }

    public void update(int position, Tour tour) {
        listBackup.set(position, tour);
        listTour.set(position, tour);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listTour != null) {
            return listTour.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img;
        private TextView schedule, time;
        private Button btnRemove;
        public ViewHolder(@NonNull View view) {
            super(view);
            img = view.findViewById(R.id.icon_img);
            schedule = view.findViewById(R.id.tv_schedule);
            time = view.findViewById(R.id.tv_time);
            btnRemove = view.findViewById(R.id.btn_remove);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(tourItemListener != null) {
                tourItemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface TourItemListener {
        void onItemClick(View view, int position);
    }
}
