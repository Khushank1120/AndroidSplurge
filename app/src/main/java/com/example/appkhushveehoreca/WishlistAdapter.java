package com.example.appkhushveehoreca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private List<WishlistModel> wishlistModelList;

    public WishlistAdapter(List<WishlistModel> wishlistModelList){
        this.wishlistModelList = wishlistModelList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wishlist_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        int resource = wishlistModelList.get(position).getProductImage();
        String title = wishlistModelList.get(position).getProductTitle();
        String title1 = wishlistModelList.get(position).getProductTitle1();
        String freeCoupon = wishlistModelList.get(position).getFreeCoupons();
        String productPrice = wishlistModelList.get(position).getProductPrice();
        String cuttedPrice = wishlistModelList.get(position).getCuttedPrice();

        viewHolder.setData(resource,title,title1,freeCoupon,productPrice,cuttedPrice);

    }

    @Override
    public int getItemCount() {
        return wishlistModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        private ImageView productImage;
        private TextView productTitle;
        private TextView productTitle1;
        private TextView freeCoupons;
        private View priceCut;
        private TextView productPrice;
        private TextView cuttedPrice;
        private Button deleteBtn;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            productTitle1 = itemView.findViewById(R.id.product_title1);
            freeCoupons = itemView.findViewById(R.id.free_coupon);
            priceCut = itemView.findViewById(R.id.price_cut);
            productPrice = itemView.findViewById(R.id.product_price);
            cuttedPrice = itemView.findViewById(R.id.cutted_price);
            deleteBtn = itemView.findViewById(R.id.delete_btn);

        }
        private void setData(int resource, String title, String title1, String freeCouponsNo, String price, String cuttedPriceValue){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            productTitle1.setText(title1);
            freeCoupons.setText(freeCouponsNo);
            productPrice.setText(price);
            cuttedPrice.setText(cuttedPriceValue);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Delete", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
