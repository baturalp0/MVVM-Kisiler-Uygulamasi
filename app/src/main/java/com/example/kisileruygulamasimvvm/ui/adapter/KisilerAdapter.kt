package com.example.kisileruygulamasimvvm.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.kisileruygulamasimvvm.R
import com.example.kisileruygulamasimvvm.data.entity.Kisiler
import com.example.kisileruygulamasimvvm.databinding.CardTasarimBinding
import com.example.kisileruygulamasimvvm.databinding.FragmentAnasayfaBinding
import com.example.kisileruygulamasimvvm.ui.adapter.fragment.AnasayfaFragmentDirections
import com.google.android.material.snackbar.Snackbar

class KisilerAdapter (var mContext : Context, var kisilerListesi:List<Kisiler>)
    :RecyclerView.Adapter<KisilerAdapter.CardTasarimTutucu>() {
    //mContext tasarımı bağlarken kullanılacak.Önemli

    inner class CardTasarimTutucu(tasarim: CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim:CardTasarimBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu { //card_tasarim'ın ViewBinding kurulumunu yapmamızı sağlayacak
       val layoutInflater = LayoutInflater.from(mContext)
        val tasarim:CardTasarimBinding = DataBindingUtil.inflate(layoutInflater,R.layout.card_tasarim, parent, false)
        return CardTasarimTutucu(tasarim)

    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val kisi = kisilerListesi.get(position)
        val t = holder.tasarim

        t.kisiNesnesi = kisi

        t.satirCard.setOnClickListener{
            val gecis = AnasayfaFragmentDirections.kisiDetayGecis(kisi = kisi)
            Navigation.findNavController(it).navigate(gecis)
        }

        t.imageViewSil.setOnClickListener{
            Snackbar.make(it,"${kisi.kisi_ad} silinin mi?",Snackbar.LENGTH_LONG)
                .setAction("EVET"){
                    Log.e("Kişi Sil",kisi.kisi_id.toString())
                }.show()
        }
    }

    override fun getItemCount(): Int {  //Kaç veri çalıştırılacağını istiyor.
        return kisilerListesi.size
    }


}