<h1 align= center><b>⭐️ ESP8266 Home Automation System Android App ⭐️</b></h1>
<h3 align = center> ESP8266 Kullanarak nesnelerin interneti için hazırlanmış bir lamba kontrol uygulamasıdır.</h3>

## ✨ <a name="features">Tanıtım</a>

ESP8266 ile nesnelerin interneti için hazırlanmış bir projedir. Android uygulama üzerinden istediğiniz konumdan istediğiniz bir röleyi kontrol edebilirsiniz.
Röleyi istediğiniz bir ürüne bağlayarak onu uzaktan açıp kapatabilirsiniz. Bu projede akvaryum lambasını kontrol etmek için kullanılmıştır. Bu sayede lambayı istediğiniz konumdan açıp kapatabilirsiniz ayrıca otomatik olarak çalışması için de ayarlabilirsiniz.
Firebase Real Time Storage kullanarak hazırlanmıştır. Firebase Database ile veriler saklanmaktadır.

### 🌄 Uygulama Görselleri

<p align="center">
  <img src="https://telegra.ph/file/ba1fb3df14c2078123c36.png">
  <img src="https://telegra.ph/file/431bb7347db616f4233c3.png">
</p>

## ⚒ <a name="configs">Kurulum</a>

    1-) Firebase.google.com adresine girin ve bir firebase hesabı oluşturun.
    2-) Daha sonra Firebase sitesinden aldığınız google_services.json dosyanızı uygulamanızın App klasörüne kopyalayın.
    3-) Firebase sitesinden bir Real Time Storage oluşturun.
    4-) ESP8266 Codes Klasöründe yer alan kodlarda Firebase Token, Firebase Url, ve Wifi Bilgileri bölümünü düzenleyin.
    5-) İlgili kütüphaneleri arduino üzerinden yükledikten sonra ESP8266'ya kodları yükleyin.
	6-) Röleyi ESP8266 da D1 pinine bağlayın.	
	7-) Şimdi android uygulamanızı derleyip çalıştırın ve rölenizi uzaktan istediğiniz gibi kontrol edebilirsiniz.
	
## 📃 <a name="license">Lisans</a>

 - Copyright (C) 2023-Present by [kadir](github.com/kadir008) ❤️️
 - Licensed under the terms of the [GNU GENERAL PUBLIC LICENSE](https://github.com/kadir008/esp8266-iot-android/blob/main/LICENSE)