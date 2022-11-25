package com.example.dto

data class CountryDTO(
    val id: Int? = null, // это ID-шка nullable потому что при принятии запроса оно будет пустая но БД самостоятельно поставит значение
    val name: String,
    val population: Int,
)
