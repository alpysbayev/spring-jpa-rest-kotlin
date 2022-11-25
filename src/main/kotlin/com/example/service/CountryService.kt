package com.example.service

import com.example.dto.CountryDTO

interface CountryService {

    fun getAll(): List<CountryDTO>

}