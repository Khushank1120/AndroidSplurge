package com.example.appkhushveehoreca;

import android.os.Bundle;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private TextView textView;
    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        searchView = findViewById(R.id.search_view);
        textView = findViewById(R.id.textView);
        recyclerView = findViewById(R.id.recycler_view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        final List<WishlistModel> list=new ArrayList<>();
        final List<String> ids=new ArrayList<>();

        adapter = new Adapter(list, false);
        adapter.setFromSearch(true);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {
                list.clear();
                ids.clear();

                final String[] tags = s.toLowerCase().split(" ");


                ////// For Steel Products //////

                for (final String tag:tags){
                    tag.trim();
                    FirebaseFirestore.getInstance().collection("CATEGORIES").document("STEEL")
                            .collection("TOP_DEALS").whereArrayContains("tags" ,tag)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        WishlistModel model = new WishlistModel(documentSnapshot.getId(), Objects.requireNonNull(documentSnapshot.get("product_image_" + x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_title_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle2_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_price_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("cutted_price_"+ x)).toString());

                                        model.setTags((ArrayList<String>) documentSnapshot.get("tags"));

                                        if (!ids.contains(model.getProductId())) ;
                                        list.add(model);
                                        ids.add(model.getProductId());
                                    }
                                    }

                            }

                            if(tag.equals(tags[tags.length-1])){

                                if(list.size() == 0){
                                    textView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                }else {
                                    textView.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    adapter.getFilter().filter(s);
                                }
                            }

                            else {
//                                String error = task.getException().getMessage();
//                                Toast.makeText(SearchActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                ////// For Wooden Products //////

                for (final String tag:tags){
                    tag.trim();
                    FirebaseFirestore.getInstance().collection("CATEGORIES").document("WOODEN")
                            .collection("TOP_DEALS").whereArrayContains("tags" ,tag)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        WishlistModel model = new WishlistModel(documentSnapshot.getId(), documentSnapshot.get("product_image_" + x).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_title_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle2_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_price_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("cutted_price_"+ x)).toString());

                                        model.setTags((ArrayList<String>) documentSnapshot.get("tags"));

                                        if (!ids.contains(model.getProductId())) ;
                                        list.add(model);
                                        ids.add(model.getProductId());
                                    }
                                }

                            }

                            if(tag.equals(tags[tags.length-1])){

                                if(list.size() == 0){
                                    textView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                }else {
                                    textView.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    adapter.getFilter().filter(s);
                                }
                            }

                            else {
//                                String error = task.getException().getMessage();
//                                Toast.makeText(SearchActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                ////// For Ceramic Products //////

                for (final String tag:tags){
                    tag.trim();
                    FirebaseFirestore.getInstance().collection("CATEGORIES").document("CERAMIC")
                            .collection("TOP_DEALS").whereArrayContains("tags" ,tag)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        WishlistModel model = new WishlistModel(documentSnapshot.getId(), documentSnapshot.get("product_image_" + x).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_title_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle2_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_price_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("cutted_price_"+ x)).toString());

                                        model.setTags((ArrayList<String>) documentSnapshot.get("tags"));

                                        if (!ids.contains(model.getProductId())) ;
                                        list.add(model);
                                        ids.add(model.getProductId());
                                    }
                                }

                            }

                            if(tag.equals(tags[tags.length-1])){

                                if(list.size() == 0){
                                    textView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                }else {
                                    textView.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    adapter.getFilter().filter(s);
                                }
                            }

                            else {
//                                String error = task.getException().getMessage();
//                                Toast.makeText(SearchActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                ////// For Premium Pots Products //////

                for (final String tag:tags){
                    tag.trim();
                    FirebaseFirestore.getInstance().collection("CATEGORIES").document("PREMIUM POTS")
                            .collection("TOP_DEALS").whereArrayContains("tags" ,tag)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        WishlistModel model = new WishlistModel(documentSnapshot.getId(), documentSnapshot.get("product_image_" + x).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_title_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle2_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_price_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("cutted_price_"+ x)).toString());

                                        model.setTags((ArrayList<String>) documentSnapshot.get("tags"));

                                        if (!ids.contains(model.getProductId())) ;
                                        list.add(model);
                                        ids.add(model.getProductId());
                                    }
                                }

                            }

                            if(tag.equals(tags[tags.length-1])){

                                if(list.size() == 0){
                                    textView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                }else {
                                    textView.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    adapter.getFilter().filter(s);
                                }
                            }

                            else {
//                                String error = task.getException().getMessage();
//                                Toast.makeText(SearchActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                ////// For Cutlery Products //////

                for (final String tag:tags){
                    tag.trim();
                    FirebaseFirestore.getInstance().collection("CATEGORIES").document("CUTLERY")
                            .collection("TOP_DEALS").whereArrayContains("tags" ,tag)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        WishlistModel model = new WishlistModel(documentSnapshot.getId(), documentSnapshot.get("product_image_" + x).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_title_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle2_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_price_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("cutted_price_"+ x)).toString());

                                        model.setTags((ArrayList<String>) documentSnapshot.get("tags"));

                                        if (!ids.contains(model.getProductId())) ;
                                        list.add(model);
                                        ids.add(model.getProductId());
                                    }
                                }

                            }

                            if(tag.equals(tags[tags.length-1])){

                                if(list.size() == 0){
                                    textView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                }else {
                                    textView.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    adapter.getFilter().filter(s);
                                }
                            }

                            else {
//                                String error = task.getException().getMessage();
//                                Toast.makeText(SearchActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                ////// For Glassware Products //////

                for (final String tag:tags){
                    tag.trim();
                    FirebaseFirestore.getInstance().collection("CATEGORIES").document("GLASSWARE")
                            .collection("TOP_DEALS").whereArrayContains("tags" ,tag)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        WishlistModel model = new WishlistModel(documentSnapshot.getId(), documentSnapshot.get("product_image_" + x).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_title_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle2_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_price_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("cutted_price_"+ x)).toString());

                                        model.setTags((ArrayList<String>) documentSnapshot.get("tags"));

                                        if (!ids.contains(model.getProductId())) ;
                                        list.add(model);
                                        ids.add(model.getProductId());
                                    }
                                }

                            }

                            if(tag.equals(tags[tags.length-1])){

                                if(list.size() == 0){
                                    textView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                }else {
                                    textView.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    adapter.getFilter().filter(s);
                                }
                            }

                            else {
//                                String error = task.getException().getMessage();
//                                Toast.makeText(SearchActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                ////// For Opalware Products //////

                for (final String tag:tags){
                    tag.trim();
                    FirebaseFirestore.getInstance().collection("CATEGORIES").document("OPALWARE")
                            .collection("TOP_DEALS").whereArrayContains("tags" ,tag)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        WishlistModel model = new WishlistModel(documentSnapshot.getId(), documentSnapshot.get("product_image_" + x).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_title_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle2_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_price_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("cutted_price_"+ x)).toString());

                                        model.setTags((ArrayList<String>) documentSnapshot.get("tags"));

                                        if (!ids.contains(model.getProductId())) ;
                                        list.add(model);
                                        ids.add(model.getProductId());
                                    }
                                }

                            }

                            if(tag.equals(tags[tags.length-1])){

                                if(list.size() == 0){
                                    textView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                }else {
                                    textView.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    adapter.getFilter().filter(s);
                                }
                            }

                            else {
//                                String error = task.getException().getMessage();
//                                Toast.makeText(SearchActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                ////// For Gifting Products //////

                for (final String tag:tags){
                    tag.trim();
                    FirebaseFirestore.getInstance().collection("CATEGORIES").document("GIFTING")
                            .collection("TOP_DEALS").whereArrayContains("tags" ,tag)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    long no_of_products = (long) documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        WishlistModel model = new WishlistModel(documentSnapshot.getId(), documentSnapshot.get("product_image_" + x).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_title_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_subtitle2_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("product_price_"+ x)).toString()
                                                , Objects.requireNonNull(documentSnapshot.get("cutted_price_"+ x)).toString());

                                        model.setTags((ArrayList<String>) documentSnapshot.get("tags"));

                                        if (!ids.contains(model.getProductId())) ;
                                        list.add(model);
                                        ids.add(model.getProductId());
                                    }
                                }

                            }

                            if(tag.equals(tags[tags.length-1])){

                                if(list.size() == 0){
                                    textView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.GONE);
                                }else {
                                    textView.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    adapter.getFilter().filter(s);
                                }
                            }

                            else {
//                                String error = task.getException().getMessage();
//                                Toast.makeText(SearchActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                return false;
            }


            @Override
            public boolean onQueryTextChange(String s) {

                return false;
            }
        });
    }

    static class Adapter extends WishlistAdapter implements Filterable{

        private List<WishlistModel> originalList;
        public Adapter(List<WishlistModel> wishlistModelList, Boolean wishlist) {
            super(wishlistModelList, wishlist);

            originalList = wishlistModelList;

        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults results = new FilterResults();

                    List<WishlistModel> filteredList = new ArrayList<>();

                    final String[] tags = constraint.toString().toLowerCase().split(" ");

                    for(WishlistModel model : originalList){
                        ArrayList<String> presentTags = new ArrayList<>();
                        for(String tag : tags){
                            if(model.getTags().contains(tag)){
                                presentTags.add(tag);

                            }
                        }
                        model.setTags(presentTags);
                    }
                    for(int i = tags.length-1; i >= 0; i--){
                        for(WishlistModel model: originalList){
                            if(model.getTags().size() == i){
                                filteredList.add(model);
                            }
                        }

                    }

                    results.values = filteredList;
                    results.count = filteredList.size();

                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    if(results.count > 0){
                        setWishlistModelList((List<WishlistModel>) results.values);
                    }

                    notifyDataSetChanged();

                }
            };
        }
    }
}