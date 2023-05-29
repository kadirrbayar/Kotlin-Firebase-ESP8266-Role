package com.kadir.akvaryumkontrol

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kadir.akvaryumkontrol.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val database = FirebaseDatabase.getInstance()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val uyari = AlertDialog.Builder(this)
        uyari.setTitle("Akvaryum Kontrol")
        uyari.setPositiveButton("Tamam", { dialog, which -> })

        binding.button4.setOnClickListener { //otomatik butonu
            try {
                val acilis = binding.acilmaBox.text.toString().toInt()
                val kapanis = binding.kapanmaBox.text.toString().toInt()
                if (acilis <= 24 && kapanis <= 24) {
                    database.getReference("acilis").setValue(acilis.toString())
                    database.getReference("kapanis").setValue(kapanis.toString())
                    database.getReference("otomatik").setValue("1")
                    uyari.setMessage("Akvaryum lambası otomatik çalışma için ayarlandı. Şimdi belirlenen saatlerde otomatik olarak açılıp kapancaktır")
                } else {
                    uyari.setMessage("Lütfen 24 Saat aralığında geçerli bir zaman dilimi giriniz.")
                }
            } catch (e: java.lang.Exception) {
                uyari.setMessage("Saat bilgileri boş bırakılamaz.")
            }
            binding.acilmaBox.text.clear()
            binding.kapanmaBox.text.clear()
            uyari.create().show()
        }
        binding.button.setOnClickListener { // kapa butonu
            database.getReference("otomatik").setValue("0")
            database.getReference("lamba").setValue("0") //kapat
            uyari.setMessage("Akvaryum lambası kapatıldı")
            uyari.create().show()
        }
        binding.button3.setOnClickListener { //aç butonu
            database.getReference("otomatik").setValue("0")
            database.getReference("lamba").setValue("1") //aç
            uyari.setMessage("Akvaryum lambası açıldı")
            uyari.create().show()
        }

        val datalar = object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                val lamp = snapshot.child("lamba").value
                val otomatik = snapshot.child("otomatik").value
                val acilis = snapshot.child("acilis").value
                val kapanis = snapshot.child("kapanis").value
                binding.acilisText.text = acilis.toString()
                binding.kapanisText.text = kapanis.toString()

                if (lamp == "1") {
                    binding.lambaText.text = "Açık"
                    binding.button3.isEnabled = false
                    binding.button.isEnabled = true
                } else if (lamp == "0") {
                    binding.lambaText.text = "Kapalı"
                    binding.button3.isEnabled = true
                    binding.button.isEnabled = false
                }

                if (otomatik == "1") {
                    binding.otomatikText.text = "Açık"
                } else if (otomatik == "0") {
                    binding.otomatikText.text = "Kapalı"
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        }
        database.reference.addValueEventListener(datalar)
    }
}

