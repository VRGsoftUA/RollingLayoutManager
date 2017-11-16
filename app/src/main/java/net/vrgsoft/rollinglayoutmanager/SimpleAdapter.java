package net.vrgsoft.rollinglayoutmanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {
    private List<String> mData;

    public SimpleAdapter() {
        this(new ArrayList<String>());
    }

    public SimpleAdapter(List<String> data) {
        mData = data;
    }

    public void setData(List<String> data) {
        mData = data;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View item = inflater.inflate(R.layout.item_simple, parent, false);
        return new SimpleViewHolder(item);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.title);
        }

        public void bind(String data){
            mTextView.setText(data);
        }
    }
}
