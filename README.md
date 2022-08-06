# Using-Certificate-based-Authentication-with-the-Microsoft-Graph-API-MuleSoft

This is a mulesoft poc code refering which you can fetch access token for microsoft graph api.
Please replace with your .pfx certificate, password, tenant , client id in property file config.yaml
Please add your .pfx file under src/main/resources/keystore

To generate JWT token the data weave library is used: https://anypoint.mulesoft.com/exchange/68ef9520-24e9-4cf2-b2f5-620025690913/data-weave-jwt-library/minor/1.0/pages/RSA/ this is added as a pom dependency
