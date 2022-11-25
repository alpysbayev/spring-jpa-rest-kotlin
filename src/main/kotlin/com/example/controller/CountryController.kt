package com.example.controller

import org.springframework.web.bind.annotation.RestController
import com.example.dto.CountryDTO
import com.example.service.CountryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


@RestController
@RequestMapping("/countries")
class CountryController(
    private val countryService: CountryService,
) {
    @GetMapping
    fun getAll(@RequestParam("page") pageIndex: Int): List<CountryDTO> = countryService.getAll(pageIndex)
}