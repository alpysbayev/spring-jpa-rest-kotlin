package com.example.controller

import org.springframework.web.bind.annotation.RestController
import com.example.dto.CountryDTO
import com.example.service.CountryService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


@RestController
@RequestMapping("/countries")
class CountryController(
    private val countryService: CountryService,
) {
    @GetMapping
    fun getAll(@RequestParam("page") pageIndex: Int): List<CountryDTO> = countryService.getAllByPage(pageIndex)

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): CountryDTO = countryService.getById(id)

    @GetMapping("/country")
    fun getById(@RequestParam("name") name: String): CountryDTO = countryService.getByName(name)

    @GetMapping("/search-by-prefix")
    fun searchCountries(@RequestParam("prefix") prefix: String): List<CountryDTO> = countryService.searchByPrefix(prefix)

    @GetMapping("/search-by-part")
    fun searchCountriesByPart(@RequestParam("part") part: String): List<CountryDTO> = countryService.searchByPart(part)

    @GetMapping("/all")
    fun getAllCountries(): List<CountryDTO> = countryService.getAll()

    @GetMapping("/names")
    fun getCountryNames(): List<String> = countryService.getNames()

    @PostMapping
    fun create(@RequestBody dto: CountryDTO): Int { //@RequestBody - говорит о том что все входные данные будут в теле запроса а не в URL
        return countryService.create(dto)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody dto: CountryDTO) {
        countryService.update(id, dto)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) {
        countryService.delete(id)
    }
}