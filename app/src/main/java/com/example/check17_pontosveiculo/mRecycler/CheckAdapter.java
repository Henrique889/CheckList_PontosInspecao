package com.example.check17_pontosveiculo.mRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.check17_pontosveiculo.R;
import com.example.check17_pontosveiculo.mDataObject.Registros_Checks;

import java.util.List;

public class CheckAdapter extends RecyclerView.Adapter<CheckAdapter.My_Holder> {
    private Context context;
    private List<Registros_Checks> registros_checks;

    public CheckAdapter(Context context, List<Registros_Checks> registros_checks) {
            this.registros_checks = registros_checks;
            this.context = context;
    }

    @Override
    public My_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);

            View view = inflater.inflate(R.layout.list_checks, parent, false);

            return new My_Holder(view);
            }

    @Override
    public void onBindViewHolder(My_Holder holder, int position) {
            //getting the product of the specified position
            Registros_Checks registrosChecks = registros_checks.get(position);

            //binding the data with the viewholder views
            holder.txtNumero.setText(registrosChecks.getNumero_embarque());
            holder.txtData.setText(registrosChecks.getData());
            holder.txtTransportadora.setText(registrosChecks.getTransportadora());
            holder.txtNome.setText(registrosChecks.getNome());
            holder.txtPlaca.setText(registrosChecks.getPlaca_caminhao());
            holder.txtPlacaCarreta.setText(registrosChecks.getPlaca_carreta());
            holder.txtLacreOEA.setText(registrosChecks.getNumero_LacreOEA());
            holder.txtlacre.setText(registrosChecks.getNumero_lacre());

            holder.txtParachoque.setText(registrosChecks.getCheckBox_Parachoque());
            holder.txtParachoque2.setText(registrosChecks.getCheckBox_Parachoque2());
            holder.txtParachoque3.setText(registrosChecks.getCheckBox_Parachoque3());
            holder.txtMotor.setText(registrosChecks.getCheckBox_Motor());
            holder.txtMotor2.setText(registrosChecks.getCheckBox_Motor2());
            holder.txtMotor3.setText(registrosChecks.getCheckBox_Motor3());
            holder.txtCalotas.setText(registrosChecks.getCheckBox_Pneus());
            holder.txtCalotas2.setText(registrosChecks.getCheckBox_Pneus2());
            holder.txtCalotas3.setText(registrosChecks.getCheckBox_Pneus3());
            holder.txtPiso_Cabine.setText(registrosChecks.getCheckBox_Piso());
            holder.txtPiso_Cabine2.setText(registrosChecks.getCheckBox_Piso2());
            holder.txtPiso_Cabine3.setText(registrosChecks.getCheckBox_Piso3());
            holder.txtTanqueC.setText(registrosChecks.getCheckBox_Tanque());
            holder.txtTanqueC2.setText(registrosChecks.getCheckBox_Tanque2());
            holder.txtTanqueC3.setText(registrosChecks.getCheckBox_Tanque3());
            holder.txtCabine.setText(registrosChecks.getCheckBox_Cabine());
            holder.txtCabine2.setText(registrosChecks.getCheckBox_Cabine2());
            holder.txtCabine3.setText(registrosChecks.getCheckBox_Cabine3());
            holder.txtTanquesAr.setText(registrosChecks.getCheckBox_Tanques_ar());
            holder.txtTanquesAr2.setText(registrosChecks.getCheckBox_Tanques_ar2());
            holder.txtTanquesAr3.setText(registrosChecks.getCheckBox_Tanques_ar3());
            holder.txtEixos.setText(registrosChecks.getCheckBox_Eixos());
            holder.txtEixos2.setText(registrosChecks.getCheckBox_Eixos2());
            holder.txtEixos3.setText(registrosChecks.getCheckBox_Eixos3());

            holder.txtQuintaRoda.setText(registrosChecks.getCheckBox_Roda());
            holder.txtQuintaRoda2.setText(registrosChecks.getCheckBox_Roda2());
            holder.txtQuintaRoda3.setText(registrosChecks.getCheckBox_Roda3());
            holder.txtExterior.setText(registrosChecks.getCheckBox_Exterior());
            holder.txtExterior2.setText(registrosChecks.getCheckBox_Exterior2());
            holder.txtExterior3.setText(registrosChecks.getCheckBox_Exterior3());
            holder.txtPiso.setText(registrosChecks.getCheckBox_Piso_interior());
            holder.txtPiso2.setText(registrosChecks.getCheckBox_Piso_interior2());
            holder.txtPiso3.setText(registrosChecks.getCheckBox_Piso_interior3());
            holder.txtPortas.setText(registrosChecks.getCheckBox_Portas());
            holder.txtPortas2.setText(registrosChecks.getCheckBox_Portas2());
            holder.txtPortas3.setText(registrosChecks.getCheckBox_Portas3());
            holder.txtParedes.setText(registrosChecks.getCheckBox_Paredes());
            holder.txtParedes2.setText(registrosChecks.getCheckBox_Paredes2());
            holder.txtParedes3.setText(registrosChecks.getCheckBox_Paredes3());
            holder.txtTeto.setText(registrosChecks.getCheckBox_Teto());
            holder.txtTeto2.setText(registrosChecks.getCheckBox_Teto2());
            holder.txtTeto3.setText(registrosChecks.getCheckBox_Teto3());
            holder.txtPDianteira.setText(registrosChecks.getCheckBox_Parede_dianteira());
            holder.txtPDianteira2.setText(registrosChecks.getCheckBox_Parede_dianteira2());
            holder.txtPDianteira3.setText(registrosChecks.getCheckBox_Parede_dianteira3());
            holder.txtUnidade.setText(registrosChecks.getCheckBox_Refrigerador());
            holder.txtUnidade2.setText(registrosChecks.getCheckBox_Refrigerador2());
            holder.txtUnidade3.setText(registrosChecks.getCheckBox_Refrigerador3());
            holder.txtEscapamento.setText(registrosChecks.getCheckBox_Escapamento());
            holder.txtEscapamento2.setText(registrosChecks.getCheckBox_Escapamento2());
            holder.txtEscapamento3.setText(registrosChecks.getCheckBox_Escapamento3());


    }


    @Override
    public int getItemCount() {
            return registros_checks.size();
            }

    class My_Holder extends RecyclerView.ViewHolder {
        TextView txtNome,txtData,txtPlaca,txtNumero,txtLacreOEA,txtTransportadora,txtPlacaCarreta,txtlacre,
                txtParachoque,txtMotor,txtCalotas,txtPiso_Cabine,txtTanqueC,txtCabine,txtTanquesAr,txtEixos,
                txtParachoque2,txtMotor2,txtCalotas2,txtPiso_Cabine2,txtTanqueC2,txtCabine2,txtTanquesAr2,txtEixos2,
                txtParachoque3,txtMotor3,txtCalotas3,txtPiso_Cabine3,txtTanqueC3,txtCabine3,txtTanquesAr3,txtEixos3,
                txtQuintaRoda, txtExterior, txtPiso, txtPortas, txtParedes, txtTeto, txtPDianteira,txtUnidade,
                txtEscapamento,txtQuintaRoda2, txtExterior2, txtPiso2, txtPortas2, txtParedes2, txtTeto2,
                txtPDianteira2,txtUnidade2, txtEscapamento2,txtQuintaRoda3, txtExterior3, txtPiso3, txtPortas3,
                txtParedes3, txtTeto3, txtPDianteira3, txtUnidade3, txtEscapamento3;

        public My_Holder (View itemView ) {
            super( itemView );
            txtNumero = itemView.findViewById(R.id.id_numero_p);
            txtData = itemView.findViewById(R.id.dataHora);
            txtTransportadora = itemView.findViewById(R.id.Transportadora);
            txtNome = itemView.findViewById(R.id.Nome_motorista);
            txtPlaca = itemView.findViewById(R.id.txt_PLACA);
            txtPlacaCarreta = itemView.findViewById(R.id.txt_carreta);
            txtLacreOEA = itemView.findViewById(R.id.txt_lacreOEA);
            txtlacre = itemView.findViewById(R.id.txt_numero_lacre);

            txtParachoque= itemView.findViewById(R.id.txt_posterior_parachoques);
            txtParachoque2= itemView.findViewById(R.id.txt_posterior_parachoques2);
            txtParachoque3= itemView.findViewById(R.id.txt_posterior_parachoques3);
            txtMotor= itemView.findViewById(R.id.txt_Motor);
            txtMotor2= itemView.findViewById(R.id.txt_Motor2);
            txtMotor3= itemView.findViewById(R.id.txt_Motor3);
            txtCalotas= itemView.findViewById(R.id.txtPneus);
            txtCalotas2= itemView.findViewById(R.id.txtPneus2);
            txtCalotas3= itemView.findViewById(R.id.txtPneus3);
            txtPiso_Cabine= itemView.findViewById(R.id.txt_piso_cabine);
            txtPiso_Cabine2= itemView.findViewById(R.id.txt_piso_cabine2);
            txtPiso_Cabine3= itemView.findViewById(R.id.txt_piso_cabine3);
            txtTanqueC= itemView.findViewById(R.id.txt_tanque_combustivel);
            txtTanqueC2= itemView.findViewById(R.id.txt_tanque_combustivel2);
            txtTanqueC3= itemView.findViewById(R.id.txt_tanque_combustivel3);
            txtCabine= itemView.findViewById(R.id.txt_Cabine);
            txtCabine2= itemView.findViewById(R.id.txt_Cabine2);
            txtCabine3= itemView.findViewById(R.id.txt_Cabine3);
            txtTanquesAr = itemView.findViewById(R.id.txt_tanques);
            txtTanquesAr2 = itemView.findViewById(R.id.txt_tanques2);
            txtTanquesAr3 = itemView.findViewById(R.id.txt_tanques3);
            txtEixos= itemView.findViewById(R.id.txt_eixos_transmissao);
            txtEixos2= itemView.findViewById(R.id.txt_eixos_transmissao2);
            txtEixos3= itemView.findViewById(R.id.txt_eixos_transmissao3);

            txtQuintaRoda= itemView.findViewById(R.id.txt_quinta_roda);
            txtQuintaRoda2= itemView.findViewById(R.id.txt_quinta_roda2);
            txtQuintaRoda3= itemView.findViewById(R.id.txt_quinta_roda3);
            txtExterior = itemView.findViewById(R.id.txt_Exterior_abaixo);
            txtExterior2 = itemView.findViewById(R.id.txt_Exterior_abaixo2);
            txtExterior3 = itemView.findViewById(R.id.txt_Exterior_abaixo3);
            txtPiso = itemView.findViewById(R.id.txt_pisoIn);
            txtPiso2 = itemView.findViewById(R.id.txt_pisoIn2);
            txtPiso3 = itemView.findViewById(R.id.txt_pisoIn3);
            txtPortas = itemView.findViewById(R.id.txt_Porta);
            txtPortas2 = itemView.findViewById(R.id.txt_Porta2);
            txtPortas3 = itemView.findViewById(R.id.txt_Porta3);
            txtParedes = itemView.findViewById(R.id.txt_Paredes);
            txtParedes2 = itemView.findViewById(R.id.txt_Paredes2);
            txtParedes3 = itemView.findViewById(R.id.txt_Paredes3);
            txtTeto = itemView.findViewById(R.id.txt_Teto);
            txtTeto2 = itemView.findViewById(R.id.txt_Teto2);
            txtTeto3 = itemView.findViewById(R.id.txt_Teto3);
            txtPDianteira = itemView.findViewById(R.id.txt_pDianteira);
            txtPDianteira2 = itemView.findViewById(R.id.txt_pDianteira2);
            txtPDianteira3 = itemView.findViewById(R.id.txt_pDianteira3);
            txtUnidade = itemView.findViewById(R.id.txt_unidadeR);
            txtUnidade2 = itemView.findViewById(R.id.txt_unidadeR2);
            txtUnidade3 = itemView.findViewById(R.id.txt_unidadeR3);
            txtEscapamento = itemView.findViewById(R.id.txtEscapamento);
            txtEscapamento2 = itemView.findViewById(R.id.txtEscapamento2);
            txtEscapamento3 = itemView.findViewById(R.id.Escapamento3);


        }
    }
}
