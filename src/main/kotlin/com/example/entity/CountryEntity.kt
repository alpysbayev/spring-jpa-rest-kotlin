package com.example.entity

import javax.persistence.*

@Entity
@Table(name = "country")
class CountryEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // нужна если мы создаем автоинкрементное поле на стороне БД
    val id: Int = 0,

    var name: String = "", // если это поле и колонки в БД названы одинаково то можно и без аннотации (как снизу)

    @Column(name = "population") // можно так же вручную установить имя колонки таблицы с БД если они отличаеются от здешных
    var population: Int = 0,


    @OneToMany(mappedBy = "country") // mappedBy="country" потому что в CityEntity мы связующее поле назвали "country"
    val cities: List<CityEntity> = emptyList(),
){}