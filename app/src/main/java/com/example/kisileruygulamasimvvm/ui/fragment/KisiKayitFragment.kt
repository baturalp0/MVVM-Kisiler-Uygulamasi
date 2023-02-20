package com.example.kisileruygulamasimvvm.ui.adapter.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.kisileruygulamasimvvm.R
import com.example.kisileruygulamasimvvm.databinding.FragmentKisiKayitBinding


class KisiKayitFragment : Fragment() {
    private lateinit var tasarim: FragmentKisiKayitBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_kisi_kayit, container, false) //DataBinding için gerekli olan kurulum.
        tasarim.kisiKayitFragment = this //kisiKayitFragment'ı -> fragment_kisi_kayit.xml içinde oluşturduk.  Bu kaydetme butonunun çalışması için gerekli yapı.(Aradaki bağlantıyı kuruyor.).
        tasarim.kisiKayitToolbarBaslik = "Kişi Kayıt" //kisiKayitToolbarBaslik'ı -> fragment_kisi_kayit.xml içinde oluşturduk


        return tasarim.root
    }

    fun buttonKaydet(kisi_ad:String,kisi_tel:String){
        Log.e("Kişi Kayıt","$kisi_ad - $kisi_tel")
    }


}