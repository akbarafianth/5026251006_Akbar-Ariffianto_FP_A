# Simple Tic-Tac-Toe Game dengan Java Swing, Login, dan Statistik

## Identitas Mahasiswa

Nama: Akbar Ariffianto
NRP: 5026251006
Kelas: A

## Deskripsi Project

Project ini adalah aplikasi game sederhana Tic-Tac-Toe yang dibuat menggunakan Java Swing. Aplikasi ini memiliki fitur login, permainan melawan komputer, pencatatan statistik pemain, dan tampilan Top 5 scorer.

Game ini dimainkan oleh user melawan komputer. User menggunakan simbol **X**, sedangkan komputer menggunakan simbol **O**. User dan komputer akan bergantian mengisi kotak pada papan 3x3. Pemain dinyatakan menang apabila berhasil membuat tiga simbol yang sama dalam satu baris, kolom, atau diagonal.

Project ini juga menggunakan database PostgreSQL untuk menyimpan data login dan statistik pemain. Database dikelola menggunakan DBeaver agar data bisa dicek dan diuji dengan lebih mudah.

## Fitur Aplikasi

Fitur yang ada pada aplikasi ini adalah:

* Login menggunakan username dan password dari database.
* Validasi login berhasil dan gagal.
* Game Tic-Tac-Toe menggunakan tampilan Java Swing.
* User bermain sebagai X dan komputer sebagai O.
* Program bisa mendeteksi hasil menang, kalah, dan seri.
* Program menolak langkah yang tidak valid, misalnya memilih kotak yang sudah terisi.
* Statistik pemain tersimpan di database.
* Statistik yang dicatat adalah wins, losses, draws, dan score.
* Menampilkan statistik pribadi pemain.
* Menampilkan Top 5 scorer menggunakan JTable.
* Data Top 5 diambil langsung dari database, bukan ditulis manual di program.

## Database yang Digunakan

Database yang digunakan pada project ini adalah **PostgreSQL**.

Nama database:

```sql
game_project
```

Nama tabel:

```sql
players
```

Project ini hanya menggunakan satu tabel, yaitu tabel `players`. Tabel ini dipakai untuk menyimpan data login sekaligus statistik permainan.

Struktur tabel `players`:

| Kolom    | Keterangan           |
| -------- | -------------------- |
| id       | ID pemain            |
| username | Username untuk login |
| password | Password untuk login |
| wins     | Jumlah kemenangan    |
| losses   | Jumlah kekalahan     |
| draws    | Jumlah seri          |
| score    | Total skor pemain    |


## Alur Program

Alur aplikasi dimulai dari `Main.java`. Setelah program dijalankan, aplikasi akan membuka halaman login.

User memasukkan username dan password. Data login tersebut dicek ke tabel `players` melalui `PlayerService`. Kalau login berhasil, aplikasi akan membuka Main Menu. Kalau login gagal, aplikasi akan menampilkan pesan error.

Dari Main Menu, user bisa memilih beberapa menu, yaitu mulai game, melihat statistik pribadi, melihat Top 5 scorer, atau keluar dari aplikasi.

Saat game selesai, hasil permainan akan langsung digunakan untuk memperbarui data statistik di database. Setelah itu, user bisa melihat statistik terbaru melalui menu My Statistics atau melihat ranking pada menu Top 5 Scorers.

## Perhitungan Skor

Sistem skor yang digunakan:

| Hasil Game | Perubahan Skor |
| ---------- | -------------- |
| Menang     | +10            |
| Seri       | +3             |
| Kalah      | +0             |

Jika user menang, kolom `wins` bertambah 1 dan `score` bertambah 10.
Jika user kalah, kolom `losses` bertambah 1.
Jika user seri, kolom `draws` bertambah 1 dan `score` bertambah 3.


## Penjelasan Class

### Main.java

Class utama untuk menjalankan program. Class ini membuka `LoginFrame` saat aplikasi pertama kali dijalankan.

### DatabaseManager.java

Class untuk mengatur koneksi antara program Java dan database PostgreSQL. Koneksi dilakukan menggunakan JDBC.

### Player.java

Class model untuk menyimpan data pemain, seperti id, username, wins, losses, draws, dan score.

### PlayerService.java

Class yang berisi proses yang berhubungan dengan database, seperti login, mengambil data pemain, memperbarui statistik, dan mengambil data Top 5 scorer.

### GameLogic.java

Class yang mengatur aturan permainan Tic-Tac-Toe. Di dalamnya terdapat proses validasi langkah, pengecekan pemenang, pengecekan seri, dan langkah komputer.

### LoginFrame.java

Class GUI untuk halaman login. User memasukkan username dan password melalui halaman ini.

### MainMenuFrame.java

Class GUI untuk halaman menu utama setelah login berhasil. Menu yang tersedia adalah Start Game, My Statistics, Top 5 Scorers, dan Exit.

### GameFrame.java

Class GUI untuk halaman permainan. Papan Tic-Tac-Toe ditampilkan dalam bentuk tombol 3x3. Class ini menghubungkan klik tombol dengan logic permainan.

### StatisticsFrame.java

Class GUI untuk menampilkan statistik pribadi pemain yang sedang login. Data yang ditampilkan berasal dari database.

### TopScorersFrame.java

Class GUI untuk menampilkan 5 pemain dengan score tertinggi. Data ditampilkan menggunakan JTable dan diambil langsung dari database.

## Query Top 5 Scorers

Data Top 5 scorer diambil dengan query berikut:

```sql
SELECT username, wins, losses, draws, score
FROM players
ORDER BY score DESC, wins DESC
LIMIT 5;
```

Urutan ranking berdasarkan score tertinggi. Kalau ada score yang sama, pemain dengan jumlah wins lebih banyak akan berada di posisi lebih atas.


## Link Repository dan Video

GitHub Repository: [https://github.com/akbarafianth/5026251006_Akbar-Ariffianto_FP_A.git]
YouTube Demonstration Video: [https://youtu.be/hvE77dtlhWA]

## Catatan

Project ini dibuat sebagai tugas individu mata kuliah Pemrograman dasar. Fokus utama project ini adalah menerapkan dasar pemrograman Java, penggunaan Java Swing, pemisahan class sederhana, koneksi database PostgreSQL, serta pencatatan statistik permainan menggunakan satu tabel database.
