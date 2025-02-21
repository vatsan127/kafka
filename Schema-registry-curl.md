## Get Schema by Subject Name and Version
```
curl -X GET "http://<schema-registry-url>/subjects/<subject-name>/versions/<version-number>"
```
```
curl -X GET "http://localhost:8081/subjects/my-topic-value/versions/1"
```
## Get the Latest Schema for a Subject
```
curl -X GET "http://<schema-registry-url>/subjects/<subject-name>/versions/latest"
```
```
curl -X GET "http://localhost:8081/subjects/my-topic-value/versions/latest"
```

## Get All Versions for a Subject
```
curl -X GET "http://<schema-registry-url>/subjects/<subject-name>/versions"
```
```
curl -X GET "http://localhost:8081/subjects/my-topic-value/versions"
```

## Get All Subjects
```
curl -X GET http://<schema-registry-url>/subjects"
```
```
curl -X GET "http://localhost:8081/subjects"
```

## Register a Schema
```
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
    "schema": "{\"type\":\"record\",\"name\":\"myrecord\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"}]}"
  }' \
  "http://<schema-registry-url>/subjects/<subject-name>/versions"
```
```
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
    "schema": "{\"type\":\"record\",\"name\":\"myrecord\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"}]}"
  }' \
  "http://localhost:8081/subjects/my-topic-value/versions"
```

## Check Schema Compatibility
```
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
    "schema": "{\"type\":\"record\",\"name\":\"myrecord\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"}]}"
  }' \
  "http://<schema-registry-url>/compatibility/subjects/<subject-name>/versions/latest"
```
```
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{
    "schema": "{\"type\":\"record\",\"name\":\"myrecord\",\"fields\":[{\"name\":\"name\",\"type\":\"string\"}]}"
  }' \
  "http://localhost:8081/compatibility/subjects/my-topic-value/versions/latest"
```

## Delete the schema by ID
```
curl -X DELETE "http://<schema-registry-url>/subjects/<subject-name>/versions/<version-number>"
```

## Delete all versions of a subject (optional)
```
curl -X DELETE "http://<schema-registry-url>/subjects/<subject-name>"
```
