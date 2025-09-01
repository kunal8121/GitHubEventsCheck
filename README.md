GitHub Events CLI
A command-line tool built with Spring Boot that fetches and displays recent GitHub events for any user.

**project URL**
https://github.com/kunal8121/GitHubEventsCheck


Features

Fetches recent GitHub events using GitHub API
Displays up to 5 most recent events
Shows event type, repository, actor, and timestamp
Built with Spring Boot and Java
Command-line interface for easy usage

Prerequisites

Java 17 or higher
Maven 3.6 or higher

Installation

Clone the repository:

bashgit clone https://github.com/kunal8121/github-events-cli.git
cd practiseCode

Build the project:

bashmvn clean package
Usage
Run the application with a GitHub username:
bashjava -jar target/practiseCode-0.0.1-SNAPSHOT.jar <github-username>

Example:
bashjava -jar target/practiseCode-0.0.1-SNAPSHOT.jar kunal8121

Sample Output:
Event: PushEvent | Repo: kunal8121/my-project | Actor: kunal8121 | Timestamp: 2025-09-01T03:00:00Z
Event: CreateEvent | Repo: kunal8121/new-repo | Actor: kunal8121 | Timestamp: 2025-08-31T15:30:00Z
Event: IssuesEvent | Repo: kunal8121/bug-tracker | Actor: kunal8121 | Timestamp: 2025-08-31T10:15:00Z
Project Structure
src/
├── main/
│   ├── java/
│   │   └── com/Practise/practiseCode/
│   │       ├── PractiseCodeApplication.java
│   │       ├── runner/
│   │       │   └── GitHubEventsRunner.java
│   │       └── service/
│   │           └── GitHubEventsService.java
│   └── resources/
│       └── application.properties
├── pom.xml
└── README.md
Dependencies

Spring Boot Starter Web
Gson for JSON parsing
Standard Java HTTP libraries

API Reference
This project uses the GitHub Events API:

Endpoint: https://api.github.com/users/{username}/events
Documentation: GitHub Events API

Error Handling
The application handles various error scenarios:

Invalid usernames
Network connectivity issues
GitHub API rate limiting
JSON parsing errors

Contributing

Fork the repository
Create a feature branch (git checkout -b feature/new-feature)
Commit your changes (git commit -am 'Add new feature')
Push to the branch (git push origin feature/new-feature)
Create a Pull Request

License
This project is open source and available under the MIT License.
Author
Your Name - Your GitHub Profile
Acknowledgments

GitHub API for providing the events data
Spring Boot framework for the application structure
