package com.gabs.localstack.configs

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value

object AWSConfig {

    @Value("\${config.aws.s3.url}")
    private lateinit var s3EndpointUrl: String

    fun buildS3Client(): AmazonS3 =
        AmazonS3ClientBuilder
            .standard()
            .withEndpointConfiguration(getEndpointConfiguration(s3EndpointUrl))
            .withPathStyleAccessEnabled(true)
            .withCredentials(DefaultAWSCredentialsProviderChain())
            .build()

    private fun getEndpointConfiguration(url: String): AwsClientBuilder.EndpointConfiguration {
        return AwsClientBuilder.EndpointConfiguration(
            url,
            Regions.US_EAST_1.name
        )
    }
}