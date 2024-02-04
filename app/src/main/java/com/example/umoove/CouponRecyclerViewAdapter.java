package com.example.umoove;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CouponRecyclerViewAdapter extends RecyclerView.Adapter<CouponRecyclerViewAdapter.viewHolder> {

    Context context;
    ArrayList<CouponModel> coupons;

    public CouponRecyclerViewAdapter(Context context, ArrayList<CouponModel> coupons){
        this.context = context;
        this.coupons = coupons;
    }

    @NonNull
    @Override
    public CouponRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_order_row, parent, false);
        return new CouponRecyclerViewAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CouponRecyclerViewAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txtViewprice.setText(String.valueOf(coupons.get(position).getPrice()));
        holder.txtViewName.setText(coupons.get(position).getName());
        holder.txtViewDescription.setText(coupons.get(position).getDescription());
        holder.txtViewEntreprise.setText(coupons.get(position).getEntreprise());
        holder.txtViewRemaining.setText(String.valueOf(coupons.get(position).getRemaining()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient client = new OkHttpClient();
                String getURL = "https://umoove.co/api/activities";

                Request req = new Request.Builder().url(getURL).build();
                client.newCall(req).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Toast.makeText(view.getContext(), "Clic sur l'élément à la position " + position, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                        String x = response.body().string();
                        Toast.makeText(view.getContext(), response.body().string(), Toast.LENGTH_SHORT).show();

                    }
                });

                //Toast.makeText(view.getContext(), "Clic sur l'élément à la position " + position, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView txtViewprice, txtViewName, txtViewDescription, txtViewEntreprise, txtViewRemaining;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewprice = itemView.findViewById(R.id.textPrice);
            txtViewName = itemView.findViewById(R.id.textName);
            txtViewDescription = itemView.findViewById(R.id.textDescription);
            txtViewEntreprise = itemView.findViewById(R.id.textEntreprise);
            txtViewRemaining = itemView.findViewById(R.id.textRemaining);

        }
    }

}
