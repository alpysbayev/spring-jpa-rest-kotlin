package com.example.entity

import javax.persistence.*

@Entity
@Table(name = "city")
class CityEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // нужна если мы создаем автоинкрементное поле на стороне БД
    val id: Int = 0,

    var name: String = "",

    @ManyToOne
    @JoinColumn(name = "country_id")
    var country: CountryEntity
){}