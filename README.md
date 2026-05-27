<p align="center">
  <img src="app/src/main/res/drawable/logo_lab16.png" alt="Logo" width="80">
</p>

<h1 align="center">ServiceChronometreJava — LAB 16</h1>
<p align="center">
  <strong>Author: Chaimaa Elgadaoui</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Android-API%2024%2B-7F1734?style=for-the-badge&logo=android" alt="Android API">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white&color=7F1734" alt="Java">
  <img src="https://img.shields.io/badge/Status-Completed-7F1734?style=for-the-badge" alt="Status Completed">
</p>

## Présentation du projet

**ServiceChronometreJava** est une application Android développée en Java permettant d’exécuter un **chronomètre en arrière-plan** grâce à un **Foreground Service**.

L’application continue de fonctionner même lorsqu’elle est réduite et affiche une **notification persistante** avec le temps en cours.

---

## 🎥 Démo

La vidéo de démonstration est disponible dans le projet :
lab16/demo_lab16.mp4
## 📂 Structure du projet

```text
ServiceChronometreJava/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/ma/ensa/servicechronometrejava/
│   │   │   │   ├── MainActivity.java
│   │   │   │   └── ChronometreService.java
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml
│   │   │   │   └── drawable/
│   │   │   └── AndroidManifest.xml
│   └── build.gradle.kts
├── gradlew
└── settings.gradle.kts

```

## Concepts utilisés
* Foreground Service
* Bound Service
* ScheduledExecutorService
* Handler + Runnable
* NotificationChannel (API 26+)
* START_STICKY
---

## 🛠 Installation
git clone https://github.com/chaimaaelgadaoui/lab16.git
Ouvrir le projet dans Android Studio
Synchroniser Gradle
Lancer sur un émulateur ou téléphone (API 24+)
---

#
---

<p align="center">
  <b>Chaimaa ElGADAOUI</b><br>
  Programmation Mobile : Android avec Java
</p>
