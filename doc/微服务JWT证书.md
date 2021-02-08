#### 生成证书(JDK路径下):
```text 
keytool -genkeypair -alias auth-jwt -keypass qqqq1234 -storepass qqqq1234 -validity 3650 -keyalg RSA -keysize 1024 -keystore auth-jwt.jks
```

#### 导出证书:
```text
keytool -list -rfc --keystore auth-jwt.jks | openssl x509 -inform pem -pubkey > pubkey.pem
```