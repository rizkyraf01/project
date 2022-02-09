package com.example.submissionjetpackpro1.utils

import com.example.submissionjetpackpro1.data.DataFilmEntity

object DataDummy {
    fun generateDummyFilm(): List<DataFilmEntity>{
    val film= ArrayList<DataFilmEntity>()

        film.add(DataFilmEntity(
        "f1",
            "Mortal Kombat",
            "Simon McQuoid",
            "seorang petarung Cole Young yang terbiasa bertarung untuk mendapatkan uang namun ia tidak tahu soal warisan keluarganya.Kaisar Outworld Shang Tsung mengirim petarungnya Sub-Zero yang merupakan seorang pengendali es dari Outworld untuk memburu Cole Young. Cole Young menjadi khawatir atas keselamatan keluarganya Cole Young kemudian mencari Sonya Blade berdasarkan petunjuk dari Jax seorang mayor pasukan khusus yang memiliki simbol naga yang sama dengan simbol yang telah Cole Young miliki sejak kecil.",
            "https://static.wikia.nocookie.net/mkwikia/images/5/5b/Mortal-kombat-poster.jpg/revision/latest/scale-to-width-down/1000?cb=20210218180106"
        ))
        return film
    }
    fun generateDummyTvshow():List<DataFilmEntity>{
        val tvshow= ArrayList<DataFilmEntity>()

        tvshow.add(
            DataFilmEntity(
            "t1",
            "La casa da papel",
            "Álex Pina",
            "Serial ini mengisahkan upaya delapan orang pencuri yang melakukan perampokan ke badan pencetakan uang Spanyol (Royal Mint of Spain). Dalam perampokan itu para pencuri menyandera para pegawai dan pengunjung serta mengunci diri mereka didalam gedung Royal Mint of Spain.",
            "https://images-na.ssl-images-amazon.com/images/I/71PeZhQ6WbL._AC_SY741_.jpg"

        ))
        return tvshow
    }
}