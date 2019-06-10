# LocalDB-Contact
Android App fetch stagingId, context, status, usedId based on contactId.
Database structure:
contacts
_id, contactId, stagingId
2, 48f3, 1196
3, 3e47, f1fe
4, 2cac, 036e

extensions
context, phoneContactId
Gmail, 2
Gmail, 3
Gmail1,4

accounts
status, userID, context
1, test_one@gmail.com, Gmail
0, test_two@gmail.com, Gmail1

Display all contactId’s in a dropdown and based on selection display the desired result.
So, If I search for 48f3 then the output should be

stagingId, context, status, userId
1196, Gmail, 1, test_one@gmail.com
