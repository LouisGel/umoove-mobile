package com.example.umoove;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
    public void onBindViewHolder(@NonNull CouponRecyclerViewAdapter.viewHolder holder, int position) {

        holder.txtViewprice.setText(String.valueOf(coupons.get(position).getPrice()));
        holder.txtViewName.setText(coupons.get(position).getName());
        holder.txtViewDescription.setText(coupons.get(position).getDescription());
        holder.txtViewEntreprise.setText(coupons.get(position).getEntreprise());
        holder.txtViewRemaining.setText(String.valueOf(coupons.get(position).getRemaining()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x = 4;
                Log.d("CouponRecyclerViewAdapter", "Clic sur l'élément à la position " + position);
                Toast.makeText(view.getContext(), "Clic sur l'élément à la position " + position, Toast.LENGTH_SHORT).show();
                ///Toast.makeText(view.getContext(), "Clic sur l'élément à la position ").show();
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
