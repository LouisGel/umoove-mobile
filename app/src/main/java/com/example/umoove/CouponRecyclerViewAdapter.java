package com.example.umoove;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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


        int price = coupons.get(position).getPrice();


        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                OkHttpClient client = new OkHttpClient();
                OkHttpClient client2 = new OkHttpClient();
                String getURL = "https://umoove.co/api/user/1";
                String postURL = "https://umoove.co/api/buy";

                Request reqVerif = new Request.Builder().url(getURL).build();


                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("coupon_type_id", "1");
                    jsonBody.put("user_id", "1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody.toString());

                Request reqPayement = new Request.Builder().url(postURL).post(requestBody).build();

                //RequestBody requestBody = new FormBody.Builder().add("coupon_type_id", "1").add("user_id", "1").build();
                //Request reqPayement = new Request.Builder().url(postURL).post(requestBody).build();




                client.newCall(reqVerif).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(view.getContext(), "Une erreur est survenue, veuillez réssayer", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response responseVerif) throws IOException {


                        String responseVerifStr = responseVerif.body().string();

                        try {
                            JSONObject jObject = new JSONObject(responseVerifStr);

                            int portefeuille = jObject.getInt("points");
                            if (portefeuille < price){
                                ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(view.getContext(), "Vous n'avez pas assez de points pour acheter ce coupon...", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                return;
                            }

                        } catch (JSONException e) {
                            ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(view.getContext(), "Une erreur est survenue, veuillez réssayer", Toast.LENGTH_SHORT).show();
                                }
                            });
                            return;
                        }



                        //=========================================================Paye le coupon
                        client2.newCall(reqPayement).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(view.getContext(), "Une erreur est survenue, veuillez réssayer", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response responsePaiement) throws IOException {
                                String responsePaiementStr = responsePaiement.body().string();

                                try {
                                    JSONObject jObject = new JSONObject(responsePaiementStr);
                                        ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    String code = jObject.getString("code");
                                                    Toast.makeText(view.getContext(), "Wow! Votre code rabais est : " + code, Toast.LENGTH_SHORT).show();
                                                } catch (JSONException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            }
                                        });
                                } catch (JSONException e) {
                                    ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(view.getContext(), "Une erreur est survenue, veuillez réssayer", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });

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

            txtViewprice = itemView.findViewById(R.id.textCode);
            txtViewName = itemView.findViewById(R.id.textUsed);
            txtViewDescription = itemView.findViewById(R.id.textDescription);
            txtViewEntreprise = itemView.findViewById(R.id.textEntreprise);
            txtViewRemaining = itemView.findViewById(R.id.textRemaining);

        }
    }

}
