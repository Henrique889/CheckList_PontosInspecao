package com.example.check17_pontosveiculo.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.check17_pontosveiculo.R;
import com.example.check17_pontosveiculo.checkList.imagem.EscolherTipoImg;
import com.example.check17_pontosveiculo.checkList.imagem.image_View;

import java.util.List;

public class MyAdapter_EscolherV extends RecyclerView.Adapter<MyAdapter_EscolherV.MyViewHolder> {

    Context context;
    List<String> list;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener (OnItemClickListener listener) {
        mListener = (OnItemClickListener) listener;
    }

    public MyAdapter_EscolherV(Context context, List<String> list){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelorv_0,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.cod);

            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick ( View v ) {
                    if ( mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick( position );
                        }
                    }

                }
            } );
        }
    }
}
