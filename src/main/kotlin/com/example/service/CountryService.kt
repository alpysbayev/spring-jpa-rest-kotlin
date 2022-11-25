package com.example.service

import com.example.dto.CountryDTO

interface CountryService {

    fun getAll(pageIndex: Int): List<CountryDTO>

    fun getById(id: Int): CountryDTO

    fun getByName(name: String): CountryDTO

    fun searchByPrefix(prefix: String): List<CountryDTO>

    fun searchByPart(part: String): List<CountryDTO>

    fun create(dto: CountryDTO): Int

    fun update(id: Int, dto: CountryDTO)

    fun delete(id: Int)
}