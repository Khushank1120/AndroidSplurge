package com.example.appkhushveehoreca;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {

    public static boolean ALREADY_ADDED_TO_CARTLIST;

    private List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductScrollModel> horizontalProductScrollModelList) {
        this.horizontalProductScrollModelList = horizontalProductScrollModelList;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder viewHolder, int position) {

        String resource = horizontalProductScrollModelList.get(position).getProductImage();
        String title = horizontalProductScrollModelList.get(position).getProductTitle();
        String description = horizontalProductScrollModelList.get(position).getProductDescription();
        String description1 = horizontalProductScrollModelList.get(position).getProductDescription1();
        String price = horizontalProductScrollModelList.get(position).getProductPrice();
        String cuttedPrice = horizontalProductScrollModelList.get(position).getCuttedPrice();


        viewHolder.setData(resource, title, description, description1, price, cuttedPrice);

    }

    @Override
    public int getItemCount() {
        if (horizontalProductScrollModelList.size() > 8) {
            return 8;
        } else {
            return horizontalProductScrollModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productDescription1;
        private TextView productPrice;
        private TextView cuttedPrice;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.h_s_product_image);
            productTitle = itemView.findViewById(R.id.h_s_product_title);
            productDescription = itemView.findViewById(R.id.h_s_product_description);
            productDescription1 = itemView.findViewById(R.id.h_s_product_description1);
            productPrice = itemView.findViewById(R.id.h_s_product_price);
            cuttedPrice = itemView.findViewById(R.id.h_s_cutted_price);

        }

        public void setData(String resource, String title, String description, String description1, String price, String cuttedPriceValue) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions().placeholder(R.drawable.placeholder)).into(productImage);
            productTitle.setText(title);
            productDescription.setText(description);
            productDescription1.setText(description1);
            productPrice.setText(price);
//            productPrice.setText("Rs." + price + "/-");
            cuttedPrice.setText(cuttedPriceValue);

            if (title.equals("")) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                        itemView.getContext().startActivity(productDetailsIntent);

                    }
                });
            }
        }

    }
}