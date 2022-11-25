package com.example.controller

import org.springframework.web.bind.annotation.RestController
import com.example.dto.CountryDTO
import com.example.service.CountryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


@RestController
@RequestMapping("/countries")
class CountryController(
    private val countryService: CountryService,
) {
    @GetMapping
    fun getAll(@RequestParam("page") pageIndex: Int): List<CountryDTO> = countryService.getAll(pageIndex)

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Int): CountryDTO = countryService.getById(id)

    @GetMapping("/country")
    fun getById(@RequestParam("name") name: String): CountryDTO = countryService.getByName(name)

    @GetMapping("/search-by-prefix")
    fun searchCountries(@RequestParam("prefix") prefix: String): List<CountryDTO> = countryService.searchByPrefix(prefix)

    @GetMapping("/search-by-part")
    fun searchCountriesByPart(@RequestParam("part") part: String): List<CountryDTO> = countryService.searchByPart(part)
}