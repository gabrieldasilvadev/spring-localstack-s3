package com.gabs.localstack.controllers

import com.gabs.localstack.services.AwsS3Service
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bucket")
data class BucketController(
        private val awsS3Service: AwsS3Service
) {

    @PutMapping
    fun uploadObjectToS3() {
        awsS3Service.uploadObjectToS3()
    }
}