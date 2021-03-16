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
Status: 200 OK
Connection: keep-alive
Content-Type: application/json

{"message":"파일을 업로드 했습니다."}
```

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

### Download File
--------------------------------
#### Request
`GET /files/filename/`
`
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
`
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

### Delete File
--------------------------------
#### Request
`GET /files/f/id`
`
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
