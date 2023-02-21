package com.example.kisileruygulamasimvvm.ui.adapter.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.kisileruygulamasimvvm.R
import com.example.kisileruygulamasimvvm.databinding.FragmentKisiDetayBinding
import com.example.kisileruygulamasimvvm.ui.viewmodel.AnasayfaViewModel
import com.example.kisileruygulamasimvvm.ui.viewmodel.KisiDetayViewModel
import com.example.kisileruygulamasimvvm.ui.viewmodel.KisiKayitViewModel

class KisiDetayFragment : Fragment() {
    private lateinit var tasarim:FragmentKisiDetayBinding
    private lateinit var viewModel: KisiDetayViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_kisi_detay, container, false) //DataBinding için gerekli olan kurulum.
        tasarim.kisiDetayFragment = this //kisiDetayFragment'ı -> fragment_kisi_detay.xml içinde oluşturduk. Bu Güncelleme butonunun çalışması için gerekli yapı.(Aradaki bağlantıyı kuruyor.)
        tasarim.kisiDetayToolbarBaslik = "Kişi Detay" //kisiDetayToolbarBaslik'ı -> fragment_kisi_detay.xml içinde oluşturduk

        val bundle:KisiDetayFragmentArgs by navArgs()
        //bundle bizim nesne alacağımız değişken olacak. KisiDetayFragmentArgs sınıfı ise navigation(main_activity_nav)
        //içinde eklediğimiz argument'ten dolayı oluşan bir sınıf. (argument'te kisi adında nesne verisi alacağız diye haber vermiştik.)

        val gelenKisi = bundle.kisi //AnaSayfadaki kişi nesnesinden aldığımız verileri gelenKisi değişkenine aktardık.

        tasarim.kisiNesnesi = gelenKisi

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : KisiDetayViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun buttonGuncelle(kisi_id:Int,kisi_ad:String,kisi_tel:String){
        viewModel.guncelle(kisi_id,kisi_ad,kisi_tel)
    }

}