package com.example.check17_pontosveiculo.mRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.check17_pontosveiculo.R;
import com.example.check17_pontosveiculo.mDataObject.Spacecraft;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private Context context;
    private List<Spacecraft> spacecrafts;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener ( OnItemClickListener listener) {
        mListener = listener;
    }

    public MyAdapter(Context context, List<Spacecraft> spacecrafts) {
        this.spacecrafts = spacecrafts;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.modelo_rv, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //getting the product of the specified position
        Spacecraft spacecraft = spacecrafts.get(position);

        //binding the data with the viewholder views
        holder.txtNome.setText(spacecraft.getNome());
        holder.txtData.setText(spacecraft.getData());
        holder.txtPlaca.setText(spacecraft.getPlaca());
        holder.txtNumero.setText(spacecraft.getNumero());
        holder.txtLacre.setText(spacecraft.getLacre());
    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView txtNome,txtData,txtPlaca,txtNumero,txtLacre;
        //private CardView item_contact;

        public MyHolder ( View itemView ) {
            super( itemView );
            //item_contact = itemView.findViewById(R.id.list_itemV);

            txtNome = itemView.findViewById(R.id.nome);
            txtData = itemView.findViewById(R.id.data);
            txtPlaca = itemView.findViewById(R.id.placa);
            txtNumero = itemView.findViewById(R.id.numero);
            txtLacre = itemView.findViewById(R.id.lacreOEA);

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
