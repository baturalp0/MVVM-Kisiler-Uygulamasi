package com.example.kisileruygulamasimvvm.ui.adapter.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kisileruygulamasimvvm.R
import com.example.kisileruygulamasimvvm.databinding.FragmentKisiKayitBinding


class KisiKayitFragment : Fragment() {
    private lateinit var tasarim: FragmentKisiKayitBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = FragmentKisiKayitBinding.inflate(inflater, container, false)

        tasarim.toolbarKisiKayit.title = "Kişi Kayıt"

        tasarim.buttonKaydet.setOnClickListener{
            val kisi_ad = tasarim.editTextKisiAd.text.toString()
            val kisi_tel = tasarim.editTextKisiTel.text.toString()

            kayit(kisi_ad,kisi_tel)
        }

        return tasarim.root
    }

    fun kayit(kisi_ad:String,kisi_tel:String){
        Log.e("Kişi Kayıt","$kisi_ad - $kisi_tel")
    }


}