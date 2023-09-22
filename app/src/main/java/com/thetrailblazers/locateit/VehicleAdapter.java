package com.thetrailblazers.locateit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.MyViewHolder>{
    public VehicleAdapter(Context context, ArrayList<Vehicle> vehicleArrayList) {
        this.context = context;
        this.list = vehicleArrayList;
    }

    Context context;
    ArrayList<Vehicle> list;
    int lastPosition = -1;


    @NonNull
    @Override
    public VehicleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(context).inflate(R.layout.vehicle_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.MyViewHolder holder, int position) {
        Vehicle vehicle = list.get(position);
        holder.name.setText(vehicle.getName());
        holder.code.setText(vehicle.getCode());
        /*holder.details.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(vehicle.getDetails()));
            context.startActivity(intent);
        */
        Glide.with(context)
                .load(vehicle.getImgUrl())
                .placeholder(R.drawable.load)
                .transition(GenericTransitionOptions.with(android.R.anim.fade_in))
                .into(holder.img);
        rvAnimation(holder.itemView, position);
    }

    private void rvAnimation(View itemView, int position) {
        if(position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, code;
        ImageView img;
        Button details, trackNow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            code = itemView.findViewById(R.id.tvCode);
            img = itemView.findViewById(R.id.imgVehicle);
            details = itemView.findViewById(R.id.btnDetails);
            trackNow = itemView.findViewById(R.id.btnTrackNow);
        }
    }
}
