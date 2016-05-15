package com.binasphere.hymmnosdict.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binasphere.hymmnosdict.App;
import com.binasphere.hymmnosdict.R;
import com.binasphere.hymmnosdict.bean.HymmnosWord;

import java.util.List;

/**
 * Created by Kerstin on 2015/12/4.
 */
public class MyReAdapter extends RecyclerView.Adapter<MyReAdapter.MyViewHolder> {
    private List<HymmnosWord> words;
    private MyItemClickListener mListener;
    public MyReAdapter(List<HymmnosWord> list){
        words=list;
    }
    public interface MyItemClickListener{
        void onClick(View v,int position);
    }
    public void setOnItemClickListener(MyItemClickListener listener){
        mListener=listener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,  final int position) {
        if(mListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(v,holder.getAdapterPosition());
                }
            });
        }
        holder.tv_hym.setText(" "+words.get(position).getHymmnos()+" ");
        holder.tv_eng.setText(words.get(position).getHymmnos());
        holder.tv_exp.setText(words.get(position).getExp_jp());

    }
    public HymmnosWord getItem(int position){
        return words.get(position);
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_hym;
        TextView tv_eng;
        TextView tv_exp;
        View space;
        public MyViewHolder(View itemView) {
            super(itemView);
            space=itemView.findViewById(R.id.space_item);
            tv_hym = (TextView) itemView.findViewById(R.id.tv_hym_rv_word);
            App.TVU.setHymmnos(tv_hym);
            tv_eng = (TextView) itemView.findViewById(R.id.tv_eng_rv_word);
            tv_exp = (TextView) itemView.findViewById(R.id.tv_exp_rv_word);
        }
    }

}
