package com.example.service.impl

import com.example.dto.CityDTO
import com.example.dto.CountryDTO
import com.example.entity.CityEntity
import com.example.entity.CountryEntity
import com.example.exception.CountryNotFoundException
import com.example.repository.CityRepository
import com.example.repository.CountryRepository
import com.example.service.CountryService
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CountryServiceImpl(
    private val countryRepository: CountryRepository,
    private val cityRepository: CityRepository
) : CountryService {

    override fun getAllByPage(pageIndex: Int): List<CountryDTO> {
        return countryRepository.findByOrderByName(PageRequest.of(pageIndex, 2)).map { it.toDTO()}
    }

    override fun getById(id: Int): CountryDTO {
        return countryRepository.findByIdOrNull(id)
            ?.toDTO()
            ?:throw CountryNotFoundException(id)
    }

    override fun getAll(): List<CountryDTO> {
        return countryRepository.findAllByOrderByName().map { it.toDTO() }
    }

    override fun getNames(): List<String> {
        return countryRepository.findAllByOrderByName().map { it.name }
    }

    override fun getByName(name: String): CountryDTO {
        return countryRepository.findByName(name).toDTO()
    }

    override fun searchByPrefix(prefix: String): List<CountryDTO> {
        return countryRepository.findByNameStartsWithIgnoreCaseOrderByName(prefix).map { it.toDTO() }
    }

//    override fun create(dto: CountryDTO): Int {
//        return countryRepository.save(dto.toEntity()).id
//    }

    @Transactional // потому что тут много действий выполняются одновременно (создание страны, создание городо) и мы вызываем этот метод за прделами этого файла
    override fun create(dto: CountryDTO): Int {
        val countryEntity = countryRepository.save(dto.toEntity())
        val cities = dto.cities.map { it.toEntity(countryEntity) }

        cityRepository.saveAll(cities)

        return countryEntity.id
    }

//    override fun update1(id: Int, dto: CountryDTO) {
//        val existence = countryRepository.findByIdOrNull(id) ?:throw CountryNotFoundException(id)
//
//        existence.name = dto.name
//        existence.population = dto.population
//
//        countryRepository.save(existence)
//    }

    @Transactional
    override fun update(id: Int, dto: CountryDTO) {
        var existingCountry = countryRepository.findByIdOrNull(id)
            ?: throw CountryNotFoundException(id)

        existingCountry.name = dto.name
        existingCountry.population = dto.population

        existingCountry = countryRepository.save(existingCountry)

        val cities = dto.cities.map { it.toEntity(existingCountry) }
        cityRepository.deleteAllByCountry(existingCountry)
        cityRepository.saveAll(cities)
    }

//    override fun delete(id: Int) {
//        val existence = countryRepository.findByIdOrNull(id) ?:throw CountryNotFoundException(id)
//
//        countryRepository.deleteById(existence.id)
//    }

    @Transactional
    override fun delete(id: Int) {
        val existingCountry = countryRepository.findByIdOrNull(id)
            ?:throw CountryNotFoundException(id)

        cityRepository.deleteAllByCountry(existingCountry)
        countryRepository.deleteById(existingCountry.id)
    }

    override fun searchByPart(part: String): List<CountryDTO> {
        return countryRepository.findByNameContainsIgnoreCaseOrderByName(part).map { it.toDTO() }
    }

    private fun CountryEntity.toDTO(): CountryDTO =
        CountryDTO(
            id = this.id,
            name = this.name,
            population = this.population,
            cities = this.cities.map { it.toDTO() },
        )

    private fun CityEntity.toDTO(): CityDTO =
        CityDTO(
            name = this.name,
        )

    private fun CountryDTO.toEntity(): CountryEntity =
        CountryEntity(
            id = 0,
            name = this.name,
            population = this.population,
        )

    private fun CityDTO.toEntity(country: CountryEntity): CityEntity =
        CityEntity(
            id = 0,
            name = this.name,
            country = country,
        )
}