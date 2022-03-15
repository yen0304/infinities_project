#### 產品後端工程師考題



## 如何使用

### 下載Image

1.下載Image檔案

首先把docker image pull下來

```bash
docker pull asce55123/test001:latest
```

可透過`docker image ls`查看是否pull成功



### 啟動docker image

2.啟動docker image

```bash
docker run -d -p 8080:8080 asce55123/test001
```



### 停止container

3.執行`docker container ls`，得到container ID之後

結果應該會如下：

```bash
docker container stop [CONTAINER ID]
```



### Dockerfile

Dockerfile檔案有放在github目錄底下，程式碼如下：

```dockerfile
FROM adoptopenjdk:11-jre-hotspot as builder
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM adoptopenjdk:11-jre-hotspot
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
```



## API文件



### 新增一本書籍

| 說明         | Method | path   |
| ------------ | ------ | ------ |
| 新增一本書籍 | POST   | /books |

#### Parameters

Body

```
{
 name: string($int32),
 author: string($int32",
 translator: string($int32),
 isbn: string($int32),
 publisher: string($int32),
 publication_date: string($date-time),
 price:	integer($int32)
}
```

Example

```json
{
 "name":"遺忘效應",
 "author":"喬．哈特",
 "translator":"彭臨桂",
 "isbn":"9789860668605",
 "publisher":"奇幻基地",
 "publication_date":"2021-07-31",
 "price":450
}
```

- Name 書籍名稱，必填欄位，其餘選填。



#### Responses

| Code | Description                 |
| ---- | --------------------------- |
| 201  | 創建成功                    |
| 400  | 書本名稱重複/未填寫必填欄位 |





### 更新一本書籍資料

| 說明             | Method | path            |
| ---------------- | ------ | --------------- |
| 更新一本書籍資料 | PUT    | /books/{bookId} |

#### Parameters

Path

| 參數名稱          | 說明   |
| :---------------- | :----- |
| bookId（Integer） | 書本id |

Body

```
{
 name: string($int32),
 author: string($int32",
 translator: string($int32),
 isbn: string($int32),
 publisher: string($int32),
 publication_date: string($date-time),
 price:	integer($int32)
}
```

Example

```json
{
 "name":"遺忘效應-改",
 "author":"喬．哈特",
 "translator":"彭臨桂",
 "isbn":"9789860668605",
 "publisher":"奇幻基地",
 "publication_date":"2021-07-31",
  "price":450
}
```

- 沒有傳送的欄位會更改為空值。

#### Responses

| Code | Description |
| ---- | ----------- |
| 200  | 更新成功    |
| 404  | id不存在    |



### 刪除一本書籍資料

| 說明         | Method | path            |
| ------------ | ------ | --------------- |
| 刪除一本書籍 | DELETE | /books/{bookId} |

#### Parameters

Path

| 參數名稱          | 說明   |
| :---------------- | :----- |
| bookId（Integer） | 書本id |

#### Responses

| Code | Description |
| ---- | ----------- |
| 200  | 刪除成功    |
| 404  | id不存在    |



### 列出所有書籍

| 說明         | Method | path   | 參數 |
| ------------ | ------ | ------ | ---- |
| 列出所有書籍 | GET    | /books | 無   |



#### Parameters

No parameters

#### Responses

| Code | Description |
| ---- | ----------- |
| 200  | 查詢成功    |

Example value

```json
[
  {
    "id": 1,
    "name": "哈利波特(6)混血王子的背叛",
    "author": "J.K.羅琳",
    "translator": "林靜華",
    "isbn": "9789573337546",
    "publisher": "皇冠",
    "publication_date": "2021-07-26",
    "price": 699
  },
{
    "id": 2,
    "name": "元宇宙大投資",
    "author": "焦娟, 易歡歡, 毛永豐",
    "translator": null,
    "isbn": "9786267079133",
    "publisher": "樂金文化 ",
    "publication_date": "2022-03-09",
    "price": 480
},
  {
    "id": 3,
    "name": "咒術迴戰 18",
    "author": "芥見",
    "translator": "張紹仁",
    "isbn": "9789572681824",
    "publisher": "東立 ",
    "publication_date": "2022-01-06",
    "price": 100
  }
]
```