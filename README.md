#Team Roles API

Author: Viktor Einarsson

## How to run

Under classes/artifacts/ there is the Tempo_TeamRoles.jar file that was made with Maven. 
You should be able to run it with "java -jar .\Tempo_TeamRoles.jar". You should see some debugging output. 

The server will listen on http://localhost:8080.

Under the postman-request folder there is a json file which you can import in Postman for a collection of requests.

## CHALLENGES:

#### Roles
I decided that having the three pre-defined roles as constants in my code would be limiting. You should be able to create/delete/modify roles. I also wondered if the "Team lead" data in the Teams endpoint should be merged with my endpoint.

===========================

#### Can user have multiple roles in a membership?

I decided that it can not. If you want to update a role for a membership you need to do a DELETE and POST. Would have been nice to create a PUT method to update a MembershipRole.

===========================

#### My endpoint relying on the Teams endpoint:
It complicates setup and maintaining the data. We are effectively maintaining the data in two places now.
I would of course be more interested in appending the functionality to the Teams endpoint if that were possible. I´m guessing that´s part of the challenge.

===========================

#### How should you approach the default role value ("Developer") for team members already in the Teams endpoint?
I assumed that the default "Developer" role meant that every membership is in fact a "Developer" membership.  

I did not want to duplicate the data from the Teams endpoint and try to create the default values in my own copy of the data. Synchronization of data is complex and can be error prone and the data in the 
Teams endpoint can change without me knowing.

##### 1. Fetching a MembershipRole for a certain user in a certain team:
I chose to look at it this way:

    1. Does my database have information on the role of certain membership role?
        1a. Yes = Return that
        1b. No = If membership is in fact valid return 'default' value
        
Since I am returning a "fake" object for the default value I do not have an Id for the MembershipRole object 
and chose to have it null and would have explained that well in the API documentation. 

Retrieving MembershipRoles by roleId is a bit harder. Since every membership is in fact a "Developer" membership and I do not have the 
data on the roles I cannot simply return data from my database and be certain it is complete. Some options for dealing with this:

    1. I could sync the two endpoints on a schedule. I don´t really like that. 
    2. I could sync the two endpoints when a request is made to the endpoint. I don´t really like that.
    3. I could only return the data I have in my database and explain in the documentation that every membership is a "Developer" membership. I don´t really like that.
    
So I don´t really like any of these options but I opted to go with 3. It kind of breaks the conformity of the two methods but I decided to do it anyway.

===========================

#### How should you handle data that has been created in my endpoint but was later changed in the Teams endpoint?
There is a strong possibility that the data will get out of sync between the Teams endpoint and my endpoint.

Example: If someone creates a membership role for a certain user in a certain team and later the user and/or team is deleted in the Teams endpoint my endpoint would not know about it.

Some possible solutions to when my endpoint is queried for something that has been deleted/modified in the Teams endpoint:
    
    1. I could simply return the data that I have and not worry about it. Not a great solution as the endpoint user probably needs to know that the data is most likely not of much use.
    2. I could do checks to verify that the data in the Teams endpoint is still there and if not I can return an error saying that.
    3. I could do the checks in option 2 and delete the data on my side.

I don´t really like any of the options but I opted to go for option 2. The user can then delete the record if he wants to.

===========================


## API Listing

#### Role Endpoint:

    GET /roles                              - Get all Roles
    GET /roles/{roleId}                     - Get a particular Role by id   
    POST /roles                             - Create a new Role
    DELETE /roles/{roleId}                  - Delete a role by id

#### MembershipRole Endpoint:

    GET /membershipRoles                    - Get all MembershipRoles
    GET /membershipRoles/{id}               - Get a particular MembershipRole by id
    GET /membershipRoles/{userId}/{teamId}  - Get a particular MembershipRole by userId and teamId
    GET /membershipRoles?roleId={roleId}    - Get all MembershipRoles by roleId
    POST /membershipRoles                   - Create a new MembershipRole
    DELETE /membershipRoles                 - Delete a MembershipRole by id