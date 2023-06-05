package com.gabs.localstack.services

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.gabs.localstack.configs.AWSConfig
import com.gabs.localstack.model.Person
import com.opencsv.CSVWriter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.StringWriter

@Service
data class AwsS3Service(
        private val amazonS3: AmazonS3,
        private val awsConfig: AWSConfig
) {

    @Value("\${config.aws.s3.bucket-name}")
    private lateinit var bucketName: String

    fun uploadObjectToS3() {
        val persons = listOf(
                Person("Gabriel", 25),
                Person("João", 30), Person("Maria", 20),
                Person("José", 40),
                Person("Ana", 50)
        )

        val fileName = "file.csv"
        val fileContent = generateCsv(persons)
        val metadata = ObjectMetadata()
        val fileStream = fileContent.byteInputStream()
        metadata.contentType = "text/csv"

        val putObjectRequest = PutObjectRequest(bucketName, fileName, fileStream, metadata)
                .withCannedAcl(CannedAccessControlList.Private)

        amazonS3.putObject(putObjectRequest)
    }

    private fun generateCsv(persons: List<Person>): String {
        val writer = StringWriter()
        val csvWriter = CSVWriter(writer)

        val header = arrayOf("name", "age")
        csvWriter.writeNext(header)

        persons.forEach { person ->
            val line = arrayOf(person.name, person.age.toString())
            csvWriter.writeNext(line)
        }

        csvWriter.close()

        return writer.toString()
    }
}
