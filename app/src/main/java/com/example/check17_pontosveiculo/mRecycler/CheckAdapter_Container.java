package com.example.check17_pontosveiculo.mRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.check17_pontosveiculo.R;
import com.example.check17_pontosveiculo.mDataObject.Registros_Containers;

import java.util.List;

public class CheckAdapter_Container extends RecyclerView.Adapter<CheckAdapter_Container.MyHolder> {
    private Context context;
    private List<Registros_Containers> registros;

    public CheckAdapter_Container(Context context, List<Registros_Containers> registros) {
        this.registros = registros;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.lists_check_container, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //getting the product of the specified position
        Registros_Containers registros_containers = registros.get(position);

        //binding the data with the viewholder views
        holder.txtNumero.setText(registros_containers.getNum_embarque());
        holder.txtData.setText(registros_containers.getData_horario());
        holder.txtTransportadora.setText(registros_containers.getTransportadora());
        holder.txtNome.setText(registros_containers.getNome_motorista());
        holder.txtPlaca.setText(registros_containers.getPlaca_caminhao());
        holder.txtPlacaCarreta.setText(registros_containers.getPlacaCarreta());
        holder.txtLacreOEA.setText(registros_containers.getNumeroLacreOEA());
        holder.txtlacre.setText(registros_containers.getNumLacre());

        holder.txtSecao.setText(registros_containers.getCheckBox_secao());
        holder.txtSecao2.setText(registros_containers.getCheckBox_secao2());
        holder.txtSecao3.setText(registros_containers.getCheckBox_secao3());
        holder.txtPortasInteriores.setText(registros_containers.getCheckBox_portas_interiores());
        holder.txtPortasInteriores2.setText(registros_containers.getCheckBox_portas_interiores2());
        holder.txtPortasInteriores3.setText(registros_containers.getCheckBox_portas_interiores3());
        holder.txtLadoDireito.setText(registros_containers.getCheckBox_ladodireito());
        holder.txtLadoDireito2.setText(registros_containers.getCheckBox_ladodireito2());
        holder.txtLadoDireito3.setText(registros_containers.getCheckBox_ladodireito3());
        holder.txtLadoEsquerdo.setText(registros_containers.getCheckBox_ladoesquerdo());
        holder.txtLadoEsquerdo2.setText(registros_containers.getCheckBox_ladoesquerdo2());
        holder.txtLadoEsquerdo3.setText(registros_containers.getCheckBox_ladoesquerdo3());
        holder.txt_Parede.setText(registros_containers.getCheckBoxParede_Dianteira());
        holder.txt_Parede2.setText(registros_containers.getCheckBoxParede_Dianteira2());
        holder.txt_Parede3.setText(registros_containers.getCheckBoxParede_Dianteira3());
        holder.txt_Teto.setText(registros_containers.getCheckBox_Teto());
        holder.txt_Teto2.setText(registros_containers.getCheckBox_Teto2());
        holder.txt_Teto3.setText(registros_containers.getCheckBox_Teto3());
        holder.txt_Piso_i.setText(registros_containers.getCheckBox_piso());
        holder.txt_Piso_i2.setText(registros_containers.getCheckBox_piso2());
        holder.txt_Piso_i3.setText(registros_containers.getCheckBox_piso3());


    }


    @Override
    public int getItemCount() {
        return registros.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView txtNome,txtData,txtPlaca,txtNumero,txtLacreOEA,txtTransportadora,txtPlacaCarreta,txtlacre,
                txtSecao,txtPortasInteriores,txtLadoDireito,txtLadoEsquerdo,txt_Parede,txt_Teto,txt_Piso_i,
                txtSecao2,txtPortasInteriores2,txtLadoDireito2,txtLadoEsquerdo2,txt_Parede2,txt_Teto2,txt_Piso_i2,
                txtSecao3,txtPortasInteriores3,txtLadoDireito3,txtLadoEsquerdo3,txt_Parede3,txt_Teto3,txt_Piso_i3,
                txtSecao4,txtPortasInteriores4,txtLadoDireito4,txtLadoEsquerdo4,txt_Parede4,txt_Teto4,txt_Piso_i4;

        public MyHolder (View itemView ) {
            super( itemView );
            txtNumero = itemView.findViewById(R.id.id_num_processo);
            txtData = itemView.findViewById(R.id.txt_dataHora);
            txtTransportadora = itemView.findViewById(R.id.txt_transportadora);
            txtNome = itemView.findViewById(R.id.nomeMotorista);
            txtPlaca = itemView.findViewById(R.id.txt_caminhao);
            txtPlacaCarreta = itemView.findViewById(R.id.txt_Carreta);
            txtLacreOEA = itemView.findViewById(R.id.txtLacre_OEA);
            txtlacre = itemView.findViewById(R.id.txt_Numero_Lacre);

            txtSecao= itemView.findViewById(R.id.txt_secao);
            txtSecao2= itemView.findViewById(R.id.txt_secao2);
            txtSecao3= itemView.findViewById(R.id.txt_secao3);

            txtPortasInteriores= itemView.findViewById(R.id.txt_portas_interiores);
            txtPortasInteriores2= itemView.findViewById(R.id.txt_portas_interiores2);
            txtPortasInteriores3= itemView.findViewById(R.id.txt_portas_interiores3);

            txtLadoDireito= itemView.findViewById(R.id.txt_ladoDireito);
            txtLadoDireito2= itemView.findViewById(R.id.txt_ladoDireito2);
            txtLadoDireito3= itemView.findViewById(R.id.txt_ladoDireito3);

            txtLadoEsquerdo= itemView.findViewById(R.id.txt_ladoEsquerdo);
            txtLadoEsquerdo2= itemView.findViewById(R.id.txt_ladoEsquerdo2);
            txtLadoEsquerdo3= itemView.findViewById(R.id.txt_ladoEsquerdo3);

            txt_Parede= itemView.findViewById(R.id.txt_Parede_D);
            txt_Parede2= itemView.findViewById(R.id.txt_Parede_D2);
            txt_Parede3= itemView.findViewById(R.id.txt_Parede_D3);

            txt_Teto= itemView.findViewById(R.id.txt_teto_e);
            txt_Teto2= itemView.findViewById(R.id.txt_teto_e2);
            txt_Teto3= itemView.findViewById(R.id.txt_teto_e3);

            txt_Piso_i= itemView.findViewById(R.id.txt_Piso_);
            txt_Piso_i2= itemView.findViewById(R.id.txt_Piso_2);
            txt_Piso_i3= itemView.findViewById(R.id.txt_Piso_3);


        }
    }
}
