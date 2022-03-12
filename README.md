# 產品後端工程師考題



## 如何使用

### 下載Image

1.下載Image檔案

首先把docker image pull下來

```bash
docker pull asce55123/test001:latest
```

可透過`docker image ls`查看是否pull成功



2.透過 `cd`指令將工作目錄移動到下載好的Github專案目錄（因為dockfile檔案放在裡面）

### 啟動docker image

3.啟動docker image

```bash
docker run -d -p 8080:8080 asce55123/test001
```



### 停止container

執行`docker container ls`，得到container ID之後

結果應該會如下：

```bash
docker container stop [CONTAINER ID]
```



## API文件

這份考題我的預設情況是書本名稱不會有重複的情況發生，假設前端所有呼叫API後的動作是建立在回應正確的情況，例如：新增書本/或是更新書本的時候，遇到書本名稱重複，或是根本沒有這個id，後端還是會回應了HTTP 200給前端。因為前端可以透過收到成功呼叫之後，進行想要做的動作（例如AJAX的success fuction()），舉例：新增書本，遇到書本名稱重複，回傳200，前端收到之後fuction()，可以更新頁面、或是出現警示訊息（JavaScript的alert）。



### 新增一本書籍

| 說明         | Method | path  |
| ------------ | ------ | ----- |
| 新增一本書籍 | POST   | /book |

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

創建成功

```json
{
  "state": 200,
  "message": "創建成功",
  "data": null
}
```

書本名稱重複

```json
{
  "state": 200,
  "message": "新增失敗，書本名稱重複",
  "data": null
}
```

未填寫必填欄位

```json
{
  "state": 200,
  "message": "新增失敗，書本名稱不能為空",
  "data": null
}
```



### 更新一本書籍資料

| 說明             | Method | path       |
| ---------------- | ------ | ---------- |
| 更新一本書籍資料 | PUT    | /book/{id} |

#### Parameters

Path

| 參數名稱       | 說明   |
| :------------- | :----- |
| id （Integer） | 書本id |

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

更新成功

```json
{
  "state": 200,
  "message": "更新成功",
  "data":{
    "id": 4,
    "name": "遺忘效應-改",
    "author": "喬．哈特",
    "translator": "彭臨桂",
    "isbn": "9789860668605",
    "publisher": "奇幻基地",
    "publication_date": "2021-07-31T00:00:00.000+00:00",
    "price": 450
    }
}
```

id不存在

```json
{
"state": 200,
"message": "書本id不存在，無法更新資料",
"data": null
}
```



### 刪除一本書籍資料

| 說明         | Method | path       |
| ------------ | ------ | ---------- |
| 刪除一本書籍 | DELETE | /book/{id} |

#### Parameters

Path

| 參數名稱       | 說明   |
| :------------- | :----- |
| id （Integer） | 書本id |

#### Responses

刪除成功

```json
{
  "state": 200,
  "message": "刪除成功",
  "data": null
}
```

id不存在

```json
{
  "state": 200,
  "message": "書本id不存在，無法進行刪除",
  "data": null
}
```





### 列出所有書籍

| 說明         | Method | path  | 參數 |
| ------------ | ------ | ----- | ---- |
| 列出所有書籍 | GET    | /book | 無   |



#### Parameters

No parameters

#### Responses

```json
{ 
  "state": 200,
  "message": "查詢成功",
  "data": {}
}
```



