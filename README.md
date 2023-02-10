# FinalProject


| Return Type | Route            | Functionality |
| ----------- | ---------------- |---------------|
|User | GET /authenticate| User can sign in|
|List< User > | GET api/users    |Get all users |
| User   | GET api/users/{id}       |Get one user by id|
| User   | POST api/register       |Register/Create a new user|
| User   | PUT api/users/{id}       |Update an existing user by id|
| void   | DELETE api/users/{id}      |Delete an existing user by id|
| Address| POST  api/user/{UserId}| Create user address|
|Address| POST api/address|Create address|
|Address| PUT api/address/{addressId} | Update address|
|List<WalkLocation>| GET api/walkLocation | Get all WalkLocations|
|WalkLocation| GET api/walkLocation/{locationId} | Get WalkLocation {locationId}|
|WalkLocation| POST api/walkLocation | Create WalkLocation|
|WalkLocation| PUT api/walkLocation/{locationId} | Update WalkLocation {locationId}|
|WalkLocation| DELETE api/walkLocation/{locationId} | Delete WalkLocation {locationId}|
|List< WalkType > | GET api/walkType    |Get all WalkTypes |
|List< Category > | GET api/walkCategory    |Get all WalkLocations |
