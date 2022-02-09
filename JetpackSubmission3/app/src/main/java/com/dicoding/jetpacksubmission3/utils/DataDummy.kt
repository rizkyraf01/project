package com.dicoding.jetpacksubmission3.utils

import com.dicoding.jetpacksubmission3.data.source.local.entity.*
import com.dicoding.jetpacksubmission3.data.source.remote.response.MoviesResponse
import com.dicoding.jetpacksubmission3.data.source.remote.response.TvShowResponse

object DataDummy {



    fun generateRemoteMovies(): List<MoviesResponse>{
        val movies = ArrayList<MoviesResponse>()
        movies.add(MoviesResponse("R.drawable.banner_a_star",
            listOf(),1, "A Star is Born","Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            10.0,"R.drawable.poster_a_start_is_born","2018",200,"A Star is Born","https://www.youtube.com/watch?v=nSbzyEJ8X9E",
            7.5
        ))
        movies.add(MoviesResponse("R.drawable.banner_a_star",
            listOf(),1, "A Star is Born","Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            10.0,"R.drawable.poster_a_start_is_born","2018",200,"A Star is Born","https://www.youtube.com/watch?v=nSbzyEJ8X9E",
            7.5
        ))
        movies.add(MoviesResponse("R.drawable.banner_a_star",
            listOf(),1, "A Star is Born","Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            10.0,"R.drawable.poster_a_start_is_born","2018",200,"A Star is Born","https://www.youtube.com/watch?v=nSbzyEJ8X9E",
            7.5
        ))
        movies.add(MoviesResponse("R.drawable.banner_a_star",
            listOf(),1, "A Star is Born","Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            10.0,"R.drawable.poster_a_start_is_born","2018",200,"A Star is Born","https://www.youtube.com/watch?v=nSbzyEJ8X9E",
            7.5
        ))
        movies.add(MoviesResponse("R.drawable.banner_a_star",
            listOf(),1, "A Star is Born","Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            10.0,"R.drawable.poster_a_start_is_born","2018",200,"A Star is Born","https://www.youtube.com/watch?v=nSbzyEJ8X9E",
            7.5
        ))
        return movies
    }

    fun generateRemoteDummyMovies(): MoviesResponse{
        return MoviesResponse("R.drawable.banner_a_star",
            listOf(),1, "A Star is Born","Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            10.0,"R.drawable.poster_a_start_is_born","2018",200,"A Star is Born","https://www.youtube.com/watch?v=nSbzyEJ8X9E",
            7.5)
    }

    fun generateRemoteTvShow(): List<TvShowResponse>{
        val tvShow = ArrayList<TvShowResponse>()
        tvShow.add(
            TvShowResponse("R.drawable.banner_arrow",  listOf(44),"2012",
                listOf(),1,"Arrow","Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "R.drawable.poster_arrow",7.2,  "https://www.youtube.com/watch?v=2yrviapP5uY"
            ))
        tvShow.add(
            TvShowResponse("R.drawable.banner_arrow",  listOf(44),"2012",
                listOf(),1,"Arrow","Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "R.drawable.poster_arrow",7.2,  "https://www.youtube.com/watch?v=2yrviapP5uY"
            ))
        tvShow.add(
            TvShowResponse("R.drawable.banner_arrow",  listOf(44),"2012",
                listOf(),1,"Arrow","Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "R.drawable.poster_arrow",7.2,  "https://www.youtube.com/watch?v=2yrviapP5uY"
            ))
        tvShow.add(
            TvShowResponse("R.drawable.banner_arrow",  listOf(44),"2012",
                listOf(),1,"Arrow","Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "R.drawable.poster_arrow",7.2,  "https://www.youtube.com/watch?v=2yrviapP5uY"
            ))
        tvShow.add(
            TvShowResponse("R.drawable.banner_arrow",  listOf(44),"2012",
                listOf(),1,"Arrow","Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "R.drawable.poster_arrow",7.2,  "https://www.youtube.com/watch?v=2yrviapP5uY"
            ))
        return tvShow
    }

    fun generateRemoteDummyTvShow(): TvShowResponse{
        return TvShowResponse("R.drawable.banner_arrow",  listOf(44),"2012",
            listOf(),1,"Arrow","Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "R.drawable.poster_arrow",7.2,  "https://www.youtube.com/watch?v=2yrviapP5uY"
        )
    }

