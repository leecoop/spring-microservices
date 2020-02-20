## Ports

|     Application       |     Port          |
| ------------- | ------------- |
| Users Service | rand (0) ... |
| Spring Cloud Config Server | 8888 |
| Netflix Eureka Naming Server | 8761 |
| Netflix Zuul API Gateway Server | 8765 |
| Zipkin Distributed Tracing Server | 9411 |


## Authentication proccess:

 1. AuthenticationFilter.attemptAuthentication
	  - extract loginRequest
	  - generate UsernamePasswordAuthenticationToken
		  - provide *email* and *password*
			
2.  UserServiceImpl.loadUserByUsername
	- load user from DB by emal
	- if not exist
		 - throw exception 
	- else
		- return security.core.userdetails.User instance with email and encryptedPassword
			
3. AuthenticationFilter.successfulAuthentication			
	- extract emal from authResult
	- load user entity from db
	- generate JWT token
	- append token and userId to response header
	
	
![in28MinutesLearningRoadmap-July2019.png](https://github.com/in28minutes/in28Minutes-Course-Roadmap/raw/master/in28MinutesLearningRoadmap-July2019.png)	
