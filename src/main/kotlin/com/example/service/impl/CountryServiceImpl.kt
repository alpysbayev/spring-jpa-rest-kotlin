package com.example.service.impl

import com.example.dto.CountryDTO
import com.example.entity.CountryEntity
import com.example.repository.CountryRepository
import com.example.service.CountryService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CountryServiceImpl(
    private val countryRepository: CountryRepository,
) : CountryService {

    override fun getAll(pageIndex: Int): List<CountryDTO> {
        return countryRepository.findByOrderByName(PageRequest.of(pageIndex, 2)).map { it.toDTO()}
    }

    private fun CountryEntity.toDTO(): CountryDTO = CountryDTO(
                                                        id = this.id,
                                                        name = this.name,
                                                        population = this.population,
                                                    )
}