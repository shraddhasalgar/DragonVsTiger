package com.dragon.multigame.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dragon.multigame.Interface.ClassCallback;
import com.dragon.multigame.R;
import com.dragon.multigame.Utils.Functions;
import com.dragon.multigame.model.HomescreenModel;

import java.util.ArrayList;
import java.util.List;

public class HomegameListAdapter extends RecyclerView.Adapter<HomegameListAdapter.myholder> implements Filterable {
    List<HomescreenModel> arrayList;
    List<HomescreenModel> seachList;
    Context context;
    ClassCallback callback;

    public HomegameListAdapter(Context context) {
        this.context = context;
    }

    public void setCallback(ClassCallback callback) {
        this.callback = callback;
    }

    public void setArrayList(List<HomescreenModel> arrayList)
    {
        this.arrayList = arrayList;
        seachList = new ArrayList<>(arrayList);
    }

    @NonNull
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homeicon,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull myholder holder, int position) {
        HomescreenModel model = arrayList.get(position);
        if(model != null)
            holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class myholder extends RecyclerView.ViewHolder{
        public myholder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(HomescreenModel model) {
            ImageView ivHomegamecard = itemView.findViewById(R.id.ivHomegamecard);
            Glide.with(context)
                    .load(Functions.getDrawable(context,model.getItemImage()))
                    .into(ivHomegamecard);

            ivHomegamecard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.Response(v,getAdapterPosition(),model.getItemTag());
                }
            });
        }
    }

    // that function will filter the result
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    arrayList = seachList;
                } else {
                    ArrayList<HomescreenModel> filteredList = new ArrayList<>();
                    for (HomescreenModel row : seachList) {
                        String selectedvalue = charString.toLowerCase();
                        String listvalue = row.getItemType().toLowerCase();
                        Functions.LOGD("HomeGameList","listvalue : "+listvalue);

                        if(selectedvalue.contains("all") || selectedvalue.contains(listvalue))
                        {
                            filteredList.add(row);
                        }
                    }

                    arrayList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayList;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrayList = (ArrayList<HomescreenModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