    fun generateMovies(): List<MoviesEntity>{
        val movies = ArrayList<MoviesEntity>()
        movies.add(
            MoviesEntity(1, "A Star is Born","2018",
                "Action, Science Fiction, Adventure","2h 16min",7.2,
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "R.drawable.poster_a_start_is_born", "R.drawable.banner_a_star", "https://www.youtube.com/watch?v=nSbzyEJ8X9E"
            )
        )
        movies.add(
            MoviesEntity(2, "Alita","2019",
                "Action, Science Fiction, Adventure","2h 2min",7.2,
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "R.drawable.poster_alita", "R.drawable.banner_alita", "https://www.youtube.com/watch?v=w7pYhpJaJW8"
            )
        )
        movies.add(
            MoviesEntity(3, "Aquaman","2018",
                "Action, Science Fiction, Adventure","2h 23min",7.2,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "R.drawable.poster_aquaman", "R.drawable.banner_aquaman", "https://www.youtube.com/watch?v=WDkg3h8PCVU"
            )
        )
        movies.add(
            MoviesEntity(4, "Bohemian Rapsody","2018",
                "Action, Science Fiction, Adventure","2h 15min",7.2,
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "R.drawable.poster_bohemian", "R.drawable.banner_bohemian", "https://www.youtube.com/watch?v=mP0VHJYFOAU"
            )
        )
        movies.add(
            MoviesEntity(5, "Cold Pursiut","2019",
                "Action, Science Fiction, Adventure","1h 59min",7.2,
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "R.drawable.poster_cold_persuit", "R.drawable.banner_cold_pursuit", "https://www.youtube.com/watch?v=7C2Nad0aMPg"
            )
        )
        return movies
    }

    fun generateDetailMovies(): MoviesEntity{
        return MoviesEntity(1, "A Star is Born","2018",
            "Action, Science Fiction, Adventure","2h 16min",7.2,
            "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
            "R.drawable.poster_a_start_is_born", "R.drawable.banner_a_star", "https://www.youtube.com/watch?v=nSbzyEJ8X9E")
    }

    fun generateDummyMovies(): List<MoviesDetailEntity>{
        val movies = ArrayList<MoviesDetailEntity>()
        movies.add(
            MoviesDetailEntity(1, "A Star is Born","2018",
                listOf("Action", "Science Fiction", "Adventure"),"2h 16min",7.2,
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "R.drawable.poster_a_start_is_born", "R.drawable.banner_a_star", "https://www.youtube.com/watch?v=nSbzyEJ8X9E"
            )
        )
        movies.add(
            MoviesDetailEntity(2, "Alita","2019",
                listOf("Action", "Science Fiction", "Adventure"),"2h 2min",7.2,
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "R.drawable.poster_alita", "R.drawable.banner_alita", "https://www.youtube.com/watch?v=w7pYhpJaJW8"
            )
        )
        movies.add(
            MoviesDetailEntity(3, "Aquaman","2018",
                listOf("Action", "Science Fiction", "Adventure"),"2h 23min",7.2,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "R.drawable.poster_aquaman", "R.drawable.banner_aquaman", "https://www.youtube.com/watch?v=WDkg3h8PCVU"
            )
        )
        movies.add(
            MoviesDetailEntity(4, "Bohemian Rapsody","2018",
                listOf("Action", "Science Fiction", "Adventure"),"2h 15min",7.2,
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "R.drawable.poster_bohemian", "R.drawable.banner_bohemian", "https://www.youtube.com/watch?v=mP0VHJYFOAU"
            )
        )
        movies.add(
            MoviesDetailEntity(5, "Cold Pursiut","2019",
                listOf("Action", "Science Fiction", "Adventure"),"1h 59min",7.2,
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "R.drawable.poster_cold_persuit", "R.drawable.banner_cold_pursuit", "https://www.youtube.com/watch?v=7C2Nad0aMPg"
            )
        )
        movies.add(
            MoviesDetailEntity(6, "Creed II","2018",
                listOf("Action", "Science Fiction", "Adventure"),"2h 10min",7.2,
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "R.drawable.poster_creed", "R.drawable.banner_creed", "https://www.youtube.com/watch?v=u22BXhMu4tI"
            )
        )
        movies.add(
            MoviesDetailEntity(7, "Fantastic Beast: The Crimes of Grindelwald","2018",
                listOf("Action", "Science Fiction", "Adventure"),"2h 14min",7.2,
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "R.drawable.poster_crimes", "R.drawable.banner_crimes", "https://www.youtube.com/watch?v=vvFybpmyB9E"
            )
        )
        movies.add(
            MoviesDetailEntity(8, "Glass","2019",
                listOf("Action", "Science Fiction", "Adventure"),"2h 9min",7.2,
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "R.drawable.poster_glass", "R.drawable.banner_glass", "https://www.youtube.com/watch?v=95ghQs5AmNk"
            )
        )
        movies.add(
            MoviesDetailEntity(9, "How To Train Your Dragon: The Hidden World","2019",
                listOf("Action", "Science Fiction", "Adventure"),"1h 44min",7.2,
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "R.drawable.poster_how_to_train", "R.drawable.banner_how_to", "https://www.youtube.com/watch?v=CQ7XUCQ6pbE"
            )
        )
        movies.add(
            MoviesDetailEntity(10, "Avenger: Infinity War","2018",
                listOf("Action", "Science Fiction", "Adventure"),"2h 29min",7.2,
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "R.drawable.poster_infinity_war", "R.drawable.banner_infinity", "https://www.youtube.com/watch?v=6ZfuNTqbHE8"
            )
        )
        movies.add(
            MoviesDetailEntity(11, "Robin Hood","2018",
                listOf("Action", "Science Fiction", "Adventure"),"1h 56min",7.2,
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                "R.drawable.poster_robin_hood", "R.drawable.banner_robin_hood", "https://www.youtube.com/watch?v=zwPn9ZnbCo0"
            )
        )
        movies.add(
            MoviesDetailEntity(12, "Serenity","2019",
                listOf("Action", "Science Fiction", "Adventure"),"1h 42min",7.2,
                "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
                "R.drawable.poster_serenity", "R.drawable.banner_serenity", "https://www.youtube.com/watch?v=k3zMlsEK8xA"
            )
        )
        movies.add(
            MoviesDetailEntity(13, "Spiderman: Into The Spider Verse","2018",
                listOf("Action", "Science Fiction", "Adventure"),"1h 57min",7.2,
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                ".drawable.poster_spiderman", "R.drawable.banner_spiderman", "https://www.youtube.com/watch?v=g4Hbz2jLxvQ"
            )
        )
        movies.add(
            MoviesDetailEntity(14, "T34","2018",
                listOf("Action", "Science Fiction", "Adventure"),"2h 19min",7.2,
                "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                "R.drawable.poster_t34", "R.drawable.banner_t34", "https://www.youtube.com/watch?v=a5A7QboG8Nk"
            )
        )
        return movies
    }

