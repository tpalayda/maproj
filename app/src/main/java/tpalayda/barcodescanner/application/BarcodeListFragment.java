package tpalayda.barcodescanner.application;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.vision.barcode.Barcode;

import org.w3c.dom.Text;

import java.util.List;

import tpalayda.barcodescanner.R;

public class BarcodeListFragment extends Fragment {
    private RecyclerView m_recyclerView;
    private BarcodeAdapter m_adapter;
    private int m_adapterIndex;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_barcode_list,container,false);

        m_recyclerView = view.findViewById(R.id.my_recycler_view);
        m_recyclerView.setHasFixedSize(true);
        m_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }
    private class BarcodeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView m_barcodeTextView;
        private BarcodeInf m_barcodeInf;

        public BarcodeHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_barcodes,parent,false));
            itemView.setOnClickListener(this);
            m_barcodeTextView = itemView.findViewById(R.id.barcodes_id);
        }
        @Override
        public void onClick(View view) {

        }
        public void bind(BarcodeInf barcodeInf){
            m_barcodeInf = barcodeInf;
            m_barcodeTextView.setText(m_barcodeInf.getBarcodeID());
        }
    }
    private class BarcodeAdapter extends RecyclerView.Adapter<BarcodeHolder> {

        private List<BarcodeInf> m_barcodes;
        public BarcodeAdapter(List<BarcodeInf> barcodes){
            m_barcodes = barcodes;
        }
        @Override
        public BarcodeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BarcodeHolder(LayoutInflater.from(getActivity()),parent);
        }

        @Override
        public void onBindViewHolder(BarcodeHolder holder, int position) {
            BarcodeInf barcodeInf = m_barcodes.get(position);
            holder.bind(barcodeInf);
        }

        @Override
        public int getItemCount() {
            return m_barcodes.size();
        }
        public void setBarcodes(List<BarcodeInf> barcodeInfs){
            m_barcodes = barcodeInfs;
        }
    }
    private void updateUI(){
        List<BarcodeInf> barcodes = BarcodeBank.getInstance(getActivity()).getBarcodes();

        if(m_adapter == null){
            m_adapter = new BarcodeAdapter(barcodes);
            m_recyclerView.setAdapter(m_adapter);
        }
        else{
            m_adapter.setBarcodes(barcodes);
            m_adapter.notifyDataSetChanged();
        }
    }
}
