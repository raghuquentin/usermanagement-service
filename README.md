# usermanagement-service
java-version : 17,
spring-boot-version: 2.6.6,
database: H2- database,
swagger : enabled  url: http://localhost:8080/swagger-ui/index.html

endpoints: 
api/v1/user:
          /create -> create the user
                ex: request: {
                              "emailAddress": "araghu.95@gmail.com",
                              "userName": "raghu",
                              "password": "12345"
                              }
          /put   -> update the user
                ex: request: {
                              "emailAddress": "araghu.95@gmail.com",
                              "userName": "raghu1",
                              }
          /all   -> get list of user
          /delete/{emailId} -> delete the user by id
          
api/v1/auth/login -> validate the user by email address and password 
                      if user validated successfully, updated the last login date
                      
                   ex: request: {
                              "emailAddress": "araghu.95@gmail.com",
                              "userName": "raghu"
                              }

