# Генерация нагрузки

Через hey

```shell
hey -z 30s -c 200 "http://localhost/api/v1/json?payloadSize=1024"
```