    fun generateTvShow(): List<TvShowEntity>{
        val tvShow = ArrayList<TvShowEntity>()
        tvShow.add(
            TvShowEntity(1,"Arrow","2012",
                "Action, Science Fiction, Adventure","42min",7.2,
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                 "R.drawable.poster_arrow", "R.drawable.banner_arrow", "https://www.youtube.com/watch?v=2yrviapP5uY"
            )
        )
        tvShow.add(
            TvShowEntity(2,"Dragon Ball","1986",
                "Action, Science Fiction, Adventure","25min",7.2,
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "R.drawable.poster_dragon_ball", "R.drawable.banner_dagoball", "https://www.youtube.com/watch?v=DIjku6BtYW4"
            )
        )
        tvShow.add(
            TvShowEntity(3,"Fairy Tail","2009",
                "Action, Science Fiction, Adventure","25min",7.2,
                "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",
                 "R.drawable.poster_fairytail", "R.drawable.banner_fairy_tail", "https://www.youtube.com/watch?v=BaIWwk-sAlw"
            )
        )
        tvShow.add(
            TvShowEntity(4,"Family Guy","1999",
                "Action, Science Fiction, Adventure","22min",7.2,
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                 "R.drawable.poster_family_guy", "R.drawable.banner_family_guy", "https://www.youtube.com/watch?v=Le1x2To-e6g"
            )
        )
        tvShow.add(
            TvShowEntity(5,"Flash","2014",
                "Action, Science Fiction, Adventure","44min",7.2,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                 "R.drawable.poster_flash", "R.drawable.banner_flash", "https://www.youtube.com/watch?v=N-QSZKxkBjo"
            )
        )
        return tvShow
    }

    fun generateDetailTvShow(): TvShowEntity{
        return TvShowEntity(1,"Arrow","2012",
            "Action, Science Fiction, Adventure","42min",7.2,
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "R.drawable.poster_arrow", "R.drawable.banner_arrow", "https://www.youtube.com/watch?v=2yrviapP5uY"
        )
    }
}