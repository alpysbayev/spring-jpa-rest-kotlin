package com.example.exception

import org.springframework.http.HttpStatus

class CountryNotFoundException(countryId: Int) : BaseException(
    httpStatus = HttpStatus.NOT_FOUND,
    apiError = ApiError(
        errorCode = "country.not.found",
        description = "Country with id=$countryId not found!"
    )
) {
}