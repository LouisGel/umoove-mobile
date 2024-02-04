package com.example.umoove;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MesCouponRecyclerViewAdapter extends RecyclerView.Adapter<MesCouponRecyclerViewAdapter.viewHolder> {

    Context context;
    ArrayList<MesCouponsModel> coupons;

    public MesCouponRecyclerViewAdapter(Context context, ArrayList<MesCouponsModel> coupons){
        this.context = context;
        this.coupons = coupons;
    }

    @NonNull
    @Override
    public MesCouponRecyclerViewAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_myorder_row, parent, false);
        return new MesCouponRecyclerViewAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MesCouponRecyclerViewAdapter.viewHolder holder, int position) {
        Boolean usable = new Boolean(coupons.get(position).getDate_used());
        String txtUsable = (usable)?"Utilisable":"Inutilisable";
        holder.txtUsed.setText(String.valueOf(txtUsable));
        holder.txtDescription.setText(coupons.get(position).getDescription());
        holder.txtCode.setText("Code : " + coupons.get(position).getCode());
        holder.txtEntreprise.setText("Entreprise" + coupons.get(position).getEntreprise());
    }

    @Override
    public int getItemCount() {
        return coupons.size();
    }


    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView txtUsed, txtDescription, txtCode, txtEntreprise;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            txtUsed = itemView.findViewById(R.id.textUsed);
            txtDescription = itemView.findViewById(R.id.textDescription);
            txtCode = itemView.findViewById(R.id.textCode);
            txtEntreprise = itemView.findViewById(R.id.textEntreprise);

        }
    }
}
