package com.free.simplecal

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*


class MainActivity : AppCompatActivity(),View.OnClickListener,View.OnLongClickListener, TextWatcher {


    var calformular:String =""
    var lastreult:String="---"
    var decima:DecimalFormat= DecimalFormat("##,##0.########")
    var decimasymbol=DecimalFormatSymbols(Locale.getDefault())


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        calformular=formula.text.toString().replace("²","^2").replace("√(","sqrt(")
        try {
            val re=decima.format(ExtendedDoubleEvaluator().evaluate(calformular)).toString()
            result.setTextColor(ContextCompat.getColor(this,R.color.whilte))
            result.setText(re)
            lastreult=re
        }catch (e:Exception){
            result.setTextColor(ContextCompat.getColor(this,R.color.nores))
            result.setText(lastreult)
        }
    }

    override fun onLongClick(v: View?): Boolean {
        when(v?.id){
            R.id.btn_clean -> formula.setText("")
        }
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_0 -> input("0")
            R.id.btn_1 -> input("1")
            R.id.btn_2 -> input("2")
            R.id.btn_3 -> input("3")
            R.id.btn_4 -> input("4")
            R.id.btn_5 -> input("5")
            R.id.btn_6 -> input("6")
            R.id.btn_7 -> input("7")
            R.id.btn_8 -> input("8")
            R.id.btn_9 -> input("9")
            R.id.btn_decimal -> input(".")
            R.id.btn_opbrar -> input("(")
            R.id.btn_enbrar -> input(")")
            R.id.btn_root -> input("√()")
            R.id.btn_square -> input("²")
            R.id.btn_plus -> input("+")
            R.id.btn_minus -> input("-")
            R.id.btn_multiply -> input("*")
            R.id.btn_divide -> input("/")
            R.id.btn_clean -> {
                val start=Math.max(formula.selectionStart,0)
                val end=Math.max(formula.selectionEnd,0)
                val position=Math.min(start,end)
                if (position>0) formula.text.delete(position-1,position)
            }
        }
    }

    fun input(show:String){
        val start=Math.max(formula.selectionStart,0)
        val end=Math.max(formula.selectionEnd,0)
        formula.text.replace(Math.min(start,end),Math.max(start,end),show,0,show.length)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this,"ca-app-pub-1920706188935452~1731721013")
        val adsrequest=AdRequest.Builder().build()
        adview.loadAd(adsrequest)
        btn_opbrar.setOnClickListener(this)
        btn_enbrar.setOnClickListener(this)
        btn_root.setOnClickListener(this)
        btn_square.setOnClickListener(this)
        btn_0.setOnClickListener(this)
        btn_1.setOnClickListener(this)
        btn_2.setOnClickListener(this)
        btn_3.setOnClickListener(this)
        btn_4.setOnClickListener(this)
        btn_5.setOnClickListener(this)
        btn_6.setOnClickListener(this)
        btn_7.setOnClickListener(this)
        btn_8.setOnClickListener(this)
        btn_9.setOnClickListener(this)
        btn_divide.setOnClickListener(this)
        btn_multiply.setOnClickListener(this)
        btn_minus.setOnClickListener(this)
        btn_plus.setOnClickListener(this)
        btn_decimal.setOnClickListener(this)
        btn_clean.setOnClickListener(this)
        btn_clean.setOnLongClickListener(this)
        formula.showSoftInputOnFocus=false
        result.showSoftInputOnFocus=false

        formula.addTextChangedListener(this)
        decimasymbol.groupingSeparator=' '
        decimasymbol.decimalSeparator='.'
        decima.decimalFormatSymbols=decimasymbol

    }

}
