package com.devpro.a20_07_2022.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.devpro.a20_07_2022.R;
import com.devpro.a20_07_2022.listeners.BillClickListener;
import com.devpro.a20_07_2022.listeners.ProductClickListener;
import com.devpro.a20_07_2022.models.bill.Bill;
import com.devpro.a20_07_2022.models.product.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BillAdapter  extends RecyclerView.Adapter<BillViewHolder>  {
    Context context;
    List<Bill> list;
    BillClickListener listener;

    public BillAdapter(Context context, List<Bill> list,  BillClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BillViewHolder(LayoutInflater.from(context).inflate(R.layout.list_bill_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
        holder.txt_title.setText(list.get(position).product_name);
        Picasso.get().load(list.get(position).product_image).resize(200, 200).placeholder(R.drawable.background_icon).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBillClicked(String.valueOf(list.get(holder.getAdapterPosition()).bill_id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


class BillViewHolder extends RecyclerView.ViewHolder {

    CardView cardView;
    ImageView imageView, image_favorite;
    TextView txt_title, txt_rate_num, txt_num_vote;


    public BillViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.cardView);
        imageView = itemView.findViewById(R.id.image_booking);
        txt_title = itemView.findViewById(R.id.txt_title);
        txt_rate_num = itemView.findViewById(R.id.txt_rate_num);
        txt_num_vote = itemView.findViewById(R.id.txt_num_vote);
    }
}
