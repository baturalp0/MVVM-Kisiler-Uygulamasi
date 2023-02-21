package com.example.kisileruygulamasimvvm.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kisileruygulamasimvvm.data.entity.Kisiler
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class KisilerDaoRepository(var refKisiler:DatabaseReference) { //bu yapı sayesinde DataBase'den okuma işlemini gerçekleştirebileceğiz.
    //kisilerListesi LiveData dahilinde
    var kisilerListesi:MutableLiveData<List<Kisiler>>

    init { //Bu sayfadan bir nesne oluşturulduğunda yapılacaklar.(?)
        kisilerListesi = MutableLiveData()
    }

    fun kisileriGetir() :MutableLiveData<List<Kisiler>> {
        return kisilerListesi
    }

    fun kisiKayit(kisi_ad:String,kisi_tel:String){
        val yeniKisi = Kisiler("",kisi_ad,kisi_tel)
        refKisiler.push().setValue(yeniKisi)
    }

    fun kisiGuncelle(kisi_id:String,kisi_ad:String,kisi_tel:String){
        val bilgiler = HashMap<String,Any>()
        bilgiler["kisi_ad"] = kisi_ad
        bilgiler["kisi_tel"] = kisi_tel
        refKisiler.child(kisi_id).updateChildren(bilgiler)

    }

    fun kisiAra(aramaKelimesi:String){
        refKisiler.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste = ArrayList<Kisiler>()

                for (d in snapshot.children){
                    val kisi= d.getValue(Kisiler::class.java) //gelen veriyi otomatik Kisiler sınıfından veriye çeviricek
                    if (kisi != null) {
                        if(kisi.kisi_ad!!.lowercase().contains(aramaKelimesi.lowercase())){
                            kisi.kisi_id = d.key
                            liste.add(kisi)
                        }

                    }
                }

                kisilerListesi.value = liste

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun kisiSil(kisi_id:String){
        refKisiler.child(kisi_id).removeValue()
    }

    //LiveData kullandığımız kısıma geldik

    fun tumKisileriAl(){
        refKisiler.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste = ArrayList<Kisiler>()

                for (d in snapshot.children){
                    val kisi= d.getValue(Kisiler::class.java) //gelen veriyi otomatik Kisiler sınıfından veriye çeviricek
                    if (kisi != null){ kisi.kisi_id = d.key
                        liste.add(kisi)
                    }
                }

                kisilerListesi.value = liste

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

}