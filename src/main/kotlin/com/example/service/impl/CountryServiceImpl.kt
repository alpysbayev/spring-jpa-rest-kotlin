package com.example.service.impl

import com.example.dto.CountryDTO
import com.example.entity.CountryEntity
import com.example.repository.CountryRepository
import com.example.service.CountryService
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CountryServiceImpl(
    private val countryRepository: CountryRepository,
) : CountryService {

    override fun getAll(pageIndex: Int): List<CountryDTO> {
        return countryRepository.findByOrderByName(PageRequest.of(pageIndex, 2)).map { it.toDTO()}
    }

    override fun getById(id: Int): CountryDTO {
        return countryRepository.findByIdOrNull(id)
            ?.toDTO()
            ?:throw RuntimeException("Country not found!")
    }

    override fun getByName(name: String): CountryDTO {
        return countryRepository.findByName(name).toDTO()
    }

    override fun searchByPrefix(prefix: String): List<CountryDTO> {
        return countryRepository.findByNameStartsWithIgnoreCaseOrderByName(prefix).map { it.toDTO() }
    }

    override fun create(dto: CountryDTO): Int {
        return countryRepository.save(dto.toEntity()).id
    }

    override fun update(id: Int, dto: CountryDTO) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    override fun searchByPart(part: String): List<CountryDTO> {
        return countryRepository.findByNameContainsIgnoreCaseOrderByName(part).map { it.toDTO() }
    }

    private fun CountryEntity.toDTO(): CountryDTO = CountryDTO(
                                                        id = this.id,
                                                        name = this.name,
                                                        population = this.population,
                                                    )

    private fun CountryDTO.toEntity(): CountryEntity = CountryEntity(
                                                        id = 0,
                                                        name = this.name,
                                                        population = this.population,
                                                    )
}