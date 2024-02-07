# Tutorial Advanced Programming
__Reza Apriono__ </br>
__2206827945__</br>
__AdPro B - KVN__</br>

## Reflection 1

Pada tutorial 1, saya sudah menerapkan _clean code principle_ dengan melakukan beberapa hal. 

Terkait meaningful names, Penamaan variabel dan  pada kode saya telah bersifat jelas dan ringkas, serta dapat mewakili data apa yang disimpan dalam variabel atau apa yang dilakukan function tersebut.
Contohnya adalah function `findProduct()` untuk mencari product dari productData, dan variabel `indexOfProduct` untuk menyimpan index product pada productData.

Kemudian, setiap function yang dibuat hanya mengerjakan satu tugas saja dan function tersebut ukurannya tidak terlalu besar. Contohnya adalah function `create`, `edit`, dan `delete` masing-masing hanya menjalankan satu tugas saja sesuai namanya.

Selain itu, saya juga telah menggunakan version control dengan menggunakan git dan menerapkan branching untuk fitur-fitur serta test yang ada.

Meskipun beberapa bagian kode saya tidak memiliki comment, namun penamaan fungsi dan variabel yang digunakan sudah baik dan dapat merepresentasikan perilakunya tanpa perlu penjelasan lebih lanjut.


Saya sempat melakukan beberapa kesalahan saat menulis kode. Contohnya, salah menuliskan `seleniumhq` menjadi `seleniumhg` pada `build.gradle.kts` sehingga menyebabkan test tidak dapat diexecute. Selain itu, saya sempat salah mapping untuk kembali ke product list setelah mengedit product, dan juga menemui error saat membuat fitur edit dan delete. Untungnya, hal-hal tersebut masih dapat saya perbaiki. Mungkin yang bisa ditingkatkan dari kode saya adalah menambahkan _input validation_ terhadap `name`
dan `quantity` dari product.
 
## Reflection 2

1. Setelah membuat unit test, saya merasa lebih yakin bahwa kode yang saya buat dan fitur-fitur didalamnya dapat berjalan dengan semestinya dan tidak terdapat bug atau error. Menurut saya, banyaknya unit test bersifat tentatif tergantung pada program yang kita buat. Namun, seharusnya unit test dapat mencakup semua fungsionalitas pada program.
Code coverage 100% tidak menjamin bahwa kode tidak memiliki bug atau error, karena code coverage hanya merupakan perhitungan terhadap seberapa besar kode yang diuji oleh unit test. Oleh karena itu, penting untuk membuat dan memastikan bahwa unit test telah mencakup berbagai skenario, misalnya _positive scenario_ dan _negative scenario_.


2. Pembuatan functional test suite dengan cara tersebut akan menyebabkan terdapat duplikasi pada code dan mengurangi _cleanliness_ dari kode tersebut. Hal tersebut dapat menyulitkan *code maintenence* saat terdapat suatu perubahan pada _source code_. Mungkin salah satu solusi yang dapat diterapkan adalah dengan menggunakan method `setup()` untuk kode yang akan digunakan di beberapa test dan kemudian menggabungkan test untuk mengecek jumlah item pada product list dengan test create product.