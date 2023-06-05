package com.gabs.localstack.configs

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AWSConfig {

    @Value("\${config.aws.s3.access-key}")
    private lateinit var accessKey: String

    @Value("\${config.aws.s3.secret-key}")
    private lateinit var secretKey: String

    @Value("\${config.aws.s3.url}")
    private lateinit var s3EndpointUrl: String

    @Bean
    fun amazonS3(): AmazonS3 {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(getCredentialsProvider())
                .withEndpointConfiguration(getEndpointConfiguration(s3EndpointUrl))
                .build()
    }

    private fun credentials(): AWSCredentials {
        return BasicAWSCredentials(
                accessKey,
                secretKey
        )
    }

    private fun getCredentialsProvider(): AWSStaticCredentialsProvider {
        return AWSStaticCredentialsProvider(credentials())
    }

    private fun getEndpointConfiguration(url: String): AwsClientBuilder.EndpointConfiguration {
        return AwsClientBuilder.EndpointConfiguration(
                url,
                Regions.US_EAST_1.name
        )
    }
}