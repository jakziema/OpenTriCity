package com.example.beata_macbook.opentricity.UI.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Model.Place;

import java.util.ArrayList;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

public class PlaceRecyclerViewAdapter extends RecyclerView.Adapter<PlaceRecyclerViewAdapter.PlaceViewHolder>{
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private ArrayList<Place> mPlaces = new ArrayList<>();
    private Context mContext;

    public PlaceRecyclerViewAdapter(Context context, ArrayList<Place> places) {
        mContext = context;
        mPlaces = places;
    }

    @Override
    public PlaceRecyclerViewAdapter.PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, parent, false);
        PlaceViewHolder viewHolder = new PlaceViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceRecyclerViewAdapter.PlaceViewHolder holder, int position) {
        holder.bindPlace(mPlaces.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder  {
        ImageView mImageView;
        TextView mTitleTextView;
        TextView mAddressTextView;

        private Context mContext;

        public PlaceViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.placeImageView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.placeNameTextView);
            mAddressTextView = (TextView) itemView.findViewById(R.id.addressTextView);

            mContext = itemView.getContext();

        }

        public void bindPlace(Place place) {

            mTitleTextView.setText(place.getName());
            mAddressTextView.setText(place.getAddress());
        }


    }
}
