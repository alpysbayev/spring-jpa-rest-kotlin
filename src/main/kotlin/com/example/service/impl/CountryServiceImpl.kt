package com.example.service.impl

import com.example.dto.CountryDTO
import com.example.service.CountryService
import org.springframework.stereotype.Service

@Service
class CountryServiceImpl : CountryService {

    override fun getAll(): List<CountryDTO> {
        return listOf(
            CountryDTO(1, "Qazaqstan", 19_000_000),
            CountryDTO(2, "Canada", 38_250_000)
        )
    }
}