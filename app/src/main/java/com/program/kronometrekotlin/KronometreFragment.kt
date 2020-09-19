package com.program.kronometrekotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kronometre.*
import kotlinx.android.synthetic.main.item_row.*

class KronometreFragment : Fragment(R.layout.fragment_kronometre) {
    lateinit var runnable: Runnable
    var handler = Handler(Looper.myLooper()!!)
    var saniye = 0
    var dakika = 0
    var saat = 0
    var saat1 = 0
    var dakika1 = 0
    var saniye1 = 0
    var saat2 = 0
    var dakika2 = 0
    var saniye2 = 0
    var toplam = 0
    var toplam1 = 0
    var lapList = ArrayList<String>()
    private lateinit var adapter: KronometreAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saat2 = saat
        dakika2 = dakika
        saniye2 = saniye
        buttonLap.visibility = View.INVISIBLE
        buttonDurdur.visibility = View.INVISIBLE
        buttonSifirla.visibility = View.INVISIBLE
        adapter = KronometreAdapter(lapList)
        recycler_View_Kronometre.adapter = adapter
        buttonLap.setOnClickListener {
            toplam = saat*3600 + dakika*60 + saniye
            toplam1 = saat1*3600 + dakika1*60 + saniye1
            toplam = toplam - toplam1
            saat2 = toplam/3600
            dakika2 = (toplam%3600)/60
            saniye2 = (toplam%3600)%60
            lapList.add("${saat}:${dakika}:${saniye}   ${saat2}:${dakika2}:${saniye2}")
            saat1 = saat
            dakika1 = dakika
            saniye1 = saniye
            adapter.notifyDataSetChanged()
        }

        buttonBaslat.setOnClickListener{
            runnable = object: Runnable{
                override fun run() {
                    saniye ++

                    if(saniye > 59){
                        saniye = 0
                        dakika++
                        if(dakika < 10){
                            val dk = "0${dakika}"
                            textViewDakikaText.text = dk
                        }else{
                            textViewDakikaText.text = dakika.toString()
                        }
                    }
                    if(saniye < 10){
                        val sn = "0${saniye}"
                        textViewSaniyeText.text = sn
                    }else{
                        textViewSaniyeText.text = saniye.toString()
                    }

                    if(dakika > 59){
                        dakika = 0
                        val dk = "0${dakika}"
                        textViewDakikaText.text = dk
                        saat ++
                        if(saat < 10){
                            val hr = "0${saat}"
                            textViewSaatText.text = hr
                        }else{
                            textViewSaatText.text = saat.toString()
                        }
                    }

                    handler.postDelayed(runnable,1000)
                }
            }
            handler.post(runnable)
            buttonBaslat.visibility = View.INVISIBLE
            buttonDurdur.visibility = View.VISIBLE
            buttonSifirla.visibility = View.VISIBLE
            buttonLap.visibility = View.VISIBLE
        }

        buttonDurdur.setOnClickListener {
            handler.removeCallbacks(runnable)
            buttonBaslat.text = "Devam Et"
            buttonBaslat.visibility = View.VISIBLE
            buttonDurdur.visibility = View.INVISIBLE
        }

        buttonSifirla.setOnClickListener {
            saniye = 0
            dakika = 0
            saat = 0
            saniye1 = 0
            dakika1 = 0
            saat1 = 0
            textViewSaniyeText.text = "00"
            textViewDakikaText.text = "00"
            textViewSaatText.text = "00"
            lapList.clear()
            handler.removeCallbacks(runnable)
            buttonBaslat.text = "Başlat"
            buttonBaslat.visibility = View.VISIBLE
            buttonSifirla.visibility = View.INVISIBLE
            buttonLap.visibility = View.INVISIBLE
            buttonDurdur.visibility = View.INVISIBLE
        }
    }
}