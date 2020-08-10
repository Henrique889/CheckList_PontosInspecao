package com.example.check17_pontosveiculo.mRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.check17_pontosveiculo.R;
import com.example.check17_pontosveiculo.mDataObject.SpaceCraft_Containers;

import java.util.List;

public class MyAdapter_Container extends RecyclerView.Adapter<MyAdapter_Container.MyHolder> {
    private Context context;
    private List<SpaceCraft_Containers> spaces;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener ( OnItemClickListener listener) {
        mListener = listener;
    }

    public MyAdapter_Container(Context context, List<SpaceCraft_Containers> spaces) {
        this.spaces = spaces;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.model_rv_container, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //getting the product of the specified position
        SpaceCraft_Containers spacescraft = spaces.get(position);

        //binding the data with the viewholder views
        holder.txtNome.setText(spacescraft.getNome());
        holder.txtData.setText(spacescraft.getData());
        holder.txtPlaca.setText(spacescraft.getPlaca_Carreta());
        holder.txtNumero.setText(spacescraft.getNumero00());
        holder.txtLacre.setText(spacescraft.getLacre());
    }

    @Override
    public int getItemCount() {
        return spaces.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView txtNome,txtData,txtPlaca,txtNumero,txtLacre;

        public MyHolder ( View itemView ) {
            super( itemView );
            txtNome = itemView.findViewById(R.id.NomeMotorista);
            txtData = itemView.findViewById(R.id.DataHora0);
            txtPlaca = itemView.findViewById(R.id.placaCarreta);
            txtNumero = itemView.findViewById(R.id.numero_Embarque);
            txtLacre = itemView.findViewById(R.id.NumerolacreOEA);

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
