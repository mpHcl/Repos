# Repos Service

This service interacts with the GitHub API to retrieve information about GitHub repositories.

## Dependencies

- Java 21
- Spring Boot 3
- Lombok

## Installation

1. Clone this repository.
2. Import the project into your IDE.
3. Build and run the project.

## Endpoints

### GET /repos

Retrieves information about repositories owned by a user.

#### Parameters

- `name`: The username of the owner of the repositories.

#### Example

```http
GET /repos?name=exampleUser
```

#### Response example 
- **200 OK**: Returns a list of repositories owned by the user.
```JSON
[
  {
    "name": "exampleRepo",
    "ownerLogin": "exampleUser",
    "branches": [
      {
        "name": "master",
        "sha": "abcdef1234567890"
      }
    ]
  },
  {
    "name": "exampleRepo2",
    "ownerLogin": "exampleUser",
    "branches": [
      {
        "name": "master",
        "sha": "abcdaf1234567890"
      }
    ]
  }
]
```

- **404 Not Found**: If the user cannot be found, returns an error message.
```JSON
{
  "status": 404,
  "message": "User not found."
}
```
