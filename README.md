# Project RedHead ServerSide

## Communication API
1. Basic Structure
    * Basic Request Structure:\
      example: http://localhost:8080/request?type=Requesttype&parameter1=1&parameter2=2
       
       * Requesttype: type of the Request
       * other Parameter: Parameter for the Request
     
     * Basic Response Structure:
        ```json
        { "status" : "status" , "value" : "value"}
        ```
        * Status: Status of the Response (OK, ERROR)
        * Value: Value of the Response
    
2. Requests
    * Userinfo: \
        example: http://localhost:8080/request?type=UserInfo&id=123456789
         * Required Parameter:
             * type: "UserInfo"
             * id: Id of the User
        * Response Structure:
        ```json
        { "status" : "OK" , "value" : { "_id" : "123456789" , "name" : "Examplename" , "email" : "example@example.com" , "points" : 1337 , "roleid" : "user"}}
        ```
    * CreateGame: \
         example: http://localhost:8080/request?type=CreateGame&creatorId=123456789&description=bla&options=yes,no
        * Required Parameter:
            * type: "CreateGame"
            * creatorId: UserId that created the Game
            * description: Description of the Game
            * options: Vote Options (Structure: "option1,option2,...")
        * Optional Parameter:
            * timelimit: Voting Timelimit for the game.
         * Response Structure:
         ```json
         { "status" : "OK" , "value" : "Game was created!"}
         ``` 
        
