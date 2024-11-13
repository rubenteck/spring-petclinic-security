# Goal
Adding security to this the Petclinic app (because medical data should be private according to the GDPR).

## Functional changes
- A publicly available route:
  http://localhost:8080/vets.html  
  get all veterinarians (publicly available data)
- A secured route without autorization
  http://localhost:8080/owners?lastName=  
  get all users (contains sensitive user data)
  (all routes other than /vets require at least this level of security, this route is just an xample)
- 2 secured routes with autorization:
    - veterinarians only:
      http://localhost:8080/owners/1/pets/1/visits/new   
      only veterinarians can add visits.
    - the user itself only:
      http://localhost:8080/owners/1/edit
      http://localhost:8080/owners/1/pets/new  
      only the user itself can edit its data or add pets

## TODO?
- ook auth op functies ipv enkel routes
- andere auth mogelijkheden (oauth2)
- BE mocken in de testen
- auth in andere testen bypassen of standaard ingelogd zijn
- eventueel auth testen per controller zetten ipv apparte auth (niet doen voor elke controller want oefenproject) 
