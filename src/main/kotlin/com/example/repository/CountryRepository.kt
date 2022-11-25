package com.example.repository

import com.example.entity.CountryEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface CountryRepository: CrudRepository<CountryEntity, Int> {

    fun findByOrderByName(pageable: Pageable): List<CountryEntity>
}
