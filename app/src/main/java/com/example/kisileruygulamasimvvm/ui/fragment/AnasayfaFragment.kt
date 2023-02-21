package com.example.kisileruygulamasimvvm.ui.adapter.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kisileruygulamasimvvm.R
import com.example.kisileruygulamasimvvm.data.entity.Kisiler
import com.example.kisileruygulamasimvvm.databinding.ActivityMainBinding.inflate
import com.example.kisileruygulamasimvvm.databinding.FragmentAnasayfaBinding
import com.example.kisileruygulamasimvvm.databinding.FragmentAnasayfaBinding.inflate
import com.example.kisileruygulamasimvvm.ui.adapter.KisilerAdapter
import com.example.kisileruygulamasimvvm.ui.viewmodel.AnasayfaViewModel
import com.example.kisileruygulamasimvvm.ui.viewmodel.KisiKayitViewModel
import com.example.kisileruygulamasimvvm.util.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() ,SearchView.OnQueryTextListener {
    //Fragment() -> Fragment sayfası olmasından ötürü.
    //SearchView.OnQueryTextListener -> toolbar'daki search özelliğinden dolayı.
    private lateinit var tasarim:FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        tasarim.anasayfaFragment = this//anasayfaFragment'ı -> fragment_anasayfa.xml içinde oluşturduk. Bu fab butonunun çalışması için gerekli yapı.(Aradaki bağlantıyı kuruyor.)
        tasarim.anasayfaToolbarBaslik="Kişiler" //anasayfaToolbarBaslik'ı -> fragment_anasayfa.xml içinde oluşturduk
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa) //Bu kodlama gerekliymiş. Bu şekilde toolbarAnasayfaya sen bir action bar sın demiş olduk. arama özelliği için bu önemliymiş

        viewModel.kisilerListesi.observe(viewLifecycleOwner){
            val adapter = KisilerAdapter(requireContext(),it,viewModel)
            tasarim.kisilerAdapter = adapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun fabTikla(it:View){
        Navigation.gecisYap(it,R.id.kisiKayitGecis) //Extensions dosyasında oluşturduğumuz fonksiyonu kullanıyoruz. fab butonuna tıklanınca sayfa geçişi sağlanıyor.
    }

    override fun onQueryTextSubmit(query: String): Boolean { //Arama ikonuna basınca tüm veriyi arar.
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean { //her harfe/karaktere basınca aramayı yeniler.
        viewModel.ara(newText)
        return true
    }


}