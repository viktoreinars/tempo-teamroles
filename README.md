Team Roles API

Author: Viktor Einarsson


CHALLENGES:

My endpoint relying on the Teams endpoint:
It complicates setup and maintaining the data. We are effectively maintaining the data in two places now.
I would of course be more interested in appending the functionality to the new endpoint if that were possible.

===========================

How should you approach the default value for team members already in the teams endpoint?
I did not want to duplicate the data from the teams endpoint and try to create the default values in my own copy of the data. Synchronization of data is complex and can be error prone.
So I choose to look at it this way:

    1. Does my in-memory database have information on the role of certain membership?
        1a. Yes = Return that
        1b. No = Return default value

===========================

Data synchronization problems:
There is a strong possibility that the data will get out of sync between the Teams endpoint and my endpoint.

Example: If someone creates a membership role for a certain user in a certain team and later the user and/or team is deleted in the Teams endpoint my endpoint would not know about it.

Some possible solutions to when my endpoint is queried for something that has been deleted/modified in the Teams endpoint:
    1. I could simply return the data that I have and not worry about it. Not a great solution as the endpoint user probably needs to know that the data is most likely not of much use.
    2. I could do checks to verify that the data in the Teams endpoint is still there and if not I can return an error saying that.
    3. I could do the checks in option 2 and delete the data on my site.

I opted to go for option 2.


===========================


