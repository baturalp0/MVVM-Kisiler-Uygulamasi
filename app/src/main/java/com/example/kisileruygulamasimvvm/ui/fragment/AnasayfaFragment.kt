package com.example.kisileruygulamasimvvm.ui.adapter.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kisileruygulamasimvvm.R
import com.example.kisileruygulamasimvvm.data.entity.Kisiler
import com.example.kisileruygulamasimvvm.databinding.ActivityMainBinding.inflate
import com.example.kisileruygulamasimvvm.databinding.FragmentAnasayfaBinding
import com.example.kisileruygulamasimvvm.databinding.FragmentAnasayfaBinding.inflate
import com.example.kisileruygulamasimvvm.ui.adapter.KisilerAdapter


class AnasayfaFragment : Fragment() ,SearchView.OnQueryTextListener {
    //Fragment() -> Fragment sayfası olmasından ötürü.
    //SearchView.OnQueryTextListener -> toolbar'daki search özelliğinden dolayı.
    private lateinit var tasarim:FragmentAnasayfaBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = FragmentAnasayfaBinding.inflate(inflater, container, false)

        tasarim.toolbarAnasayfa.title="Kişiler"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa) //Bu kodlama gerekliymiş. Bu şekilde toolbarAnasayfaya sen bir action bar sın demiş olduk. arama özelliği için bu önemliymiş


        tasarim.rv.layoutManager = LinearLayoutManager(requireContext()) //rv için kullanılacak layoutu tanıttık.

        val kisilerListesi = ArrayList<Kisiler>()
        val k1 = Kisiler(1,"Ahmet","1111")
        val k2 = Kisiler(1,"Zeynep","2222")
        val k3 = Kisiler(1,"Beyza","3333")
        kisilerListesi.add(k1)
        kisilerListesi.add(k2)
        kisilerListesi.add(k3)

        val adapter = KisilerAdapter(requireContext(),kisilerListesi)
        tasarim.rv.adapter = adapter



        tasarim.fab.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.kisiKayitGecis) //fab butonuna tıklanınca sayfa geçişi
        }

        requireActivity().addMenuProvider(object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu,menu) //toolbarMenu'yü bağlamış olduk.

                val item = menu.findItem(R.id.action_ara) //Arama yapmak için oluşturduğumuz action_ara id'li menu_item'a ulaştık.
                val searchView = item.actionView as SearchView //Sen searchView özelliğine ait bir yapısın dedik.
                searchView.setOnQueryTextListener(this@AnasayfaFragment)

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        },viewLifecycleOwner,Lifecycle.State.RESUMED) //Burası android yaşam döngüsünden dolayı arayüzde sıkıntı yaşamamak için yapılan bir kodlama şimdilik ezbere bilsen yeterli.







        return tasarim.root
    }

    override fun onQueryTextSubmit(query: String): Boolean { //Arama ikonuna basınca tüm veriyi arar.
        ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean { //her harfe/karaktere basınca aramayı yeniler.
        ara(newText)
        return true
    }

    fun ara(aramaKelimesi:String){
        Log.e("kişi Ara",aramaKelimesi)
    }

    override fun onResume() {
        super.onResume()
        Log.e("Kişi Anasayfa","Dönüldü")
    }

}