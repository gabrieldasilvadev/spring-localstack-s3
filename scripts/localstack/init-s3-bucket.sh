# -- > Create S3 Bucket
awslocal s3 mb s3://gabs-bucket --endpoint-url http://localhost:4566

# --> List S3 Buckets
echo $(awslocal s3 ls)