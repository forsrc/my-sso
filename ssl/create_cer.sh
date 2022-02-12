



keytool -genkeypair -alias forsrc -keyalg RSA -keysize 4096 -keystore forsrc.p12 -storepass forsrc -storetype pkcs12 -validity 10 -dname "CN=forsrc.com, OU=Example, O=Curity AB, C=CN"

keytool -export -alias forsrc -keystore forsrc.p12 -storepass forsrc -file forsrc.cer