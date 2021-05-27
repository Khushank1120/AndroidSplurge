package com.example.appkhushveehoreca;

import android.os.Bundle;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView =(SearchView) findViewById(R.id.search_view);
        textView = findViewById(R.id.textview);
        recyclerView = findViewById(R.id.recycler_view);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        final List<WishlistModel> list = new ArrayList<>();
        final List<String> ids = new ArrayList<>();

        final Adapter adapter = new Adapter(list,false);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String s) {
                list.clear();
                ids.clear();

                final String[] tags = s.split(" ");
                for (final String tag: tags){
                    FirebaseFirestore.getInstance().collection("PRODUCTS").whereArrayContains("tags" ,tag)
                            .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                                    WishlistModel model = new WishlistModel(documentSnapshot.getId(),documentSnapshot.get("product_image_1").toString()
                                            , Objects.requireNonNull(documentSnapshot.get("product_title")).toString()
                                            , Objects.requireNonNull(documentSnapshot.get("product_subtitle")).toString()
                                            , Objects.requireNonNull(documentSnapshot.get("product_subtitle2")).toString()
                                            , Objects.requireNonNull(documentSnapshot.get("product_price")).toString()
                                            , Objects.requireNonNull(documentSnapshot.get("cutted_price")).toString());

                                    if(!ids.contains(model.getProductId()));
                                    list.add(model);
                                    ids.add(model.getProductId());

                                }
                                if(tag.equals(tags[tags.length-1])){
                                    if(list.size() == 0){
                                        textView.setVisibility(View.VISIBLE);
                                        recyclerView.setVisibility(View.GONE);
                                    }else{
                                        textView.setVisibility(View.GONE);
                                        recyclerView.setVisibility(View.VISIBLE);
                                        adapter.getFilter().filter(s);
                                    }
                                }

                            }else{
                                String error = task.getException().getMessage();
                                Toast.makeText(SearchActivity.this,"",Toast.LENGTH_SHORT).show();
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

    class Adapter extends WishlistAdapter implements Filterable{


        public Adapter(List<WishlistModel> wishlistModelList, Boolean wishlist) {
            super(wishlistModelList, wishlist);
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    ///// Filter Logic

                    return null;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    notifyDataSetChanged();

                }
            };
        }
    }


}