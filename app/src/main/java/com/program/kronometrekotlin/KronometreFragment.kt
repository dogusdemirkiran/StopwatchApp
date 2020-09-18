package com.program.kronometrekotlin

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
    var lapList = ArrayList<String>()
    private lateinit var adapter: KronometreAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = KronometreAdapter(lapList)
        recycler_View_Kronometre.adapter = adapter
        buttonLap.setOnClickListener {
            val saatLap: String = textViewSaatText.text.toString()
            val dakikaLap: String = textViewDakikaText.text.toString()
            val saniyeLap: String = textViewSaniyeText.text.toString()
            lapList.add("${saat}:${dakika}:${saniye}")
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
        }

        buttonDurdur.setOnClickListener {
            handler.removeCallbacks(runnable)
            buttonBaslat.visibility = View.VISIBLE
        }

        buttonSifirla.setOnClickListener {
            saniye = 0
            dakika = 0
            saat = 0
            textViewSaniyeText.text = "00"
            textViewDakikaText.text = "00"
            textViewSaatText.text = "00"
            lapList.clear()
            handler.removeCallbacks(runnable)
        }
    }

    fun sqlVeriKaydet(){
    }

    fun sqlVeriAl(){
    }
}