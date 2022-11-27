package com.example.repository

import com.example.entity.CityEntity
import com.example.entity.CountryEntity
import org.springframework.data.repository.CrudRepository

interface CityRepository: CrudRepository<CityEntity, Int> {

    fun deleteAllByCountry(country: CountryEntity)
}