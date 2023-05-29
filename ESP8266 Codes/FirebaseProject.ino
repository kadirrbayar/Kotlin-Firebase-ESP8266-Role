#include <ESP8266WiFi.h>
#include <TimeLib.h>
#include <FirebaseArduino.h>

#define FIREBASE_HOST "Firebase Database Url Adresi"
#define FIREBASE_AUTH "Firebase Token"
#define WIFI_SSID "Wifi Adı"
#define WIFI_PASSWORD "Wifi Şifresi"
#define Relay1 5
int lamba;

void setup() {
  Serial.begin(115200);
  pinMode(Relay1, OUTPUT);
  digitalWrite(Relay1, HIGH);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
  }
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  setSyncProvider(getNtpTime);
}

time_t getNtpTime() {
  const char* ntpServer = "tr.pool.ntp.org";
  const int gmtOffset_sec = 3 * 3600;
  const int daylightOffset_sec = 0;
  configTime(gmtOffset_sec, daylightOffset_sec, ntpServer);
  time_t now = time(nullptr);
  while (now < 100000) {
    delay(500);
    now = time(nullptr);
  }
  return now;
}

void kontrolet() {
  time_t t = now();
  int acilis = Firebase.getString("acilis").toInt();
  int kapanis = Firebase.getString("kapanis").toInt();
  int kapaniss = (kapanis == 24) ? 0 : kapanis;
  int aciliss = (acilis == 0) ? 24 : acilis;
  if (hour(t) >= acilis && hour(t) < kapanis && lamba == 0) {
    digitalWrite(Relay1, LOW);
    Firebase.setString("lamba", "1");
  } else if (hour(t) >= kapaniss && hour(t) < aciliss && lamba == 1) {
    digitalWrite(Relay1, HIGH);
    Firebase.setString("lamba", "0");
  }
}

void loop() {
  lamba = Firebase.getString("lamba").toInt();
  int otomatik = Firebase.getString("otomatik").toInt();
  if (otomatik == 1) 
  {
    kontrolet();
  }
  if (otomatik == 0) 
  {
    if (lamba == 0) 
    {
      digitalWrite(Relay1, HIGH);
    } 
    if (lamba == 1) 
    {
      digitalWrite(Relay1, LOW);
    }
  }
  delay(2000); 
}
