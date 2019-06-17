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
         { "status" : "OK" , "value" : { "_id" : "123456789abcd"}}
         ``` 
    * CreateBet: \
         example: http://localhost:8080/request?type=CreateBet&id=123456789&gameId=123456789&amount=42&option=0
        * Required Parameter:
            * type: "CreateBet"
            * id: UserId that want to Bet
            * gameId: Id of the game
            * amount: Amount of Points
            * option: Selected Option (Starts at 0)
         * Response Structure:
         ```json
         { "status" : "OK" , "value" : { "_id" : 1}}
         ```  
    * GameList: \
         example: http://localhost:8080/request?type=GameList
        * Required Parameter:
            * type: "GameList"
         * Response Structure:
         ```json
         { "status" : "OK" , "value" : [ { "_id" : "5d07398f94c47b1c2f5ef168" , "description" : "bla" , "date" : { "$date" : "2019-06-17T06:56:15.400Z"} , "timelimit" :  null  , "creator" : "123456789" , "bets" : [ { "_id" : 0 , "user" : "987654321" , "date" : { "$date" : "2019-06-17T07:34:56.550Z"} , "amount" : 42 , "option" : 0}] , "options" : [ "yes" , "no"] , "answer" :  null }]}
         ```   
    * GameInfo: \
         example: http://localhost:8080/request?type=GameInfo&id=5d07398f94c47b1c2f5ef168
        * Required Parameter:
            * type: "GameInfo"
            * id: Game id
         * Response Structure:
         ```json
         { "status" : "OK" , "value" : { "_id" : "5d07398f94c47b1c2f5ef168" , "description" : "bla" , "date" : { "$date" : "2019-06-17T06:56:15.400Z"} , "timelimit" :  null  , "creator" : "123456789" , "bets" : [ { "_id" : 0 , "user" : "987654321" , "date" : { "$date" : "2019-06-17T07:34:56.550Z"} , "amount" : 42 , "option" : 0}] , "options" : [ "yes" , "no"] , "answer" :  null }}
         ```    
    * BetInfo: \
         example: http://localhost:8080/request?type=BetInfo&gameId=5d07398f94c47b1c2f5ef168&id=0
        * Required Parameter:
            * type: "BetInfo"
            * gameId: Game id
            * id / userId: You can get the Bet by the Id of the Bet or the UserId that created the bet
         * Response Structure:
         ```json
         { "status" : "OK" , "value" :  { "_id" : 0 , "user" : "987654321" , "date" : { "$date" : "2019-06-17T07:34:56.550Z"} , "amount" : 42 , "option" : 0}}
         ```     
    * SetAnswer: \
         example: http://localhost:8080/request?type=SetAnswer&id=5d07398f94c47b1c2f5ef168&userId=125313392239050752&value=0
        * Required Parameter:
            * type: "SetAnswer"
            * id: Game id
            * userId: User that wants to set the Answer
            * value: Index of the correct Answer (Starts at 0)
         * Response Structure:
         ```json
         { "status" : "OK" , "value" :  "Answer was set!"}
         ``` 
        
