package dsa.eetac.upc.edu;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Recycler extends RecyclerView.Adapter<Recycler.ViewHolder> {
    private List<Track> data;

    //Asign the text TextView to the text1 in the layout
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public ViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(android.R.id.text1);
        }
    }

    //Constructor
    public Recycler(List<Track> data) {
        this.data = data;
    }

    @Override
    public Recycler.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_selectable_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Recycler.ViewHolder holder, int position) {
        Track trackdata = ((Track) data.get(position));
        holder.text.setText(trackdata.toString());
        holder.itemView.setTag(trackdata.id);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }






}