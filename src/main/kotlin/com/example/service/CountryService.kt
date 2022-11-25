package com.example.service

import com.example.dto.CountryDTO

interface CountryService {

    fun getAll(pageIndex: Int): List<CountryDTO>

    fun getById(id: Int): CountryDTO

    fun getByName(name: String): CountryDTO

    fun searchByPrefix(prefix: String): List<CountryDTO>

    fun searchByPart(part: String): List<CountryDTO>
}