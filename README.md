# imaginnovate-assignment
 
Steps for running the application : 
1. Download the project from github https://github.com/prasanth-tirlangi/imaginnovate-assignment
2. Download Spring tool suite for eclipse from the official website https://spring.io/tools 
3. Open Spring tool suite and import the above downloaded project as Maven project
4. Right click on the project and Click on "Run as Spring boot application"
5. By default the application will run on port 8080
6. Use postman to trigger the api calls

APIs

1. Save Employee Data
   Method : POST

   URI: http://localhost:8080/api/v1/employee

   Body : 

{
	"employeeId": "E1",
    "firstName": "Prasanth",
    "lastName": "Tirlangi",
    "email": "prasanth@email.com",
    "dateOfJoining": "2023-05-16",
    "salary": "50000.00",
    "mobileNumbers": [
        {
            "mobileNumber": "9999999999"
        },
        {
            "mobileNumber": "9999999998"
        }
    ]
}

2. Employee Tax Statement
   Method : GET

   URI: http://localhost:8080/api/v1/employee/tax-statement/1

3. Employee Data
   Method : GET

   URI: http://localhost:8080/api/v1/employee/1
