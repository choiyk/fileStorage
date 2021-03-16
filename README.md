# FileStorage Server
...

### Install
--------------------------------
```
code blahblah
```

### Run the app
---------------------------------
```
code blahblah
```
<br/><br/>

## REST API

### Upload File
--------------------------------
#### Request
`POST /upload/`
```
http://localhost:8088/upload
```
#### Response
```
Date: Tue, 16 Mar 2021 04:23:53 GMT
Status: 201 Created
Connection: keep-alive
Content-Type: application/json

{"message":"파일을 업로드 했습니다."}
```

<br/>

### Files List
--------------------------------
#### Request
`GET /files/`
```
http://localhost:8088/files
```
#### Response
```
Date: Tue, 16 Mar 2021 04:24:10 GMT
Status: 200 OK
Connection: keep-alive
Content-Type: application/json

[{"id":19,"name":"회사.txt","url":"/files/20210316122420_63.txt","size":84,"date":"2021-03-16T03:24:20.000+00:00","file":null}]
```

<br/>

### Download File
--------------------------------
#### Request
`GET /files/filename/`
```
http://localhost:8088/files/20210316134547_63.txt
```
#### Response
```
Date: Tue, 16 Mar 2021 04:46:21 GMT
Status: 200 OK
Connection: keep-alive
Content-Type: application/json
Content-Disposition: attachment; filename="20210316134547_63.txt"
```

<br/>

#### Request
`GET /files/f/id`
```
http://localhost:8088/files/f/19
```
#### Response
```
Date: Tue, 16 Mar 2021 04:42:29 GMT
Status: 200 OK
Connection: keep-alive
Content-Type: application/json
Content-Disposition: attachment; filename*=UTF-8''a.txt
```

<br/>

### Delete File
--------------------------------
#### Request
`GET /files/f/id`
```
http://localhost:8088/files/f/19
```
#### Response
```
Date: Tue, 16 Mar 2021 04:44:52 GMT
Status: 200 OK
Connection: keep-alive
Content-Type: application/json

{"message":"파일을 삭제했습니다."}
```

<br/>

### Error Code
--------------------------------

<br/>

### 이미 존재하는 파일과 같은 이름의 파일을 업로드 할 때
--------------------------------
#### Request
`POST /upload/`
```
http://localhost:8088/upload
```
#### Response
```
Date: Tue, 16 Mar 2021 04:46:21 GMT
Status: 400 Bad Request
Connection: keep-alive
Content-Type: application/json
{"message":"같은 이름의 파일이 존재합니다."}
```

<br/>

### File의 크기가 2GB 이상일 때
--------------------------------
#### Response
```
Date: Tue, 16 Mar 2021 04:46:21 GMT
Status: 413 Payload Too Large
Connection: keep-alive
Content-Type: application/json
{"message":"파일의 사이즈가 너무 큽니다."}
```

<br/>

### 존재하지 않는 File 요청
--------------------------------
#### Response
```
Date: Tue, 16 Mar 2021 04:46:21 GMT
Status: 404 Not Found
Connection: keep-alive
Content-Type: application/json
```

<br/>

### 서버에서 요청을 처리할 수 없을 때
--------------------------------
#### Response
```
Date: Tue, 16 Mar 2021 04:46:21 GMT
Status: 406 Not Acceptable
Connection: keep-alive
Content-Type: application/json
{"message":"파일을 업로드할 수 없습니다."}
```

<br/>
