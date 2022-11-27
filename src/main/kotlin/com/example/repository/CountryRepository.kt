package com.example.repository

import com.example.entity.CountryEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface CountryRepository: CrudRepository<CountryEntity, Int> {
    //Data JPA может иногда рабоотать некорректно с классами данных из-за этого мы тут используем CountryEntity

    fun findByOrderByName(pageable: Pageable): List<CountryEntity>

    fun findByName(name: String): CountryEntity

    fun findByNameStartsWithIgnoreCaseOrderByName(prefix: String): List<CountryEntity>

    fun findByNameContainsIgnoreCaseOrderByName(part: String): List<CountryEntity>

    fun findAllByOrderByName(): List<CountryEntity>
}
